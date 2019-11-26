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

public class TempCourse {
//   {"courseName":"WWWW","semester":"3","fees":"222","subjectIds":["1", "2"]}
   
   private String courseName;
   private Integer semester;
   private Integer totalSemester;
   private Double fees;
   private String[] subjectIds;
   private String commaSeparatedSubjectNames="";
   private String[] subjectNames;
   private String courseId;
   private String batchId;
   private String semesterId;
   private String batchName;
   private String subName;
   private String active;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getSemester() {
        return semester;
    }
    
    public void setTotalSemester(Integer totalSemester) {
        this.totalSemester = totalSemester;
    }

    public Integer getTotalSemester() {
        return this.totalSemester;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
    
    public Double getFees() {
        return fees;
    }

    public void setSubjectIds(String[] subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String[] getSubjectIds() {
        return this.subjectIds;
    }

    public void setSubjectNames(String[] subjectNames) {
        this.subjectNames = subjectNames;
    }

    public String[] getSubjectNames() {
        return subjectNames;
    }

    public void setCommaSeparatedSubjectNames() {
        if(this.subjectNames != null && this.subjectNames.length >0 ){
            this.commaSeparatedSubjectNames=this.commaSeparatedSubjectNames.join(",", subjectNames);
        }
    }
    
    public String getCommaSeparatedSubjectNames() {
        return this.commaSeparatedSubjectNames;
    }

    public String getCourseId() {
        return courseId;
    }

   public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

   public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public void setSubjectName(String subName) {
        this.subName=subName;
    }

    public String getSubName() {
        return subName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    
}
