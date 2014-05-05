package com.springapp.mvc.model.interaction;


import com.springapp.mvc.model.Passenger;
import com.springapp.mvc.model.Station;
import com.springapp.mvc.model.Train;

import java.io.Serializable;

/**
 * Created by maiks_000 on 3/20/14.
 */
public class TicketRequestContent implements Serializable {
    private final Passenger passenger;
    private final Train train;
    private final Station startStation;
    private Station endStation;

    public TicketRequestContent(Passenger passenger, Train train, Station startStation, Station endStation) {
        this.passenger = passenger;
        this.train = train;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Train getTrain() {
        return train;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }
}
