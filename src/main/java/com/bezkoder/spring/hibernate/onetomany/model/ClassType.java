package com.bezkoder.spring.hibernate.onetomany.model;

public enum ClassType {
    ZOOM,
    CLASSROOM;

    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}