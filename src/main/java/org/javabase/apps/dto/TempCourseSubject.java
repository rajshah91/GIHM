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
public class TempCourseSubject {

    private String courseName;
    private Integer semester;
    private String batchName;
    private String subName;

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

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public void setSubjectName(String subName) {
        this.subName = subName;
    }

    public String getSubName() {
        return subName;
    }

}
