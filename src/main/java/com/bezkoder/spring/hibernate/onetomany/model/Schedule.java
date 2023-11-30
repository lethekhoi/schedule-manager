package com.bezkoder.spring.hibernate.onetomany.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private String trainingType;
    private String classType;
    private String classInfo;

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<Detail> listDetails = new ArrayList<>();

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


    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public List<Detail> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<Detail> listEmployee) {
        this.listDetails = listEmployee;
    }

    public Schedule(long id, String name, String trainingType, String classType, String classInfo, List<Detail> listDetails) {
        this.id = id;
        this.courseName = name;
        this.trainingType = trainingType;
        this.classType = classType;
        this.classInfo = classInfo;
        this.listDetails = listDetails;
    }

    public Schedule() {
    }
}
