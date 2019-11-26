/**
 *
 */
package org.javabase.apps.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.dto.TempStudentAttendance;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.entity.StudentFee;
import org.javabase.apps.entity.StudentResult;
import org.javabase.apps.mapper.CourseMapper;
import org.javabase.apps.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }
    
    @Override
    public List<Student> getAllActiveStudents() {
        return studentMapper.getAllActiveStudents();
    }

    @Override
    public Student convertTempObjectToMain(TempStudent tempStudent, Map<String, Object> objMap) {
            Batch batch= objMap.containsKey("batch") && objMap.get("batch") != null ? (Batch) objMap.get("batch") : null ;
            Course course= objMap.containsKey("course") && objMap.get("course") != null ? (Course) objMap.get("course") : null ;
            
            Student student = new Student();
            student.setEnrollmentNumber(tempStudent.getEnrollmentNumber());
            student.setCourseId(course);
            student.setBatchId(batch);
            student.setFirstName(tempStudent.getFirstName());
            student.setMiddleName(tempStudent.getMiddleName());
            student.setLastName(tempStudent.getLastName());
            student.setGender(tempStudent.getGender());
            student.setBirthDate(Date.valueOf(tempStudent.getBirthDate()));
            student.setEnrollmentDate(Date.valueOf(tempStudent.getEnrollmentDate()));
            student.setAddress(tempStudent.getAddress());
            student.setMobileNumber(tempStudent.getMobileNumber());
            student.setEmailId(tempStudent.getEmailId());
            student.setAadharNumber(tempStudent.getAadharNumber());
            student.setGuardianFullName(tempStudent.getGuardianFullName());
            student.setGuardianFullAddress(tempStudent.getGuardianFullAddress());
            student.setGuardianMobileNumber(tempStudent.getGuardianMobileNumber());
            student.setBloodGroup(tempStudent.getBloodGroup());
            student.setDisability(tempStudent.getDisability());
            student.setDisabilityDetail(tempStudent.getDisabilityDetail());
            student.setDiscount(tempStudent.getDiscount());
            student.setQualification(tempStudent.getQualification());
            student.setActive(tempStudent.getActive());
            student.setUsername(tempStudent.getUsername());
            
            return student;
        }

        @Override
        public List<TempStudent> convertMainObjectToTemp(List<Student> studentList) {
            List<TempStudent> tempStudentList=new ArrayList<>();
            if(studentList != null && studentList.size()>0){
                for(Student student : studentList){
                    TempStudent ts=new TempStudent();
                    ts.setId(student.getId());
                    ts.setEnrollmentNumber(student.getEnrollmentNumber());
                    ts.setCourseName(student.getCourseId() != null ? student.getCourseId().getCourseName() : "");
                    ts.setCourseId(student.getCourseId() != null ? String.valueOf(student.getCourseId().getId()) : "");
                    ts.setBatchName(student.getBatchId() != null ? student.getBatchId().getBatch() : "");
                    ts.setBatchId(student.getBatchId() != null ? String.valueOf(student.getBatchId().getId()) : "");
                    ts.setFirstName(student.getFirstName());
                    ts.setMiddleName(student.getMiddleName());
                    ts.setLastName(student.getLastName());
                    ts.setGender(student.getGender());
                    ts.setBirthDate(String.valueOf(student.getBirthDate()));
                    ts.setEnrollmentDate(String.valueOf(student.getEnrollmentDate()));
                    ts.setAddress(student.getAddress());
                    ts.setMobileNumber(student.getMobileNumber());
                    ts.setEmailId(student.getEmailId());
                    ts.setAadharNumber(student.getAadharNumber());
                    ts.setGuardianFullName(student.getGuardianFullName());
                    ts.setGuardianFullAddress(student.getGuardianFullAddress());
                    ts.setGuardianMobileNumber(student.getGuardianMobileNumber());
                    ts.setBloodGroup(student.getBloodGroup());
                    ts.setDisability(student.getDisability());
                    ts.setDisabilityDetail(student.getDisabilityDetail());
                    ts.setDiscount(student.getDiscount());
                    ts.setQualification(student.getQualification());
                    ts.setActive(student.getActive());
                    ts.setUsername(student.getUsername());
                    tempStudentList.add(ts);
                }
            }
            return tempStudentList;
        }

        @Override
        public List<TempStudentAttendance> convertStudentAttendanceObjectToTemp(List<StudentAttendance> studentattendanceList) {
            List<TempStudentAttendance> tempStudentAttendanceList=new ArrayList<>();
            if(studentattendanceList != null && studentattendanceList.size()>0){
                for(StudentAttendance sa : studentattendanceList){
                    TempStudentAttendance tsa=new TempStudentAttendance();
                    
                    tsa.setId(sa.getId());
                    tsa.setEnrollmentNumber(sa.getStudentId() != null ? sa.getStudentId().getEnrollmentNumber() : "");
                    tsa.setStudentId(sa.getStudentId() != null ? String.valueOf(sa.getStudentId().getId()) : "");
                    tsa.setStudentName(sa.getStudentId() != null ? (sa.getStudentId().getFirstName() +" "+sa.getStudentId().getLastName()) : "");
                    tsa.setCourseName(sa.getCourseId() != null ? sa.getCourseId().getCourseName() : "");
                    tsa.setCourseId(sa.getCourseId() != null ? String.valueOf(sa.getCourseId().getId()) : "");
                    tsa.setBatchName(sa.getBatchId() != null ? sa.getBatchId().getBatch() : "");
                    tsa.setBatchId(sa.getBatchId() != null ? String.valueOf(sa.getBatchId().getId()) : "");
                    tsa.setSemesterId(sa.getSemesterId() != null ? String.valueOf(sa.getSemesterId().getId()) : "");
                    tsa.setMonthName(sa.getMonthName());
                    
                    tsa.setA1(sa.getA1());
                    tsa.setA2(sa.getA2());
                    tsa.setA3(sa.getA3());
                    tsa.setA4(sa.getA4());
                    tsa.setA5(sa.getA5());
                    tsa.setA6(sa.getA6());
                    tsa.setA7(sa.getA7());
                    tsa.setA8(sa.getA8());
                    tsa.setA9(sa.getA9());
                    tsa.setA10(sa.getA10());
                    tsa.setA11(sa.getA11());
                    tsa.setA12(sa.getA12());
                    tsa.setA13(sa.getA13());
                    tsa.setA14(sa.getA14());
                    tsa.setA15(sa.getA15());
                    tsa.setA16(sa.getA16());
                    tsa.setA17(sa.getA17());
                    tsa.setA18(sa.getA18());
                    tsa.setA19(sa.getA19());
                    tsa.setA20(sa.getA20());
                    tsa.setA21(sa.getA21());
                    tsa.setA22(sa.getA22());
                    tsa.setA23(sa.getA23());
                    tsa.setA24(sa.getA24());
                    tsa.setA25(sa.getA25());
                    tsa.setA26(sa.getA26());
                    tsa.setA27(sa.getA27());
                    tsa.setA28(sa.getA28());
                    tsa.setA29(sa.getA29());
                    tsa.setA30(sa.getA30());
                    tsa.setA31(sa.getA31());
                    
                    tsa.setTotalWorkingDaysInMonth(sa.getTotalWorkingDaysInMonth());
                    tsa.setTotalDaysInMonth(sa.getTotalDaysInMonth());
                    tsa.setTotalAbsentCount(sa.getTotalAbsentCount());
                    tsa.setTotalPresentCount(sa.getTotalPresentCount());
                    tsa.setTotalHolidaysInMonth(sa.getTotalHolidaysInMonth());
                    tsa.setTotalLeaveCount(sa.getTotalLeaveCount());
                    tsa.setTotalAggregatePercentage((100 * (sa.getTotalPresentCount() + sa.getTotalLeaveCount()))/sa.getTotalWorkingDaysInMonth());
                   
                    tempStudentAttendanceList.add(tsa);
                }
            }
            return tempStudentAttendanceList;
        }

    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText) {
        return studentMapper.getAllStudentsWithSearchCriteria(searchType,searchText);
    }
    
    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText,String fetchActive) {
        return studentMapper.getAllStudentsWithSearchCriteria(searchType,searchText,fetchActive);
    }

    @Override
    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId) {
        return studentMapper.getTotalFeeToBePaidForStudentForCourse(studentId,courseId,batchId);
    }
    
    @Override
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId) {
        return studentMapper.getTotalPaidFeeForStudent(studentId,courseId,batchId);
    }

    @Override
    public List<StudentFee> getStudentFeeHistory(Student student) {
        return studentMapper.getStudentFeeHistory(student);
    }

    @Override
    public Student getStudentByEnrollmentNumber(String enrollmentNumber) {
        return studentMapper.getStudentByEnrollmentNumber(enrollmentNumber);
    }
    
    @Override
    public Student getStudentByMobileNumber(String mobileNumber) {
        return studentMapper.getStudentByMobileNumber(mobileNumber);
    }
    
    @Override
    public Student getStudentByUsername(String Username) {
        return studentMapper.getStudentByUsername( Username);
    }
    
    @Override
    public Student getStudentByEmailId(String emailId){
        return studentMapper.getStudentByEmailId(emailId);
    }
    
    @Override
    public int deleteStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month){
        return studentMapper.deleteStudentAttendanceForGivenCriteria(courseId, batchId, semesterId, month);
    }
    
    @Override
    public int deleteStudentResultForGivenCriteria(int courseId, int batchId,int semesterId){
        return studentMapper.deleteStudentResultForGivenCriteria(courseId, batchId, semesterId);
    }
    
    @Override
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month){
        return studentMapper.getStudentAttendanceForGivenCriteria(courseId, batchId, semesterId, month);
    }
    
    @Override
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int studentId){
        return studentMapper.getStudentAttendanceForGivenCriteria(studentId);
    }

    @Override
    public List<StudentAttendance> getStudentAggregateAttendanceForGivenCriteria(int courseId, int batchId, int semesterId) {
         return studentMapper.getStudentAggregateAttendanceForGivenCriteria(courseId, batchId, semesterId);
    }
    
    @Override
    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId,int semesterId){
        return studentMapper.getStudentResultForGivenCriteria(courseId, batchId, semesterId);
    }
    
    @Override
    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId,int semesterId,int studentId){
        return studentMapper.getStudentResultForGivenCriteria(courseId, batchId, semesterId,studentId);
    }
}
