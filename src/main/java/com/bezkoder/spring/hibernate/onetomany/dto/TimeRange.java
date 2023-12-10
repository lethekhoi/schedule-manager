package com.bezkoder.spring.hibernate.onetomany.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
@Getter
@Setter
public class TimeRange {
    LocalDate FromTime;
    LocalDate ToTime;
}
