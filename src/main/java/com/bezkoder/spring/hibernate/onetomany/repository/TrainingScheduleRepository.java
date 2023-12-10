package com.bezkoder.spring.hibernate.onetomany.repository;

import com.bezkoder.spring.hibernate.onetomany.model.TrainingSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedules, Integer> {
    List<TrainingSchedules> findByTrainingDate(LocalDate trainingDate);
    List<TrainingSchedules> findByClassType(String classType);

    @Query(value = "SELECT * FROM training_schedules  " +
            "WHERE training_date >= :startTime AND training_date <= :endTime ",
            nativeQuery = true)
    List<TrainingSchedules> findByTime(@Param("startTime") LocalDate startTime,
                                       @Param("endTime") LocalDate endTime);
}
