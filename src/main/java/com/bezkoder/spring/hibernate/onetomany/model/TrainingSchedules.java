package com.bezkoder.spring.hibernate.onetomany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "training_schedules")
public class TrainingSchedules {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "training_type")
    private String trainingType;
    @Column(name = "class_type")
    private String classType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "create_by")
    private String createBy;
    @Column(name = "training_date")
    private LocalDate trainingDate;
    @Column(name = "training_time")
    private LocalTime trainingTime;
    @Column(name = "trainer")
    private String trainer;
}