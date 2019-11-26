package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.dto.TempCourseSubject;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;

public interface CourseMapper {

    public List<Course> getAllCourse();
    
    public List<Course> getAllActiveCourse();
    
    public List<Course> findCourseByName(String searchCourseName);

    public boolean addCourse(Course course);
    
    public Course getCourseByName(String courseName);

    public List<CourseFee> getAllCourseFee();
    
    public List<CourseSubject> getAllCourseSubject();

    public CourseFee getFeeForCourse(int courseId, int batchId);

    public List<TempCourseSubject> getAllCourseWithCommaSeparatedSubject();
}
