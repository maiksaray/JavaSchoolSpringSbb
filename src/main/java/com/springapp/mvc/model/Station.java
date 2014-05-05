package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Station implements Serializable {
    private int stationId;
    private String name;
    private Set<Train> trains;
    private Set<Schedule> trainTimes;
    private Map<Train, Time> schedule;

    private boolean scheduleDone;

    public void constructSchedule() {
        if (!scheduleDone) {
            schedule = new HashMap<Train, Time>();
            for (Train train : trains) {
                for (Schedule time : trainTimes) {
                    if (time.getTrain().equals(train) && time.getStation().equals(this)) {
                        this.schedule.put(train, time.getTime());
                    }
                }

            }
            scheduleDone = true;
        }
    }

    @Id
    @Column(name = "stationId")
    @GeneratedValue
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stations")
    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    public Set<Schedule> getTrainTimes() {
        return trainTimes;
    }

    public void setTrainTimes(Set<Schedule> trainTimes) {
        this.trainTimes = trainTimes;
    }

    @Transient
    public Map<Train, Time> getSchedule() {
        if(!scheduleDone){
            constructSchedule();
        }
        return schedule;
    }

    public void setSchedule(Map<Train, Time> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return name.equals(station.getName());
    }

    @Override
    public int hashCode() {
        return stationId;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
