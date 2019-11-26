package org.javabase.apps.controller.student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Permission;
import org.javabase.apps.entity.Role;
import org.javabase.apps.entity.RolePermission;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.User;
import org.javabase.apps.entity.UserPermission;
import org.javabase.apps.service.BatchService;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.StudentService;
import org.javabase.apps.utility.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/student")
public class AddStudentController {

    @Autowired
    CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String studentPage() {
        return "student/addStudent";
    }

    @ResponseBody
    @RequestMapping(value = "loadallbatch", method = RequestMethod.GET)
    public Map<String, Object> getAllBatch(@RequestParam(value = "active" ,required = false ,defaultValue = "false") String active) {
        Map<String, Object> response = new HashMap<>();
        List<Batch> batchList = null;
        if(active != null && "true".equalsIgnoreCase(active)){
            batchList=batchService.getAllActiveBatch();
        }else{
            batchList=batchService.getAllBatch();
        }
        response.put("success", true);
        response.put("data", batchList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "loadallcourse", method = RequestMethod.GET)
    public Map<String, Object> getAllCourse(@RequestParam(value = "active" ,required = false ,defaultValue = "false") String active)  {
        Map<String, Object> response = new HashMap<>();
        List<Course> courseList = null;
        if(active != null && "true".equalsIgnoreCase(active)){
            courseList=courseService.getAllActiveCourse();
        }else{
            courseList=courseService.getAllCourse();
        }
        response.put("success", true);
        response.put("data", courseList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "enrollstudent", method = RequestMethod.POST)
    public Map<String, Object> enrollStudent(@RequestBody TempStudent tempStudent) {
        Map<String, Object> response = new HashMap<>();
        TempStudent ts = tempStudent;
        Boolean save = false;

        Batch batch = new Batch();
        batch = (Batch) commonService.getObjectById(batch, Integer.parseInt(ts.getBatchId()));

        Course course = new Course();
        course = (Course) commonService.getObjectById(course, Integer.parseInt(ts.getCourseId()));

        if (course != null && batch != null) {
            Map<String, Object> map = new HashMap();
            map.put("course", course);
            map.put("batch", batch);
            Student student = studentService.convertTempObjectToMain(tempStudent, map);
            student.setActive("true");
            save = commonService.saveObject(student);

            if (save && !MyUtils.isNullOrEmpty(ts.getUsername()) && !MyUtils.isNullOrEmpty(ts.getPassword())) {
                Role role=new Role();
                role=(Role)commonService.getObjectById(role,2);//student role
                
                Permission p=new Permission();
                p=(Permission)commonService.getObjectById(p,2);//View Permission
                
                User user = new User();
                user.setCreateDate(new Date());
                user.setActive(true);
                user.setAccountNonExpired(true);
                user.setCredintialNonExpired(true);
                user.setNonLocked(true);
                user.setUsername(ts.getUsername());
                user.setPassword(ts.getPassword());
                user.setEmail(ts.getEmailId());
                user.setRole(role);
                save = commonService.saveObject(user);   
                
                UserPermission up=new UserPermission();
                up.setIsActive(true);
                up.setGrantDate(new Date());
                up.setGrantUser(1);
                up.setUser(user);
                up.setPermission(p);
                save = commonService.saveObject(up);   
                
                RolePermission rp=new RolePermission();
                rp.setPermission(p);
                rp.setGrantUser(1);
                rp.setIsActive(true);
                rp.setRole(role);
                rp.setGrantDate(new Date());
                save=commonService.saveObject(rp);
            }
        }

        if (save) {
            response.put("success", true);
            response.put("message", "Student enrolled Sucessfully.");
            return response;
        } else {
            response.put("error", true);
            response.put("success", false);
            response.put("message", "Student enrollment Failed");
            return response;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "editstudent", method = RequestMethod.POST)
    public Map<String, Object> editStudent(@RequestBody TempStudent tempStudent) {
        Map<String, Object> response = new HashMap<>();
        TempStudent ts = tempStudent;
        Boolean save = false;
        
        Student student = (Student) commonService.getObjectById(new Student(), ts.getId());
        if (student != null) {
            student.setFirstName(ts.getFirstName());
            student.setMiddleName(ts.getMiddleName());
            student.setLastName(ts.getLastName());
            student.setAddress(ts.getAddress());
            student.setMobileNumber(ts.getMobileNumber());
            student.setEmailId(ts.getEmailId());
            student.setGuardianFullName(ts.getGuardianFullName());
            student.setGuardianFullAddress(ts.getGuardianFullAddress());
            student.setGuardianMobileNumber(ts.getGuardianMobileNumber());
            student.setAadharNumber(ts.getAadharNumber());
            
            save = commonService.saveOrUpdateObject(student);
        }

        if (save) {
            response.put("success", true);
            response.put("message", "Student data updated Sucessfully.");
            return response;
        } else {
            response.put("error", true);
            response.put("success", false);
            response.put("message", "Student data update Failed");
            return response;
        }
    }

}
