package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.dto.RequestTime;
import com.bezkoder.spring.hibernate.onetomany.model.Schedule;
import com.bezkoder.spring.hibernate.onetomany.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {


    Schedule addSchedule(Schedule schedule);

    Schedule saveSchedule(Schedule schedule);

    List<Schedule> findAll();

    List<Schedule> findAllByTime(RequestTime requestTime);

    List<Schedule> findAllByTrainerName(String trainerName);

    Optional<Schedule> findById(Long scheduleId);

    void deleteAll();

    void deleteById(Long scheduleId);

    boolean existsById(Long scheduleID);
}
