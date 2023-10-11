package com.bezkoder.spring.hibernate.onetomany.service;

import com.bezkoder.spring.hibernate.onetomany.model.Detail;

import java.util.List;

public interface DetailService {
    Detail addDetail(Detail detail);

    Detail saveDetail(Detail detail);
    List<Detail> listAll();

    List<Detail> findByScheduleId(Long scheduleId);

    void deleteById(Long detailId);
}
