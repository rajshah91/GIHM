package org.javabase.apps.service;

import java.util.ArrayList;
import java.util.List;
import org.javabase.apps.dto.TempCourseSubject;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.mapper.CourseMapper;
import org.javabase.apps.utility.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseMapper courseMapper;

	@Override
	public List<Course> getAllCourse() {
		return courseMapper.getAllCourse();
	}
        
        @Override
	public List<Course> getAllActiveCourse() {
		return courseMapper.getAllActiveCourse();
	}
        

	@Override
	public boolean addCourse(Course course) {
		return courseMapper.addCourse(course);
	}
        
        @Override
        public String findCourseByName(String courseSearchStr) {
            List <Course> courseList=courseMapper.findCourseByName(courseSearchStr);
            List <String> strList=new ArrayList<>();
            if(courseList != null && courseList.size()>0){
                for(Course c : courseList){
                    strList.add(c.getCourseName());
                }
            }
            return MyUtils.getCommaSeparatedStringFromList(strList);
        }

        @Override
        public Course getCourseByName(String courseName) {
            return courseMapper.getCourseByName(courseName);
        }

        @Override
        public List<CourseFee> getAllCourseFee() {
            return courseMapper.getAllCourseFee();
        }
        
        @Override
        public List<CourseSubject> getAllCourseSubject() {
            return courseMapper.getAllCourseSubject();
        }

        @Override
        public CourseFee getFeeForCourse(Course course, Batch batch) {
            return this.getFeeForCourse(course.getId(),batch.getId());
        }
        
        @Override
        public CourseFee getFeeForCourse(int courseId, int batchId) {
            return courseMapper.getFeeForCourse(courseId,batchId);
        }
        
        @Override
        public List<TempCourseSubject> getAllCourseWithCommaSeparatedSubject() {
            return courseMapper.getAllCourseWithCommaSeparatedSubject();
        }

}
