/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raj.shah
 */
@Entity
@Table(name = "course")
@XmlRootElement
public class Course implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<StudentResult> studentResultList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<StudentAttendance> studentAttendanceList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "total_semester")
    private Integer totalSemester;
    @Column(name = "active")
    private String active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<StudentFee> studentFeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseSubject> courseSubjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseFee> courseFeeList;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTotalSemester() {
        return totalSemester;
    }

    public void setTotalSemester(Integer totalSemester) {
        this.totalSemester = totalSemester;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    
    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<StudentFee> getStudentFeeList() {
        return studentFeeList;
    }

    public void setStudentFeeList(List<StudentFee> studentFeeList) {
        this.studentFeeList = studentFeeList;
    }

    @XmlTransient
    public List<CourseSubject> getCourseSubjectList() {
        return courseSubjectList;
    }

    public void setCourseSubjectList(List<CourseSubject> courseSubjectList) {
        this.courseSubjectList = courseSubjectList;
    }

    @XmlTransient
    public List<CourseFee> getCourseFeeList() {
        return courseFeeList;
    }

    public void setCourseFeeList(List<CourseFee> courseFeeList) {
        this.courseFeeList = courseFeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.Course[ id=" + id + " ]";
    }

    @XmlTransient
    public List<StudentAttendance> getStudentAttendanceList() {
        return studentAttendanceList;
    }

    public void setStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
        this.studentAttendanceList = studentAttendanceList;
    }

    public List<StudentResult> getStudentResultCollection() {
        return studentResultList;
    }

    public void setStudentResultCollection(List<StudentResult> studentResultList) {
        this.studentResultList = studentResultList;
    }
    
}
