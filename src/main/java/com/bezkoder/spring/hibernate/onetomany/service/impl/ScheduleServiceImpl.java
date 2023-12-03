package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.dto.RequestSearch;
import com.bezkoder.spring.hibernate.onetomany.dto.RequestTime;
import com.bezkoder.spring.hibernate.onetomany.dto.TimeRange;
import com.bezkoder.spring.hibernate.onetomany.model.Schedule;
import com.bezkoder.spring.hibernate.onetomany.repository.ScheduleRepository;
import com.bezkoder.spring.hibernate.onetomany.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bezkoder.spring.hibernate.onetomany.dto.TimeFrame.all;


@Service
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    ScheduleRepository repository;


    @Override
    public Schedule addSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Schedule> findAllByTime(RequestTime requestTime) {
        TimeRange timeRange = TimeRange.CreateTimeRange(requestTime.getTimeFrame());
        System.out.println("from" + timeRange.getFromTime());
        System.out.println("to" + timeRange.getToTime());
        return repository.findByTime(timeRange.getFromTime(), timeRange.getToTime());

    }

    @Override
    public List<Schedule> search(RequestSearch requestSearch) {
        List<Schedule> response = new ArrayList<>();
        if (requestSearch.getTimeFrame() != all) {
            TimeRange timeRange = TimeRange.CreateTimeRange(requestSearch.getTimeFrame());
            System.out.println("from: " + timeRange.getFromTime());
            System.out.println("to: " + timeRange.getToTime());
            response = repository.search(requestSearch.getCourseName(), timeRange.getFromTime(), timeRange.getToTime());
        } else {
            response = repository.findByCourseName(requestSearch.getCourseName());
        }
        return response;
    }



    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        return repository.findById(scheduleId);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(Long scheduleId) {
        repository.deleteById(scheduleId);
    }

    @Override
    public boolean existsById(Long scheduleID) {
        return repository.existsById(scheduleID);
    }


}
