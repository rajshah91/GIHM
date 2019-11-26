/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author raj.shah
 */
@Entity
@Table(name = "student")
@XmlRootElement
public class Student implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<StudentResult> studentResultList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<StudentAttendance> studentAttendanceList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username", length=30)
    private String username;
    @Basic(optional = false)
    @Column(name = "enrollment_number", length=30)
    private String enrollmentNumber;
    @Basic(optional = false)
    @Column(name = "first_name", length=50)
    private String firstName;
    @Column(name = "middle_name", length=50)
    private String middleName;
    @Basic(optional = false)
    @Column(name = "last_name", length=50)
    private String lastName;
    @Basic(optional = false)
    @Column(name = "gender", length=10)
    private String gender;
    @Basic(optional = false)
    @Column(name = "aadhar_number", length=12)
    private String aadharNumber;
    @Basic(optional = false)
    @Column(name = "qualification", length=80)
    private String qualification;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "enrollment_date")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    @Column(name = "address", length=700)
    private String address;
    @Column(name = "mobile_number", length=20)
    private String mobileNumber;
    @Column(name = "email_id", length=80)
    private String emailId;
    @Column(name = "guardian_full_name", length=100)
    private String guardianFullName;
    @Column(name = "guardian_full_address", length=700)
    private String guardianFullAddress;
    @Column(name = "guardian_mobile_number", length=20)
    private String guardianMobileNumber;
    @Column(name = "blood_group", length=20)
    private String bloodGroup;
    @Column(name = "disability", length=10)
    private String disability;
    @Column(name = "disability_detail", length=200)
    private String disabilityDetail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private Double discount;
    
    @Column(name = "active")
    private String active;
    
    @CreationTimestamp
    @Column(name = "data_create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreateTime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<StudentFee> studentFeeList;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String enrollmentNumber, String firstName, String lastName, String gender, String qualification) {
        this.id = id;
        this.enrollmentNumber = enrollmentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.qualification = qualification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStudentResultList(List<StudentResult> studentResultList) {
        this.studentResultList = studentResultList;
    }

    public List<StudentResult> getStudentResultList() {
        return studentResultList;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGuardianFullName() {
        return guardianFullName;
    }

    public void setGuardianFullName(String guardianFullName) {
        this.guardianFullName = guardianFullName;
    }

    public String getGuardianFullAddress() {
        return guardianFullAddress;
    }

    public void setGuardianFullAddress(String guardianFullAddress) {
        this.guardianFullAddress = guardianFullAddress;
    }

    public String getGuardianMobileNumber() {
        return guardianMobileNumber;
    }

    public void setGuardianMobileNumber(String guardianMobileNumber) {
        this.guardianMobileNumber = guardianMobileNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDisabilityDetail() {
        return disabilityDetail;
    }

    public void setDisabilityDetail(String disabilityDetail) {
        this.disabilityDetail = disabilityDetail;
    }
    
    public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
    
    public String getAadharNumber() {
		return aadharNumber;
	}
    
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(Date dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
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

    public void setActive(String active) {
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    @XmlTransient
    public List<StudentFee> getStudentFeeList() {
        return studentFeeList;
    }

    public void setStudentFeeList(List<StudentFee> studentFeeList) {
        this.studentFeeList = studentFeeList;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.Student[ id=" + id + " ]";
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
