package com.bezkoder.spring.hibernate.onetomany.controller;


import com.bezkoder.spring.hibernate.onetomany.dto.TrainerDto;
import com.bezkoder.spring.hibernate.onetomany.model.ResponseObject;
import com.bezkoder.spring.hibernate.onetomany.model.Trainer;
import com.bezkoder.spring.hibernate.onetomany.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getTrainer() {
        ResponseObject object = new ResponseObject();
        List<TrainerDto> listTrainerName =trainerService.getAllTrainer();
        object.setData(listTrainerName);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

}
