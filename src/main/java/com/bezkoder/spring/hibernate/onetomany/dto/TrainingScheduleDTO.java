package com.bezkoder.spring.hibernate.onetomany.dto;

import lombok.Data;

@Data
public class TrainingScheduleDTO {
    String id;
    String courseName;
    String link;
    String classType;
    String trainingType;
    String trainingDate;
    String trainingTime;
    String trainer;
}
