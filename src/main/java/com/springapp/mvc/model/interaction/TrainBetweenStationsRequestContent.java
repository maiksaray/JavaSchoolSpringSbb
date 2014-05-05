package com.springapp.mvc.model.interaction;

import com.springapp.mvc.model.Station;

import java.io.Serializable;

public class TrainBetweenStationsRequestContent implements Serializable {
    private Station from;
    private Station to;

    public TrainBetweenStationsRequestContent(Station from, Station to){
        this.from = from;
        this.to = to;
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }
}
