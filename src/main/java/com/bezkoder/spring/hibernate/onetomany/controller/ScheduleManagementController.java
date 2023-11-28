package com.bezkoder.spring.hibernate.onetomany.controller;

import com.bezkoder.spring.hibernate.onetomany.dto.RequestTime;
import com.bezkoder.spring.hibernate.onetomany.dto.ScheduleDTO;
import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Detail;
import com.bezkoder.spring.hibernate.onetomany.model.Schedule;
import com.bezkoder.spring.hibernate.onetomany.service.DetailService;
import com.bezkoder.spring.hibernate.onetomany.service.ScheduleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping()
public class ScheduleManagementController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    DetailService detailService;
    @Autowired
    private ModelMapper modelMapper;
    @Validated
    @PostMapping("/admin/schedules")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO, BindingResult bindingResult) throws ParseException, BindException {
        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        try {
            Schedule schedule = convertToEntity(scheduleDTO);
            //get all Detail startTime & endTime
            List<Detail> details = detailService.listAll();
            for (Detail detail : schedule.getListDetails()) {
                if (!checkValidTime(details, detail.getStartTime(), detail.getEndTime())) {
                    throw new RuntimeException("Have an schedule at this time");
                }
                detail.setSchedule(schedule);
            }
            Schedule _schedule = scheduleService.addSchedule(schedule);
            ScheduleDTO response = convertToDto(_schedule);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> listAllSchedule() {
        try {
            List<Schedule> schedules = new ArrayList<Schedule>();
            scheduleService.findAll().forEach(schedules::add);
            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }


    }

    @PostMapping("/user/schedules-by-time")
    public ResponseEntity<List<Schedule>> listScheduleByTime(@RequestBody RequestTime requestTime) throws ParseException {

        List<Schedule> schedules = new ArrayList<Schedule>();
        scheduleService.findAllByTime(requestTime).forEach(schedules::add);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);

    }

    @PostMapping("/user/schedules-by-trainer-name/{trainerName}")
    public ResponseEntity<List<Schedule>> listScheduleByTrainerName(@PathVariable("trainerName") String trainerName) throws ParseException {
        List<Schedule> schedules = new ArrayList<Schedule>();
        scheduleService.findAllByTrainerName(trainerName).forEach(schedules::add);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);

    }


    @PutMapping("admin/schedules/{id}")
    public ResponseEntity<Optional<Schedule>> updateSchedule(@PathVariable("id") long id, @RequestBody Schedule schedule) {
        Schedule _schedule = scheduleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        _schedule.setCourseName(schedule.getCourseName());
        _schedule.setTrainingType(schedule.getTrainingType());
        _schedule.setClassType(schedule.getClassType());
        _schedule.setRoomInfo(schedule.getRoomInfo());
        _schedule.setZoomLink(schedule.getZoomLink());
        Schedule scheduleUpdate = scheduleService.saveSchedule(_schedule);

        for (Detail detail : schedule.getListDetails()) {
            detail.setSchedule(scheduleUpdate);
            detailService.saveDetail(detail);
        }

        Optional<Schedule> resp = scheduleService.findById(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


//    @PutMapping("/tutorials/{id}")
//    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//        Tutorial _tutorial = tutorialRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
//
//        _tutorial.setTitle(tutorial.getTitle());
//        _tutorial.setDescription(tutorial.getDescription());
//        _tutorial.setPublished(tutorial.isPublished());
//
//        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//    }

//    @DeleteMapping("/admin/schedules")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        scheduleService.deleteAll();
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("/admin/schedules/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        scheduleService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Schedule convertToEntity(ScheduleDTO scheduleDTO) throws ParseException {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);

        return schedule;
    }

    private ScheduleDTO convertToDto(Schedule schedule) {
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);

        return scheduleDTO;
    }


    private boolean checkValidTime(List<Detail> details, Timestamp fromTime, Timestamp toTime) {
        for (Detail d : details) {
            if
            (
                    fromTime.after(d.getStartTime()) && fromTime.before(d.getEndTime()) ||
                            toTime.after(d.getStartTime()) && toTime.before(d.getEndTime()) ||
                            fromTime.equals(d.getStartTime()) ||
                            fromTime.equals(d.getEndTime()) ||
                            toTime.equals(d.getStartTime()) ||
                            toTime.equals(d.getEndTime())
            ) {
                return false;
            }
        }

        return true;
    }
}



