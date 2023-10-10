package com.bezkoder.spring.hibernate.onetomany.dto;

import java.util.List;

public class ScheduleDTO {
    private long id;
    private String trainingType;
    private String classType;
    private String zoomLink;
    private String roomInfo;
    private List<DetailDTO> listDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public List<DetailDTO> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<DetailDTO> listDetails) {
        this.listDetails = listDetails;
    }
}
