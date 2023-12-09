package com.bezkoder.spring.hibernate.onetomany.repository;

import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedules, Integer> {
}
