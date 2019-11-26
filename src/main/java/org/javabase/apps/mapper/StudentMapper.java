package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.entity.StudentFee;
import org.javabase.apps.entity.StudentResult;

public interface StudentMapper {

    public List<Student> getAllStudents();
    
    public List<Student> getAllActiveStudents();

    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText);
    
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText,String fetchActive);

    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId);

    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId);

    public List<StudentFee> getStudentFeeHistory(Student student);

    public Student getStudentByEnrollmentNumber(String enrollmentNumber);
    
    public Student getStudentByMobileNumber(String mobileNumber);
    
    public Student getStudentByUsername(String Username);
    
    public int deleteStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month);
    
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month);
    
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int studentId);

    public List<StudentAttendance> getStudentAggregateAttendanceForGivenCriteria(int courseId, int batchId, int semesterId);

    public int deleteStudentResultForGivenCriteria(int courseId, int batchId, int semesterId);

    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId, int semesterId);
    
    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId, int semesterId,int studentId);

    public Student getStudentByEmailId(String emailId);

}
