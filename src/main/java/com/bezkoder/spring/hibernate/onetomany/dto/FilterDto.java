package com.bezkoder.spring.hibernate.onetomany.dto;

import lombok.Data;

@Data
public class FilterDto {
    private  String filterBy;
    private String value;

    public FilterDto(String filterBy, String value) {
        this.filterBy = filterBy;
        this.value = value;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
