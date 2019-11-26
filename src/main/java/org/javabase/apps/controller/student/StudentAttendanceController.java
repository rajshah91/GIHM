package org.javabase.apps.controller.student;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.javabase.apps.dto.TempStudentAttendance;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.excel.ExcelReadHandler;
import org.javabase.apps.service.BatchService;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.StudentService;
import org.javabase.apps.utility.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "dashboard/student")
public class StudentAttendanceController {

    @Autowired
    CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/attendance", "/attendance?*"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String studentAttendancePage() {
        return "student/studentAttendance";
    }
    
    @RequestMapping(value = {"/viewAttendance"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String viewStudentAttendancePage() {
        return "student/viewStudentAttendance";
    }

    @RequestMapping(value = {"/viewAttendanceToStudent", "/viewAttendanceToStudent?*"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String AttendanceViewToStudentPage() {
        return "student/viewAttendanceToStudent";
    }

    @RequestMapping(value = {"/viewAggregateAttendance"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String viewAggregateAttendance() {
        return "student/viewAggregateStudentAttendance";
    }

    @RequestMapping(value = "downloadsamplefile", method = {RequestMethod.GET, RequestMethod.POST})
    public void studentAttendanceSampleFileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = null;
        String month = request.getParameter("month");
        String fileName = "/files/Student_Attendance_" + month + ".xlsx";

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        file = new File(classloader.getResource(fileName).getFile());

        if (file == null || !file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            }
            return;
        }
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : " + mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "uploadStudentAttendance", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadStudentAttendance(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId, @RequestParam("month") String month,
            @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> response = new HashMap<>();

        boolean success = false;
        boolean isAnyRecordFailed = true;
        boolean fileUploadsuccess = false;
        String message = "";
        String fileUploadMessage = "";
        String uploadedFileFullPath = "";
        String uploadedFileName = "";
        if (!MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(semesterId) && !MyUtils.isNullOrEmpty(month) && file != null && !file.isEmpty()) {
            Batch batch = new Batch();
            batch = (Batch) commonService.getObjectById(batch, Integer.parseInt(batchId));

            Course course = new Course();
            course = (Course) commonService.getObjectById(course, Integer.parseInt(courseId));

            Semester semester = new Semester();
            semester = (Semester) commonService.getObjectById(semester, Integer.parseInt(semesterId));

            Map<String, String> uploadMap = MyUtils.uploadFile(file, MyUtils.FileType.Permanent, file.getOriginalFilename());
            if (uploadMap != null && uploadMap.size() > 0) {
                fileUploadsuccess = Boolean.parseBoolean(uploadMap.get("success"));
                fileUploadMessage = uploadMap.get("message");
                uploadedFileFullPath = uploadMap.get("uploaded_file_full_path");
                uploadedFileName = uploadMap.get("uploaded_file_name");
                if (!MyUtils.isNullOrEmpty(uploadedFileName) && !MyUtils.isNullOrEmpty(uploadedFileFullPath)) {
                    ExcelReadHandler erd = new ExcelReadHandler();
                    erd.setStudentService(studentService);
                    List<StudentAttendance> studAttendance = erd.handleStudentAttendanceFile(uploadedFileName, uploadedFileFullPath, course, batch, semester, month);
                    if (studAttendance != null && studAttendance.size() > 0) {
                        int deleted = studentService.deleteStudentAttendanceForGivenCriteria(course.getId(), batch.getId(), semester.getId(), month);
                        for (StudentAttendance sa : studAttendance) {
                            success = commonService.saveObject(sa);
                            if (!success && !isAnyRecordFailed) {
                                isAnyRecordFailed = true;
                            }else{
                                isAnyRecordFailed =false;
                            }
                        }
                    }
                }
            }
        }

        return "redirect:/dashboard/student/attendance?success=" + !isAnyRecordFailed;
    }

    @ResponseBody
    @RequestMapping(value = "/viewAttendance/getStudentAttendance", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getStudentAttendance(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId, @RequestParam("month") String month) {

        Map<String, Object> response = new HashMap<>();
        List<StudentAttendance> studentAttendanceList = new ArrayList<>();
        List<TempStudentAttendance> tempstudentAttendanceList = new ArrayList<>();

        if (!MyUtils.isNullOrEmpty(month) && !MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(semesterId)) {
            studentAttendanceList = studentService.getStudentAttendanceForGivenCriteria(Integer.parseInt(courseId), Integer.parseInt(batchId), Integer.parseInt(semesterId), month);
        }

        if (studentAttendanceList != null && !studentAttendanceList.isEmpty()) {
            tempstudentAttendanceList=studentService.convertStudentAttendanceObjectToTemp(studentAttendanceList);
        }
        
        response.put("success", true);
        response.put("message", "Student Attendance Load Sucess.");
        response.put("data", tempstudentAttendanceList);
        return response;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/viewAttendance/getAggregateStudentAttendance", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getAggregateStudentAttendance(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId) {

        Map<String, Object> response = new HashMap<>();
        List<StudentAttendance> studentAttendanceList = new ArrayList<>();
        List<TempStudentAttendance> tempstudentAttendanceList = new ArrayList<>();

        if (!MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(semesterId)) {
            studentAttendanceList = studentService.getStudentAggregateAttendanceForGivenCriteria(Integer.parseInt(courseId), Integer.parseInt(batchId), Integer.parseInt(semesterId));
        }

        if (studentAttendanceList != null && !studentAttendanceList.isEmpty()) {
            tempstudentAttendanceList=studentService.convertStudentAttendanceObjectToTemp(studentAttendanceList);
        }
        
        response.put("success", true);
        response.put("message", "Student Aggregate Attendance Load Sucess.");
        response.put("data", tempstudentAttendanceList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/viewAttendanceToStudent/getStudentAttendance", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getAttendanceOfStudent(@RequestParam("studentId") String studentId) {

        Map<String, Object> response = new HashMap<>();
        List<StudentAttendance> studentAttendanceList = new ArrayList<>();
        List<TempStudentAttendance> tempstudentAttendanceList = new ArrayList<>();

        if (!MyUtils.isNullOrEmpty(studentId)) {
            studentAttendanceList = studentService.getStudentAttendanceForGivenCriteria(Integer.parseInt(studentId));
        }

        if (studentAttendanceList != null && !studentAttendanceList.isEmpty()) {
            tempstudentAttendanceList=studentService.convertStudentAttendanceObjectToTemp(studentAttendanceList);
        }
        
        response.put("success", true);
        response.put("message", "Student Attendance Load Sucess.");
        response.put("data", tempstudentAttendanceList);
        return response;
    }
}
