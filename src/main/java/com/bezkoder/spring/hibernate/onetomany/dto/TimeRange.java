package com.bezkoder.spring.hibernate.onetomany.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class TimeRange {
    private Timestamp fromTime;
    private Timestamp toTime;

    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }


    public static TimeRange CreateTimeRange(TimeFrame timeFrame) {
        TimeRange timeRange = new TimeRange();
        Timestamp toTime = null, fromTime = null;
        LocalDateTime now = LocalDateTime.now();


        switch (timeFrame) {
            case nextWeek:
                fromTime = Timestamp.valueOf(now);
                toTime = Timestamp.valueOf(now.plusWeeks(1));
                break;
            case nextMonth:
                fromTime = Timestamp.valueOf(now);
                toTime = Timestamp.valueOf(now.plusMonths(1));
                break;
            case nextYear:
                fromTime = Timestamp.valueOf(now);
                toTime = Timestamp.valueOf(now.plusYears(1));
                break;
            case lastWeek:
                toTime = Timestamp.valueOf(now);
                fromTime = Timestamp.valueOf(now.minusWeeks(1));
                break;
            case lastMonth:
                toTime = Timestamp.valueOf(now);
                fromTime = Timestamp.valueOf(now.minusMonths(1));
                break;
            case lastYear:
                toTime = Timestamp.valueOf(now);
                fromTime = Timestamp.valueOf(now.minusYears(1));
                break;
            case all:
                break;
        }

        timeRange.setToTime(toTime);
        timeRange.setFromTime(fromTime);
        return timeRange;
    }
}


