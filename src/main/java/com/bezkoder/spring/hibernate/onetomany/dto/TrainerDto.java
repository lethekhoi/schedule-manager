package com.bezkoder.spring.hibernate.onetomany.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrainerDto {
    String id;
    String name;

    public TrainerDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
