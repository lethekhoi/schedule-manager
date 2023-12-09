package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.model.Trainer;
import com.bezkoder.spring.hibernate.onetomany.repository.TrainerRepository;
import com.bezkoder.spring.hibernate.onetomany.service.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@SuppressWarnings("java:S1188")
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    TrainerRepository trainerRepository;

    @Override
    public List<String> getAllTrainer() {
        List<Trainer> trainers = trainerRepository.findAll();

        List<String> listTrainerName = new ArrayList<>();
        trainers.forEach(trainer -> {
            listTrainerName.add(trainer.getName());
        });

        return listTrainerName;
    }
}
