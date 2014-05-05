package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable {
    private int ticketId;
    private Passenger passenger;
    private Station startStation;
    private Station endStation;
    private Train train;

    @Id
    @Column(name = "ticketId")
    @GeneratedValue
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return ticketId == ticket.ticketId;
    }

    @Override
    public int hashCode() {
        return ticketId;
    }

    @OneToOne
    @JoinColumn(name = "passengerId")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @OneToOne
    @JoinColumn(name = "startStationId", referencedColumnName = "stationId")
    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    @OneToOne
    @JoinColumn(name = "endStationId", referencedColumnName = "stationId")
    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    @OneToOne
    @JoinColumn(name = "trainId")
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
