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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author raj.shah
 */
@Entity
@Table(name = "student_attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAttendance.findAll", query = "SELECT s FROM StudentAttendance s")
    , @NamedQuery(name = "StudentAttendance.findById", query = "SELECT s FROM StudentAttendance s WHERE s.id = :id")
    , @NamedQuery(name = "StudentAttendance.findByMonthName", query = "SELECT s FROM StudentAttendance s WHERE s.monthName = :monthName")
    , @NamedQuery(name = "StudentAttendance.findByA1", query = "SELECT s FROM StudentAttendance s WHERE s.a1 = :a1")
    , @NamedQuery(name = "StudentAttendance.findByA2", query = "SELECT s FROM StudentAttendance s WHERE s.a2 = :a2")
    , @NamedQuery(name = "StudentAttendance.findByA3", query = "SELECT s FROM StudentAttendance s WHERE s.a3 = :a3")
    , @NamedQuery(name = "StudentAttendance.findByA4", query = "SELECT s FROM StudentAttendance s WHERE s.a4 = :a4")
    , @NamedQuery(name = "StudentAttendance.findByA5", query = "SELECT s FROM StudentAttendance s WHERE s.a5 = :a5")
    , @NamedQuery(name = "StudentAttendance.findByA6", query = "SELECT s FROM StudentAttendance s WHERE s.a6 = :a6")
    , @NamedQuery(name = "StudentAttendance.findByA7", query = "SELECT s FROM StudentAttendance s WHERE s.a7 = :a7")
    , @NamedQuery(name = "StudentAttendance.findByA8", query = "SELECT s FROM StudentAttendance s WHERE s.a8 = :a8")
    , @NamedQuery(name = "StudentAttendance.findByA9", query = "SELECT s FROM StudentAttendance s WHERE s.a9 = :a9")
    , @NamedQuery(name = "StudentAttendance.findByA10", query = "SELECT s FROM StudentAttendance s WHERE s.a10 = :a10")
    , @NamedQuery(name = "StudentAttendance.findByA11", query = "SELECT s FROM StudentAttendance s WHERE s.a11 = :a11")
    , @NamedQuery(name = "StudentAttendance.findByA12", query = "SELECT s FROM StudentAttendance s WHERE s.a12 = :a12")
    , @NamedQuery(name = "StudentAttendance.findByA13", query = "SELECT s FROM StudentAttendance s WHERE s.a13 = :a13")
    , @NamedQuery(name = "StudentAttendance.findByA14", query = "SELECT s FROM StudentAttendance s WHERE s.a14 = :a14")
    , @NamedQuery(name = "StudentAttendance.findByA15", query = "SELECT s FROM StudentAttendance s WHERE s.a15 = :a15")
    , @NamedQuery(name = "StudentAttendance.findByA16", query = "SELECT s FROM StudentAttendance s WHERE s.a16 = :a16")
    , @NamedQuery(name = "StudentAttendance.findByA17", query = "SELECT s FROM StudentAttendance s WHERE s.a17 = :a17")
    , @NamedQuery(name = "StudentAttendance.findByA18", query = "SELECT s FROM StudentAttendance s WHERE s.a18 = :a18")
    , @NamedQuery(name = "StudentAttendance.findByA19", query = "SELECT s FROM StudentAttendance s WHERE s.a19 = :a19")
    , @NamedQuery(name = "StudentAttendance.findByA20", query = "SELECT s FROM StudentAttendance s WHERE s.a20 = :a20")
    , @NamedQuery(name = "StudentAttendance.findByA21", query = "SELECT s FROM StudentAttendance s WHERE s.a21 = :a21")
    , @NamedQuery(name = "StudentAttendance.findByA22", query = "SELECT s FROM StudentAttendance s WHERE s.a22 = :a22")
    , @NamedQuery(name = "StudentAttendance.findByA23", query = "SELECT s FROM StudentAttendance s WHERE s.a23 = :a23")
    , @NamedQuery(name = "StudentAttendance.findByA24", query = "SELECT s FROM StudentAttendance s WHERE s.a24 = :a24")
    , @NamedQuery(name = "StudentAttendance.findByA25", query = "SELECT s FROM StudentAttendance s WHERE s.a25 = :a25")
    , @NamedQuery(name = "StudentAttendance.findByA26", query = "SELECT s FROM StudentAttendance s WHERE s.a26 = :a26")
    , @NamedQuery(name = "StudentAttendance.findByA27", query = "SELECT s FROM StudentAttendance s WHERE s.a27 = :a27")
    , @NamedQuery(name = "StudentAttendance.findByA28", query = "SELECT s FROM StudentAttendance s WHERE s.a28 = :a28")
    , @NamedQuery(name = "StudentAttendance.findByA29", query = "SELECT s FROM StudentAttendance s WHERE s.a29 = :a29")
    , @NamedQuery(name = "StudentAttendance.findByA30", query = "SELECT s FROM StudentAttendance s WHERE s.a30 = :a30")
    , @NamedQuery(name = "StudentAttendance.findByA31", query = "SELECT s FROM StudentAttendance s WHERE s.a31 = :a31")
    , @NamedQuery(name = "StudentAttendance.findByTotalDaysInMonth", query = "SELECT s FROM StudentAttendance s WHERE s.totalDaysInMonth = :totalDaysInMonth")
    , @NamedQuery(name = "StudentAttendance.findByTotalHolidaysInMonth", query = "SELECT s FROM StudentAttendance s WHERE s.totalHolidaysInMonth = :totalHolidaysInMonth")
    , @NamedQuery(name = "StudentAttendance.findByTotalWorkingDaysInMonth", query = "SELECT s FROM StudentAttendance s WHERE s.totalWorkingDaysInMonth = :totalWorkingDaysInMonth")
    , @NamedQuery(name = "StudentAttendance.findByTotalPresentCount", query = "SELECT s FROM StudentAttendance s WHERE s.totalPresentCount = :totalPresentCount")
    , @NamedQuery(name = "StudentAttendance.findByTotalAbsentCount", query = "SELECT s FROM StudentAttendance s WHERE s.totalAbsentCount = :totalAbsentCount")
    , @NamedQuery(name = "StudentAttendance.findByTotalLeaveCount", query = "SELECT s FROM StudentAttendance s WHERE s.totalLeaveCount = :totalLeaveCount")
    , @NamedQuery(name = "StudentAttendance.findByDataUpdateTime", query = "SELECT s FROM StudentAttendance s WHERE s.dataUpdateTime = :dataUpdateTime")})
public class StudentAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "month_name", length=20)
    private String monthName;
    @Column(name = "a1", length=3)
    private String a1;
    @Column(name = "a2", length=3)
    private String a2;
    @Column(name = "a3", length=3)
    private String a3;
    @Column(name = "a4", length=3)
    private String a4;
    @Column(name = "a5", length=3)
    private String a5;
    @Column(name = "a6", length=3)
    private String a6;
    @Column(name = "a7", length=3)
    private String a7;
    @Column(name = "a8", length=3)
    private String a8;
    @Column(name = "a9", length=3)
    private String a9;
    @Column(name = "a10", length=3)
    private String a10;
    @Column(name = "a11", length=3)
    private String a11;
    @Column(name = "a12", length=3)
    private String a12;
    @Column(name = "a13", length=3)
    private String a13;
    @Column(name = "a14", length=3)
    private String a14;
    @Column(name = "a15", length=3)
    private String a15;
    @Column(name = "a16", length=3)
    private String a16;
    @Column(name = "a17", length=3)
    private String a17;
    @Column(name = "a18", length=3)
    private String a18;
    @Column(name = "a19", length=3)
    private String a19;
    @Column(name = "a20", length=3)
    private String a20;
    @Column(name = "a21", length=3)
    private String a21;
    @Column(name = "a22", length=3)
    private String a22;
    @Column(name = "a23", length=3)
    private String a23;
    @Column(name = "a24", length=3)
    private String a24;
    @Column(name = "a25", length=3)
    private String a25;
    @Column(name = "a26", length=3)
    private String a26;
    @Column(name = "a27", length=3)
    private String a27;
    @Column(name = "a28", length=3)
    private String a28;
    @Column(name = "a29", length=3)
    private String a29;
    @Column(name = "a30", length=3)
    private String a30;
    @Column(name = "a31", length=3)
    private String a31;
    @Column(name = "total_days_in_month")
    private Integer totalDaysInMonth;
    @Column(name = "total_holidays_in_month")
    private Integer totalHolidaysInMonth;
    @Column(name = "total_working_days_in_month")
    private Integer totalWorkingDaysInMonth;
    @Column(name = "total_present_count")
    private Integer totalPresentCount;
    @Column(name = "total_absent_count")
    private Integer totalAbsentCount;
    @Column(name = "total_leave_count")
    private Integer totalLeaveCount;
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

    public StudentAttendance() {
    }

    public StudentAttendance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }


    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public String getA6() {
        return a6;
    }

    public void setA6(String a6) {
        this.a6 = a6;
    }

    public String getA7() {
        return a7;
    }

    public void setA7(String a7) {
        this.a7 = a7;
    }

    public String getA8() {
        return a8;
    }

    public void setA8(String a8) {
        this.a8 = a8;
    }

    public String getA9() {
        return a9;
    }

    public void setA9(String a9) {
        this.a9 = a9;
    }

    public String getA10() {
        return a10;
    }

    public void setA10(String a10) {
        this.a10 = a10;
    }

    public String getA11() {
        return a11;
    }

    public void setA11(String a11) {
        this.a11 = a11;
    }

    public String getA12() {
        return a12;
    }

    public void setA12(String a12) {
        this.a12 = a12;
    }

    public String getA13() {
        return a13;
    }

    public void setA13(String a13) {
        this.a13 = a13;
    }

    public String getA14() {
        return a14;
    }

    public void setA14(String a14) {
        this.a14 = a14;
    }

    public String getA15() {
        return a15;
    }

    public void setA15(String a15) {
        this.a15 = a15;
    }

    public String getA16() {
        return a16;
    }

    public void setA16(String a16) {
        this.a16 = a16;
    }

    public String getA17() {
        return a17;
    }

    public void setA17(String a17) {
        this.a17 = a17;
    }

    public String getA18() {
        return a18;
    }

    public void setA18(String a18) {
        this.a18 = a18;
    }

    public String getA19() {
        return a19;
    }

    public void setA19(String a19) {
        this.a19 = a19;
    }

    public String getA20() {
        return a20;
    }

    public void setA20(String a20) {
        this.a20 = a20;
    }

    public String getA21() {
        return a21;
    }

    public void setA21(String a21) {
        this.a21 = a21;
    }

    public String getA22() {
        return a22;
    }

    public void setA22(String a22) {
        this.a22 = a22;
    }

    public String getA23() {
        return a23;
    }

    public void setA23(String a23) {
        this.a23 = a23;
    }

    public String getA24() {
        return a24;
    }

    public void setA24(String a24) {
        this.a24 = a24;
    }

    public String getA25() {
        return a25;
    }

    public void setA25(String a25) {
        this.a25 = a25;
    }

    public String getA26() {
        return a26;
    }

    public void setA26(String a26) {
        this.a26 = a26;
    }

    public String getA27() {
        return a27;
    }

    public void setA27(String a27) {
        this.a27 = a27;
    }

    public String getA28() {
        return a28;
    }

    public void setA28(String a28) {
        this.a28 = a28;
    }

    public String getA29() {
        return a29;
    }

    public void setA29(String a29) {
        this.a29 = a29;
    }

    public String getA30() {
        return a30;
    }

    public void setA30(String a30) {
        this.a30 = a30;
    }

    public String getA31() {
        return a31;
    }

    public void setA31(String a31) {
        this.a31 = a31;
    }

    public Integer getTotalDaysInMonth() {
        return totalDaysInMonth;
    }

    public void setTotalDaysInMonth(Integer totalDaysInMonth) {
        this.totalDaysInMonth = totalDaysInMonth;
    }

    public Integer getTotalHolidaysInMonth() {
        return totalHolidaysInMonth;
    }

    public void setTotalHolidaysInMonth(Integer totalHolidaysInMonth) {
        this.totalHolidaysInMonth = totalHolidaysInMonth;
    }

    public Integer getTotalWorkingDaysInMonth() {
        return totalWorkingDaysInMonth;
    }

    public void setTotalWorkingDaysInMonth(Integer totalWorkingDaysInMonth) {
        this.totalWorkingDaysInMonth = totalWorkingDaysInMonth;
    }

    public Integer getTotalPresentCount() {
        return totalPresentCount;
    }

    public void setTotalPresentCount(Integer totalPresentCount) {
        this.totalPresentCount = totalPresentCount;
    }

    public Integer getTotalAbsentCount() {
        return totalAbsentCount;
    }

    public void setTotalAbsentCount(Integer totalAbsentCount) {
        this.totalAbsentCount = totalAbsentCount;
    }

    public Integer getTotalLeaveCount() {
        return totalLeaveCount;
    }

    public void setTotalLeaveCount(Integer totalLeaveCount) {
        this.totalLeaveCount = totalLeaveCount;
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
        if (!(object instanceof StudentAttendance)) {
            return false;
        }
        StudentAttendance other = (StudentAttendance) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.StudentAttendance[ id=" + id + " ]";
    }
}
