/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.dto;

import java.io.Serializable;

/**
 *
 * @author raj.shah
 */
public class TempStudentResult implements Serializable{ 
    
    private int courseId;
    private String courseName;
    private int batchId;
    private String batchName;
    private int semesterId;
    private String semesterName;
    private int studentId;
    private String studentName;
    private String studentResultJson;
    private String enrollmentNo;

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
     * @return the batchId
     */
    public int getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(int batchId) {
        this.batchId = batchId;
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

    /**
     * @return the semesterId
     */
    public int getSemesterId() {
        return semesterId;
    }

    /**
     * @param semesterId the semesterId to set
     */
    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * @return the semesterName
     */
    public String getSemesterName() {
        return semesterName;
    }

    /**
     * @param semesterName the semesterName to set
     */
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    /**
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
     * @return the studentResultJson
     */
    public String getStudentResultJson() {
        return studentResultJson;
    }

    /**
     * @param studentResultJson the studentResultJson to set
     */
    public void setStudentResultJson(String studentResultJson) {
        this.studentResultJson = studentResultJson;
    }

    /**
     * @return the enrollmentNo
     */
    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    /**
     * @param enrollmentNo the enrollmentNo to set
     */
    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }
    
    
    
}
