package com.springapp.mvc.model.interaction;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by maiks_000 on 3/20/14.
 */
public class TrainsInTimeAndRangeRequestContent implements Serializable{
    private final String startStation;
    private final String endStation;
    private final Time startTime;
    private final Time endTime;

    public TrainsInTimeAndRangeRequestContent(String arg, String arg1, Time time, Time time1) {
        this.startStation = arg;
        this.endStation = arg1;
        this.startTime = time;
        this.endTime = time1;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
