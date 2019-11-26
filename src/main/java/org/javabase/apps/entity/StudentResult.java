/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author JKRN
 */
@Entity
@Table(name = "student_result")
@NamedQueries({
    @NamedQuery(name = "StudentResult.findAll", query = "SELECT s FROM StudentResult s")})
public class StudentResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "student_result_json")
    private String studentResultJson;
    
    @UpdateTimestamp
    @Column(name = "data_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpdateTime;
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Batch batchId;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Semester semesterId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;

    public StudentResult() {
    }

    public StudentResult(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentResultJson() {
        return studentResultJson;
    }

    public void setStudentResultJson(String studentResultJson) {
        this.studentResultJson = studentResultJson;
    }

    public Date getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(Date dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    public Batch getBatchId() {
        return batchId;
    }

    public void setBatchId(Batch batchId) {
        this.batchId = batchId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof StudentResult)) {
            return false;
        }
        StudentResult other = (StudentResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.StudentResult[ id=" + id + " ]";
    }
    
}
