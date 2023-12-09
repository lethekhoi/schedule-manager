package com.bezkoder.spring.hibernate.onetomany.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "courses")
@Generated
public class Course {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "link")
    private String link;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TrainingSchedules> schedules = new ArrayList<>();
}