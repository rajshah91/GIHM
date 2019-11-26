/**
 *
 */
package org.javabase.apps.mapper;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.entity.StudentFee;
import org.javabase.apps.entity.StudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        try {
            String hql = "FROM Student";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllActiveStudents() {
        try {
            String hql = "FROM Student WHERE active='true'";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText) {
        try {
            String hql = "FROM Student s WHERE s." + searchType + " like '%" + searchText + "%'";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText,String fetchActive) {
        try {
            String hql = "FROM Student s WHERE s." + searchType + " like '%" + searchText + "%' AND active='"+fetchActive+"' ";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "SELECT SUM(sf.amountPaid) FROM StudentFee sf WHERE sf.studentId.id=" + studentId + " AND sf.courseId.id=" + courseId + " AND sf.batchId.id=" + batchId;
            hql += " GROUP BY sf.studentId.id,sf.batchId.id,sf.courseId.id ";

            Query query = session.createQuery(hql);
            List listResult = query.list();
            double paidfee = listResult != null && listResult.size() > 0 ? (double) listResult.get(0) : 0;
            session.close();
            return paidfee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return 0;
        }
    }

    @Override
    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "SELECT cf.feeAmount-s.discount FROM CourseFee cf JOIN Student s ON s.courseId=cf.courseId "
                    + " WHERE s.id=" + studentId + " AND cf.courseId.id=" + courseId + " AND cf.batchId.id=" + batchId;

//            String sql="SELECT cf.fee_amount-s.discount from course_fee cf INNER JOIN student s ON s.course_id=cf.course_id "
//                    + " WHERE s.id='' AND cf.course_id ='' AND cf.batch_id=''";
            Query query = session.createQuery(hql);
            List listResult = query.list();
            double paidfee = listResult != null && listResult.size() > 0 ? (double) listResult.get(0) : 0;
            session.close();
            return paidfee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return 0;
        }
    }

    @Override
    public List<StudentFee> getStudentFeeHistory(Student student) {
        try {
            String hql = "FROM StudentFee sf WHERE sf.studentId.id=" + student.getId();
            return (List<StudentFee>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Student getStudentByEnrollmentNumber(String enrollmentNumber) {
        try {
            String hql = "FROM Student s WHERE s.enrollmentNumber='" + enrollmentNumber + "'";
            return (Student) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Student getStudentByUsername(String Username) {
        try {
            String hql = "FROM Student s WHERE s.username='" + Username + "'";
            return (Student) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    @Override
    public Student getStudentByMobileNumber(String mobileNumber) {
        try {
            String hql = "FROM Student s WHERE s.mobileNumber='" + mobileNumber + "'";
            return (Student) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Student getStudentByEmailId(String emaiId) {
        try {
            String hql = "FROM Student s WHERE s.emailId='" + emaiId + "'";
            return (Student) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public int deleteStudentAttendanceForGivenCriteria(int courseId, int batchId, int semesterId, String month) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        int totalDeleteCount = 0;
        try {
            Query hql = session.createQuery("DELETE FROM StudentAttendance sa WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId + " AND sa.monthName='" + month + "' ");
            totalDeleteCount = hql.executeUpdate();
            session.close();
            return totalDeleteCount;
        } catch (Exception e) {
            session.close();
            System.out.println("Error in CommonMapperImpl.deleteObject : " + e.getMessage());
            e.printStackTrace();
            return totalDeleteCount;
        }
    }
    
    @Override
    @Transactional
    public int deleteStudentResultForGivenCriteria(int courseId, int batchId, int semesterId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        int totalDeleteCount = 0;
        try {
            Query hql = session.createQuery("DELETE FROM StudentResult sa WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId );
            totalDeleteCount = hql.executeUpdate();
            session.close();
            return totalDeleteCount;
        } catch (Exception e) {
            session.close();
            System.out.println("Error in CommonMapperImpl.deleteStudentResultForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            return totalDeleteCount;
        }
    }

    @Override
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int courseId, int batchId, int semesterId, String month) {
        List<StudentAttendance> salist = new ArrayList<>();
        try {
            String hql = " FROM StudentAttendance sa WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId + " AND sa.monthName='" + month + "' ";
            return (List<StudentAttendance>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.getStudentAttendanceForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            return salist;
        }
    }

    @Override
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int studentId) {
        List<StudentAttendance> salist = new ArrayList<>();
        try {
            String hql = " FROM StudentAttendance sa WHERE  sa.studentId.id=" + studentId;
            return (List<StudentAttendance>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.getStudentAttendanceForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            return salist;
        }
    }

    @Override     
    public List<StudentAttendance> getStudentAggregateAttendanceForGivenCriteria(int courseId, int batchId, int semesterId) {
        List<StudentAttendance> salist = new ArrayList<>();
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = " SELECT sa.studentId,sa.courseId,sa.batchId,sa.semesterId,SUM(sa.totalWorkingDaysInMonth),SUM(sa.totalPresentCount), "
                    + " SUM(sa.totalAbsentCount),SUM(sa.totalLeaveCount) FROM StudentAttendance sa "
                    + " WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId+" GROUP BY sa.studentId.id, sa.courseId.id,"
                    + " sa.batchId.id,sa.semesterId.id ";
            Query query = session.createQuery(hql);
            List<Object[]> listResult = query.list();
            if (listResult != null && listResult.size() > 0) {
                for (Object[] arr : listResult) {
                    StudentAttendance tsa = new StudentAttendance();
                    tsa.setStudentId((Student)arr[0]);
                    tsa.setCourseId((Course)arr[1]);
                    tsa.setBatchId((Batch) arr[2]);
                    tsa.setSemesterId((Semester) arr[3]);
                    tsa.setTotalWorkingDaysInMonth(Integer.valueOf(arr[4].toString()) );
                    tsa.setTotalPresentCount(Integer.valueOf(arr[5].toString()) );
                    tsa.setTotalAbsentCount(Integer.valueOf(arr[6].toString()) );
                    tsa.setTotalLeaveCount(Integer.valueOf(arr[7].toString()) );
                    salist.add(tsa);
                }
            }
            session.close();
            return salist;
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.getStudentAggregateAttendanceForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            session.close();
            return salist;
        }
    }
    
    @Override
    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId, int semesterId) {
        List<StudentResult> salist = new ArrayList<>();
        try {
            String hql = " FROM StudentResult sa WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId ;
            return (List<StudentResult>) hibernateTemplate.find(hql);
        } catch (DataAccessException e) {
            System.out.println("Error in CommonMapperImpl.getStudentResultForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            return salist;
        }
    }
    
    @Override
    public List<StudentResult> getStudentResultForGivenCriteria(int courseId, int batchId, int semesterId,int studentId) {
        List<StudentResult> salist = new ArrayList<>();
        try {
            String hql = " FROM StudentResult sa WHERE  sa.courseId.id=" + courseId
                    + " AND sa.batchId.id=" + batchId + " AND sa.semesterId.id=" + semesterId +" AND sa.studentId.id="+studentId;
            return (List<StudentResult>) hibernateTemplate.find(hql);
        } catch (DataAccessException e) {
            System.out.println("Error in CommonMapperImpl.getStudentResultForGivenCriteria : " + e.getMessage());
            e.printStackTrace();
            return salist;
        }
    }
}
