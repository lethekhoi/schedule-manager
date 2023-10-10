package com.bezkoder.spring.hibernate.onetomany.dto;

import java.sql.Timestamp;
import java.util.Date;

public class DetailDTO {

    private long id;
    private String trainerName;
    private Timestamp startTime;
    private Timestamp endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }


}
