package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
public class Schedule implements Serializable {
    private Time time;
    private Train train;
    private Station station;
    private int id;

    @Id
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name="time")

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name="trainId")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="stationId")
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
/*
    @ManyToOne
    @JoinColumn(name="trainId")
    public Set<model.model.Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<model.model.Train> trains) {
        this.trains = trains;
    }*/
}
