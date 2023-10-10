package com.bezkoder.spring.hibernate.onetomany.repository;


import com.bezkoder.spring.hibernate.onetomany.dto.TimeRange;
import com.bezkoder.spring.hibernate.onetomany.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT SCHEDULES.* FROM SCHEDULES JOIN DETAILS ON SCHEDULES.ID= DETAILS.SCHEDULE_ID " +
            "WHERE details.start_time>= :startTime AND details.start_time <= :endTime " +
            "AND details.end_time>= :startTime AND details.end_time <= :endTime " +
            "GROUP BY SCHEDULES.ID ",
            nativeQuery = true)
    List<Schedule> findByTime(@Param("startTime") Timestamp startTime,
                              @Param("endTime") Timestamp endTime);

    @Query(value = "SELECT SCHEDULES.* FROM SCHEDULES JOIN DETAILS ON SCHEDULES.ID= DETAILS.SCHEDULE_ID " +
            "WHERE details.trainer_name like %:trainerName% " +
            "GROUP BY SCHEDULES.ID ",
            nativeQuery = true)
    List<Schedule> findByTrainerName(@Param("trainerName") String trainerName);
}
