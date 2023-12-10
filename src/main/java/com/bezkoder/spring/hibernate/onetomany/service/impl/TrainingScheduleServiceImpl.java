package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.dto.FilterDto;
import com.bezkoder.spring.hibernate.onetomany.dto.TimeRange;
import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;
import com.bezkoder.spring.hibernate.onetomany.repository.TrainingScheduleRepository;
import com.bezkoder.spring.hibernate.onetomany.service.TrainingScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@SuppressWarnings("java:S1188")
@RequiredArgsConstructor
public class TrainingScheduleServiceImpl implements TrainingScheduleService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

    @Override
    public List<TrainingSchedules> listByFilter(FilterDto filterDto) {
        List<TrainingSchedules> resp = new ArrayList<>();
        switch (filterDto.getFilterBy()) {
            case "filterByDay":
                LocalDate date = LocalDate.parse(filterDto.getValue(), formatter);
                resp = trainingScheduleRepository.findByTrainingDate(date);
                break;
            case "filterByQuarter", "filterByMonth", "filterByYear":
                TimeRange t = getTimeRange(filterDto);
                System.out.println(t);
                resp = trainingScheduleRepository.findByTime(t.getFromTime(), t.getToTime());
                break;
            case "filterByClassType":
                resp = trainingScheduleRepository.findByClassType(filterDto.getValue());
                break;
        }


        return resp;
    }


    private TimeRange getTimeRange(FilterDto filterDto) {
        TimeRange t = new TimeRange();
        switch (filterDto.getFilterBy()) {
            case "filterByMonth":
                t = getTimeRangeByMonth(filterDto.getValue());
                break;
            case "filterByQuarter":
                t = getTimeRangeByQuarter(filterDto.getValue());
                break;
            case "filterByYear":
                t = getTimeRangeByYear(filterDto.getValue());
                break;
        }
        return t;
    }

    private TimeRange getTimeRangeByMonth(String month) {
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeRange t = new TimeRange();
        int m = Integer.parseInt(month);
        int dom = YearMonth.of(localDateTime.getYear(), m).atEndOfMonth().getDayOfMonth();
        t.setFromTime(LocalDate.of(localDateTime.getYear(), m, 1));
        t.setToTime(LocalDate.of(localDateTime.getYear(), m, dom));
        return t;
    }

    private TimeRange getTimeRangeByQuarter(String quarter) {
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeRange t = new TimeRange();
        int q = Integer.parseInt(quarter);
        int dom = YearMonth.of(localDateTime.getYear(), q * 3).atEndOfMonth().getDayOfMonth();
        t.setFromTime(LocalDate.of(localDateTime.getYear(), q * 3 - 2, 1));
        t.setToTime(LocalDate.of(localDateTime.getYear(), q * 3, dom));
        return t;
    }

    private TimeRange getTimeRangeByYear(String year) {
        TimeRange t = new TimeRange();
        int y = Integer.parseInt(year);
        t.setFromTime(LocalDate.of(y, 1, 1));
        t.setToTime(LocalDate.of(y, 12, 31));
        return t;
    }
}
