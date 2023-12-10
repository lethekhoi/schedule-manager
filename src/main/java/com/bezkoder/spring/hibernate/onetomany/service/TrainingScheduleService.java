package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.FilterDto;
import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;

import java.util.List;

public interface TrainingScheduleService {

    TrainingSchedules addSchedule(TrainingSchedules schedule);
    List<TrainingSchedules> listAllSchedule();
    void deleteScheduleById(int id);

    TrainingSchedules getTrainingScheduleById(int id);

    List<TrainingSchedules> listByFilter(FilterDto filterDto);


}
