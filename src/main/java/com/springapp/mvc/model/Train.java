package com.springapp.mvc.model;

import com.springapp.mvc.service.ClientUtils;
import com.springapp.mvc.service.ServerUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Entity
public class Train implements Serializable {
    private int trainId;
    private int seatsNumber;
    //Mapped from db
    private Set<Station> stations;
    private Set<Schedule> stationTimes;
    //Constructed from other stuff
    private Map<Station, Time> schedule;
    private String startStation;
    private String endStation;

    @Transient
    public String getStartStation(){
        if(startStation == null || startStation.equals("")){
            setStartStation(ClientUtils.getFirstStationInSchedule(this.getSchedule()).getName());
        }
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    @Transient
    public String getEndStation() {
        if(endStation ==null || endStation.equals("")){
            setEndStation(ClientUtils.getLastStationInSchedule(this.getSchedule()).getName());
        }
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    private boolean scheduleDone = false;

    public void constructSchedule() {
        if (!scheduleDone) {
            schedule = new HashMap<Station, Time>();
            for (Station station : stations) {
                for (Schedule time : stationTimes) {
                    if (time.getTrain().equals(this) && time.getStation().equals(station)) {
                        this.schedule.put(station, time.getTime());
                    }
                }

            }
            scheduleDone = true;
        }
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "schedule", joinColumns = {
            @JoinColumn(name = "trainId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "stationId",
                    nullable = false, updatable = false)})
    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> schedule) {
        //scheduleDone = true;
        this.stations = schedule;
    }

    public boolean hasStation(Station station) {
        return stations.contains(station);
    }

    @Id
    @Column(name = "trainId")
    @GeneratedValue
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "seatsNumber")
    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    public Set<Schedule> getStationTimes() {
        return stationTimes;
    }

    public void setStationTimes(Set<Schedule> stationTimes) {
        this.stationTimes = stationTimes;
    }

    @Transient
    public Map<Station, Time> getSchedule() {
        if (!scheduleDone) {
            constructSchedule();
        }
        return schedule;
    }

    public void setSchedule(Map<Station, Time> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (trainId != train.trainId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return trainId;
    }

    @Override
    public String toString(){
        return String.valueOf(this.trainId);
    }



}
