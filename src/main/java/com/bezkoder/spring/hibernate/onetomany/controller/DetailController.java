package com.bezkoder.spring.hibernate.onetomany.controller;

import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;

import com.bezkoder.spring.hibernate.onetomany.model.Detail;
import com.bezkoder.spring.hibernate.onetomany.service.DetailService;
import com.bezkoder.spring.hibernate.onetomany.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DetailController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    DetailService detailService;


    @GetMapping("/user/schedules/{scheduleId}/details")
    public ResponseEntity<List<Detail>> getAllCommentsByTutorialId(@PathVariable(value = "scheduleId") Long scheduleId) {
        if (!scheduleService.existsById(scheduleId)) {
            throw new ResourceNotFoundException("Not found schedule with id = " + scheduleId);
        }

        List<Detail> details = detailService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/admin/schedules/{schedileId}/details")
    public ResponseEntity<Detail> createDetails(@PathVariable(value = "scheduleId") Long scheduleId,
                                                @RequestBody Detail detailRequest) {
        Detail detail = scheduleService.findById(scheduleId).map(schedule -> {
            detailRequest.setSchedule(schedule);
            return detailService.addDetail(detailRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Schedule with id = " + scheduleId));

        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    //
//    @PutMapping("/comments/{id}")
//    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));
//
//        comment.setContent(commentRequest.getContent());
//
//        return new ResponseEntity<>(commentRepository.save(configcomment), HttpStatus.OK);
//    }
//
    @DeleteMapping("/admin/details/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        detailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//
//    @DeleteMapping("/tutorials/{tutorialId}/comments")
//    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
//        if (!tutorialRepository.existsById(tutorialId)) {
//            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
//        }
//
//        commentRepository.deleteByTutorialId(tutorialId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
