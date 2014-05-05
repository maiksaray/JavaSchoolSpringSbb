package com.springapp.mvc.dao;

import com.springapp.mvc.model.Passenger;

import java.util.List;

public interface PassengerDAO {

    public List<Passenger> getAll();

    public void addPassenger(Passenger passenger);

    public Passenger getPassenger(Passenger passenger);
}
