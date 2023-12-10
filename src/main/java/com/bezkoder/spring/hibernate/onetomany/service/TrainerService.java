package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.TrainerDto;
import com.bezkoder.spring.hibernate.onetomany.model.Trainer;

import java.util.List;

public interface TrainerService {
    public List<TrainerDto> getAllTrainer();
}
