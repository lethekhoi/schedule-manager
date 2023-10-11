package com.bezkoder.spring.hibernate.onetomany.service.impl;

import com.bezkoder.spring.hibernate.onetomany.model.Detail;
import com.bezkoder.spring.hibernate.onetomany.repository.DetailRepository;
import com.bezkoder.spring.hibernate.onetomany.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    DetailRepository repository;


    @Override
    public Detail addDetail(Detail detail) {
        return repository.save(detail);
    }

    @Override
    public Detail saveDetail(Detail detail) {
        return repository.save(detail);
    }
    @Override
    public List<Detail> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Detail> findByScheduleId(Long scheduleId) {
        return repository.findByScheduleId(scheduleId);
    }

    @Override
    public void deleteById(Long detailId) {
        repository.deleteById(detailId);
    }


}
