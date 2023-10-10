package com.bezkoder.spring.hibernate.onetomany.repository;

import com.bezkoder.spring.hibernate.onetomany.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {

    List<Detail> findByScheduleId(Long scheduleId);
}
