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
@Table(name = "semester")
@XmlRootElement
public class Semester implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private List<StudentResult> studentResultList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private List<StudentAttendance> studentAttendanceList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "semester")
    private Integer semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private List<CourseSubject> courseSubjectList;

    public Semester() {
    }

    public Semester(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @XmlTransient
    public List<CourseSubject> getCourseSubjectList() {
        return courseSubjectList;
    }

    public void setCourseSubjectList(List<CourseSubject> courseSubjectList) {
        this.courseSubjectList = courseSubjectList;
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
        if (!(object instanceof Semester)) {
            return false;
        }
        Semester other = (Semester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.Semester[ id=" + id + " ]";
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
