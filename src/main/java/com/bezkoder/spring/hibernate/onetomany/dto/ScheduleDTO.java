package com.bezkoder.spring.hibernate.onetomany.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ScheduleDTO {
    @NotBlank
    String courseName;
    String link;
    String classType;
    String trainingType;
    String trainingDate;
    String trainingTime;
    String trainer;

}
