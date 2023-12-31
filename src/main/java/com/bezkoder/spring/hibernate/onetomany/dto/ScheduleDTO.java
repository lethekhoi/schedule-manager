package com.bezkoder.spring.hibernate.onetomany.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ScheduleDTO  {
    private long id;
    @NotEmpty(message = "Training Type not be empty")
    private String trainingType;
    @NotEmpty(message = "Course name may not be empty")
    private String courseName;
    @NotEmpty(message = "Class type may not be empty")
    private String classType;
    private String classInfo;

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    private List<DetailDTO> listDetails;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public List<DetailDTO> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<DetailDTO> listDetails) {
        this.listDetails = listDetails;
    }
}
