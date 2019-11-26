package org.javabase.apps.mapper;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.javabase.apps.dto.TempCourseSubject;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class CourseMapperImpl implements CourseMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Course> getAllCourse() {
        try {
            String hql = "FROM Course";
            return (List<Course>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Course> getAllActiveCourse() {
        try {
            String hql = "FROM Course WHERE active ='true'";
            return (List<Course>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean addCourse(Course course) {
        try {
            hibernateTemplate.save(course);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Course> findCourseByName(String searchCourseName) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query query = session.createQuery("FROM Student WHERE studentName like concat('%',:studentName,'%')");
//        query.setParameter("studentName", likeStudentName);
//        List&lt;Student&gt; list = query.list();
//        if(list.size()==0)
//            return null;
//        return list;
        try {
            String hql = "FROM Course c WHERE c.courseName like '%" + searchCourseName + "%'";
            return (List<Course>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
//            String hql2 = "FROM Course c WHERE c.courseName like :courseName ";
//            return (List<Course>) hibernateTemplate.findByNamedParam(hql2, "courseName", '%' + searchCourseName + '%');
    }

    @Override
    public Course getCourseByName(String courseName) {
        try {
            String hql = "FROM Course c WHERE c.courseName = '" + courseName + "'";
            return (Course) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CourseFee> getAllCourseFee() {
        try {
            String hql = "FROM CourseFee";
            return (List<CourseFee>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CourseSubject> getAllCourseSubject() {
        try {
            String hql = "FROM CourseSubject";
            return (List<CourseSubject>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public CourseFee getFeeForCourse(int courseId, int batchId) {
        try {
            String hql = "FROM CourseFee c WHERE c.courseId.id = '" + courseId + "' AND c.batchId.id= '" + batchId + "'";
            CourseFee cf = (CourseFee) hibernateTemplate.find(hql).get(0);
            return cf;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<TempCourseSubject> getAllCourseWithCommaSeparatedSubject() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        List<TempCourseSubject> courseSubList = new ArrayList<>();
        try {
//            String hql = "SELECT cs.courseId.courseName,cs.batchId.batch,cs.semesterId.semester,GROUP_CONCAT(cs.subjectId.subName) FROM CourseSubject cs ";
//            hql += " GROUP BY cs.courseId.id,cs.batchId.id,cs.semesterId.id,cs.subjectId.subId";

            String sql = " SELECT c.course_name,b.batch,s.semester,GROUP_CONCAT(sub.sub_name) FROM course_subject cs INNER JOIN batch b ON b.id=cs.batch_id \n"
                    + " INNER JOIN semester s ON s.id=cs.semester_id INNER JOIN course c ON c.id=cs.course_id INNER JOIN subject sub ON sub.sub_id=cs.subject_id\n"
                    + " GROUP BY cs.batch_id,cs.course_id,cs.semester_id";

            Query query = session.createSQLQuery(sql);
            List<Object[]> listResult = query.list();
            if (listResult != null && listResult.size() > 0) {
                for (Object[] arr : listResult) {
                    TempCourseSubject tcs = new TempCourseSubject();
                    tcs.setCourseName(arr[0].toString());
                    tcs.setBatchName(arr[1].toString());
                    tcs.setSemester((Integer) arr[2]);
                    tcs.setSubjectName(arr[3].toString());
                    courseSubList.add(tcs);
                }
            }
            session.close();
            return courseSubList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return null;
        }
    }

}
