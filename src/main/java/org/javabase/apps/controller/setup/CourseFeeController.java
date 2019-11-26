package org.javabase.apps.controller.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/courseFee")
public class CourseFeeController {

    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAddCourseFeePage() {
        return "institution/courseFee";
    }
   
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> addCourseFee(@RequestBody TempCourse tempCourse) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = false;
        
        Batch batch=new Batch();
        batch = (Batch)commonService.getObjectById(batch, Integer.parseInt(tempCourse.getBatchId()));
        
        Course course=new Course();
        course = (Course)commonService.getObjectById(course, Integer.parseInt(tempCourse.getCourseId()));
        
        if (course != null && batch != null) {
            CourseFee coursefee = new CourseFee();
            coursefee.setBatchId(batch);
            coursefee.setCourseId(course);
            coursefee.setFeeAmount(tempCourse.getFees());
            save = commonService.saveObject(coursefee);
        }

        if (save) {
            response.put("success", true);
            response.put("message", "Add Course Fee Success");
            return response;
        } else {
            response.put("error", true);
            response.put("success", false);
            response.put("message", "Add Course Fee Failed");
            return response;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "loadcoursefee", method = RequestMethod.GET)
    public Map<String, Object> getAllCourseFee() {
        Map<String, Object> response = new HashMap<>();
        List<CourseFee> courseFeeList = courseService.getAllCourseFee();
        List<TempCourse> tempcourseFeeList = new ArrayList<>();
        for(CourseFee cf : courseFeeList){
            TempCourse tc=new TempCourse();
            tc.setCourseName(cf.getCourseId().getCourseName());
            tc.setCourseId(String.valueOf(cf.getCourseId().getId()));
            tc.setFees(cf.getFeeAmount());
            tc.setBatchName(cf.getBatchId().getBatch());
            tc.setBatchId(String.valueOf(cf.getBatchId().getId()));
            tempcourseFeeList.add(tc);
        }
        response.put("success", true);
        response.put("data", tempcourseFeeList);
        return response;

    }

}
