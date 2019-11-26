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
import org.javabase.apps.dto.TempStudentResult;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentResult;
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
public class StudentResultController {

    @Autowired
    CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/uploadResult"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String studentResultUploadPage() {
        return "student/uploadStudentResult";
    }

    @RequestMapping(value = {"/viewResult"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String viewStudentResultPage() {
        return "student/viewStudentResult";
    }

    @RequestMapping(value = {"/viewResultToStudent", "/viewResultToStudent?*"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String AttendanceViewToStudentPage() {
        return "student/viewResultToStudent";
    }

//    @RequestMapping(value = {"/viewAggregateAttendance"}, method = {RequestMethod.GET, RequestMethod.POST})
//    public String viewAggregateAttendance() {
//        return "student/viewAggregateStudentAttendance";
//    }
    @RequestMapping(value = "downloadsampleresultfile", method = {RequestMethod.GET, RequestMethod.POST})
    public void studentResultSampleFileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = null;
        String fileName = "/files/student_result_template.xlsx";

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

    @RequestMapping(value = "uploadStudentResult", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadStudentResult(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId, @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, Object> response = new HashMap<>();

        boolean success = false;
        boolean isAnyRecordFailed = true;
        boolean fileUploadsuccess = false;
        String message = "";
        String fileUploadMessage = "";
        String uploadedFileFullPath = "";
        String uploadedFileName = "";
        if (!MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(semesterId) && file != null && !file.isEmpty()) {
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

                    Map<String, String> studentResultJsonMap = erd.handleStudentResultFile(uploadedFileName, uploadedFileFullPath);

                    if (studentResultJsonMap != null && studentResultJsonMap.size() > 0) {
                        int deleted = studentService.deleteStudentResultForGivenCriteria(course.getId(), batch.getId(), semester.getId());
                        for (Map.Entry<String, String> entry : studentResultJsonMap.entrySet()) {
                            Student student = null;
                            student = studentService.getStudentByEnrollmentNumber(entry.getKey());
                            if (student != null && student.getId() != null) {
                                StudentResult sr = new StudentResult();
                                sr.setBatchId(batch);
                                sr.setCourseId(course);
                                sr.setSemesterId(semester);
                                sr.setStudentId(student);
                                sr.setStudentResultJson(entry.getValue());
                                success = commonService.saveObject(sr);
                            }
                            isAnyRecordFailed = !success && !isAnyRecordFailed;
                        }
                    }
                }
            }
        }

        return "redirect:/dashboard/student/uploadResult?success=" + !isAnyRecordFailed;
    }
     
    @ResponseBody
    @RequestMapping(value = "/viewResult/getStudentResult", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getStudentResult(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId) {
        
        Map<String, Object> response = new HashMap<>();
        List<StudentResult> studentResultList = new ArrayList<>();
        List<TempStudentResult> tempstudentResultList = new ArrayList<>();

        if (!MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(semesterId)) {
            studentResultList = studentService.getStudentResultForGivenCriteria(Integer.parseInt(courseId), Integer.parseInt(batchId), Integer.parseInt(semesterId));
        }

        if (studentResultList != null && !studentResultList.isEmpty()) {
            for(StudentResult sr : studentResultList){
                TempStudentResult tsr=new TempStudentResult();
                tsr.setBatchId(sr.getBatchId().getId());
                tsr.setBatchName(sr.getBatchId().getBatch());
                tsr.setCourseId(sr.getCourseId().getId());
                tsr.setCourseName(sr.getCourseId().getCourseName());
                tsr.setSemesterId(sr.getSemesterId().getId());
                tsr.setSemesterName(String.valueOf(sr.getSemesterId().getSemester()));
                tsr.setStudentId(sr.getStudentId().getId());
                tsr.setStudentName(sr.getStudentId().getFirstName() +  " " + sr.getStudentId().getLastName());
                tsr.setStudentName(sr.getStudentId().getFirstName() +  " " + sr.getStudentId().getLastName());
                tsr.setEnrollmentNo(sr.getStudentId().getEnrollmentNumber());
                tsr.setStudentResultJson(sr.getStudentResultJson());
                tempstudentResultList.add(tsr);
            }
        }
        
        response.put("success", true);
        response.put("message", "Student Result Load Sucess.");
        response.put("data", tempstudentResultList);
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "/viewResultToStudent/getStudentResult", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getResultOfStudent(@RequestParam("batchId") String batchId, @RequestParam("semesterId") String semesterId,
            @RequestParam("courseId") String courseId,@RequestParam("studentId") String studentId) {
        
        Map<String, Object> response = new HashMap<>();
        List<StudentResult> studentResultList = new ArrayList<>();
        List<TempStudentResult> tempstudentResultList = new ArrayList<>();

        if (!MyUtils.isNullOrEmpty(batchId) && !MyUtils.isNullOrEmpty(courseId) && !MyUtils.isNullOrEmpty(semesterId) && !MyUtils.isNullOrEmpty(studentId)) {
            studentResultList = studentService.getStudentResultForGivenCriteria(Integer.parseInt(courseId), Integer.parseInt(batchId), Integer.parseInt(semesterId), Integer.parseInt(studentId));
        }

        if (studentResultList != null && !studentResultList.isEmpty()) {
            for(StudentResult sr : studentResultList){
                TempStudentResult tsr=new TempStudentResult();
                tsr.setBatchId(sr.getBatchId().getId());
                tsr.setBatchName(sr.getBatchId().getBatch());
                tsr.setCourseId(sr.getCourseId().getId());
                tsr.setCourseName(sr.getCourseId().getCourseName());
                tsr.setSemesterId(sr.getSemesterId().getId());
                tsr.setSemesterName(String.valueOf(sr.getSemesterId().getSemester()));
                tsr.setStudentId(sr.getStudentId().getId());
                tsr.setStudentName(sr.getStudentId().getFirstName() +  " " + sr.getStudentId().getLastName());
                tsr.setStudentName(sr.getStudentId().getFirstName() +  " " + sr.getStudentId().getLastName());
                tsr.setEnrollmentNo(sr.getStudentId().getEnrollmentNumber());
                tsr.setStudentResultJson(sr.getStudentResultJson());
                tempstudentResultList.add(tsr);
            }
        }
        
        response.put("success", true);
        response.put("message", "Student Result Load Sucess.");
        response.put("data", tempstudentResultList);
        return response;
    }
}
