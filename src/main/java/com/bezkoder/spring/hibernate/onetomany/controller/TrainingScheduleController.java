package com.bezkoder.spring.hibernate.onetomany.controller;


import com.bezkoder.spring.hibernate.onetomany.dto.FilterDto;
import com.bezkoder.spring.hibernate.onetomany.dto.ScheduleDTO;
import com.bezkoder.spring.hibernate.onetomany.dto.TrainerDto;
import com.bezkoder.spring.hibernate.onetomany.dto.TrainingScheduleDTO;
import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.onetomany.model.Course;
import com.bezkoder.spring.hibernate.onetomany.model.ResponseObject;
import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;
import com.bezkoder.spring.hibernate.onetomany.repository.CourseRepository;
import com.bezkoder.spring.hibernate.onetomany.service.TrainerService;
import com.bezkoder.spring.hibernate.onetomany.service.TrainingScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/schedule")
public class TrainingScheduleController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TrainingScheduleService trainingScheduleService;
    @Autowired
    private TrainerService trainerService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
    @Autowired
    private CourseRepository courseRepository;
    private List<TrainerDto> listTrainer = new ArrayList<>();

    @PostMapping()
    public ResponseEntity<ResponseObject> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            Course course = courseRepository.findByCourseName(scheduleDTO.getCourseName());
            List<TrainingSchedules> trainingSchedules = new ArrayList<>();
            if (course == null) {
                course = new Course();
                course.setCourseName(scheduleDTO.getCourseName());
            } else {
                trainingSchedules = course.getSchedules();
            }
            TrainingSchedules trainingSchedule = convertToEntity(scheduleDTO);
            trainingSchedule.setCourse(course);
            trainingSchedules.add(trainingSchedule);
            course.setSchedules(trainingSchedules);
            courseRepository.save(course);
            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }

    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseObject> filterSchedule(@RequestBody FilterDto filterDto) {
        try {

            listTrainer = trainerService.getAllTrainer();
            List<TrainingSchedules> trainingSchedules = trainingScheduleService.listByFilter(filterDto);


            List<TrainingScheduleDTO> resp = new ArrayList<>();
            trainingSchedules.forEach((t) -> {
                TrainingScheduleDTO dto = convertToTrainingScheduleDTO(t);
                resp.add(dto);
            });

            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);
            object.setData(resp);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }

    }

    @GetMapping()
    public ResponseEntity<ResponseObject> listAllSchedule() {
        try {


            listTrainer = trainerService.getAllTrainer();
            List<TrainingSchedules> trainingSchedules = trainingScheduleService.listAllSchedule();
            List<TrainingScheduleDTO> resp = new ArrayList<>();
            trainingSchedules.forEach((t) -> {
                TrainingScheduleDTO dto = convertToTrainingScheduleDTO(t);
                resp.add(dto);
            });
            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);
            object.setData(resp);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getScheduleById(@PathVariable("id") int id) {
        try {
            TrainingSchedules trainingSchedule = trainingScheduleService.getTrainingScheduleById(id);

            TrainingScheduleDTO dto = convertToTrainingScheduleDTO(trainingSchedule);

            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);
            object.setData(dto);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get schedule: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> editScheduleById(@PathVariable("id") int id,
                                                           @RequestBody ScheduleDTO scheduleDTO) {
        try {

            TrainingSchedules t = trainingScheduleService.getTrainingScheduleById(id);
            if (t == null) {
                throw new ResourceNotFoundException("not found training schedule");
            }
            t = convertUpdateTrainingSchedule(scheduleDTO, t);
            Course c = t.getCourse();
            c.setCourseName(scheduleDTO.getCourseName());
            c.setLink(scheduleDTO.getLink());
            c.getSchedules().add(t);

            courseRepository.save(c);

            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);

            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get schedule: " + e.getMessage());
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteSchedule(@PathVariable("id") int id) {
        try {
            trainingScheduleService.deleteScheduleById(id);
            ResponseObject object = new ResponseObject();
            object.setMessage("Success");
            object.setStatus(200);

            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create schedule: " + e.getMessage());
        }

    }


    private TrainingSchedules convertToEntity(ScheduleDTO scheduleDTO) {
        TrainingSchedules trainingSchedules = modelMapper.map(scheduleDTO, TrainingSchedules.class);

        LocalDate date = LocalDate.parse(scheduleDTO.getTrainingDate(), formatter);
        trainingSchedules.setTrainingDate(date);

        LocalTime time = LocalTime.parse(scheduleDTO.getTrainingTime(), formatter1);
        trainingSchedules.setTrainingTime(time);

        return trainingSchedules;
    }

    private TrainingSchedules convertUpdateTrainingSchedule(ScheduleDTO dto, TrainingSchedules t) {
        t.setTrainingType(dto.getTrainingType());
        t.setClassType(dto.getClassType());
        LocalDate date = LocalDate.parse(dto.getTrainingDate(), formatter);
        t.setTrainingDate(date);
        LocalTime time = LocalTime.parse(dto.getTrainingTime(), formatter1);
        t.setTrainingTime(time);

        return t;
    }


    private TrainingScheduleDTO convertToTrainingScheduleDTO(TrainingSchedules trainingSchedules) {
        TrainingScheduleDTO trainingScheduleDTO = new TrainingScheduleDTO();
        trainingScheduleDTO.setId(trainingSchedules.getId() + "");
        trainingScheduleDTO.setCourseName(trainingSchedules.getCourse().getCourseName());
        if (trainingSchedules.getCourse().getLink() == null) {
            trainingScheduleDTO.setLink("");
        } else {
            trainingScheduleDTO.setLink(trainingSchedules.getCourse().getLink());
        }

        trainingScheduleDTO.setClassType(trainingSchedules.getClassType());
        trainingScheduleDTO.setTrainingType(trainingSchedules.getTrainingType());
        trainingScheduleDTO.setTrainingDate(trainingSchedules.getTrainingDate().toString());
        trainingScheduleDTO.setTrainingTime(trainingSchedules.getTrainingTime().toString());


        trainingScheduleDTO.setTrainer(resolveTrainerName(trainingSchedules.getTrainer()));


        return trainingScheduleDTO;
    }

    private String resolveTrainerName(String id) {
        for (TrainerDto t : listTrainer) {
            if (t.getId().equals(id)) {
                return t.getName();
            }
        }
        return "";
    }

}
