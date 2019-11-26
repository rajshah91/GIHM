/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.dto;

/**
 *
 * @author raj.shah
 */
public class TempStudentFee {
    private int id;
    private String studentId;
    private String courseId;
    private String batchId;
    private Double totalFee;
    private Double discount;
    private Double remainingFee;
    private Double amountPaid;
    private String paymentMode;
    private String paymentDetail;
    private String feeCollectedBy;
    private String paymentDate;
    private String studentName;
    private String courseName;
    private String enrollmentNumber; 
    private String batchName;
    private String feeDescription;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return the totalFee
     */
    public Double getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee the totalFee to set
     */
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return the remainingFee
     */
    public Double getRemainingFee() {
        return remainingFee;
    }

    /**
     * @param remainingFee the remainingFee to set
     */
    public void setRemainingFee(Double remainingFee) {
        this.remainingFee = remainingFee;
    }

    /**
     * @return the amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(String paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    
    /**
     * @return the feeCollectedBy
     */
    public String getFeeCollectedBy() {
        return feeCollectedBy;
    }

    /**
     * @param feeCollectedBy the feeCollectedBy to set
     */
    public void setFeeCollectedBy(String feeCollectedBy) {
        this.feeCollectedBy = feeCollectedBy;
    }

    /**
     * @return the paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the enrollmentNumber
     */
    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    /**
     * @param enrollmentNumber the enrollmentNumber to set
     */
    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    /**
     * @return the batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * @param batchName the batchName to set
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getFeeDescription() {
        return feeDescription;
    }

    public void setFeeDescription(String feeDescription) {
        this.feeDescription = feeDescription;
    }
    
    
    
}
