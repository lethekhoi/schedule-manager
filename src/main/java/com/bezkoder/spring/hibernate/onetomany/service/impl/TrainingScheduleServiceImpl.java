package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;
import com.bezkoder.spring.hibernate.onetomany.repository.TrainingScheduleRepository;
import com.bezkoder.spring.hibernate.onetomany.service.TrainingScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@SuppressWarnings("java:S1188")
@RequiredArgsConstructor
public class TrainingScheduleServiceImpl implements TrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Override
    public TrainingSchedules addSchedule(TrainingSchedules trainingSchedules) {
        return trainingScheduleRepository.save(trainingSchedules);
    }

    @Override
    public List<TrainingSchedules> listAllSchedule() {
        return trainingScheduleRepository.findAll();
    }

    @Override
    public void deleteScheduleById(int id) {
        trainingScheduleRepository.deleteById(id);
    }

    @Override
    public TrainingSchedules getTrainingScheduleById(int id) {
       return trainingScheduleRepository.getById(id);
    }
}
