package com.springapp.mvc.service;


import java.util.ArrayList;
import java.util.List;

import com.springapp.mvc.dao.PassengerDAO;
import com.springapp.mvc.dao.StationDAO;
import com.springapp.mvc.dao.TicketDAO;
import com.springapp.mvc.dao.TrainDAO;
import com.springapp.mvc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataProvider {

    @Autowired
    private StationDAO stationDao;

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private PassengerDAO passengerDAO;

    @Autowired
    private TicketDAO ticketDAO;

    public DataProvider() {
    }

    @Transactional
    public synchronized List<Station> getAllStations() {
        List<Station> result = stationDao.getAll();
        return result;
    }

    @Transactional
    public synchronized List<Train> getAllTrains() {
        return trainDAO.getAll();
    }

    @Transactional
    public synchronized Station getStation(String startStation) {
        return stationDao.getStation(startStation);
    }


    @Transactional
    public synchronized Train getTrain(String trainId) {
        return trainDAO.getTrain(trainId);
    }

    @Transactional
    public synchronized List<Ticket> getTicketsByPassenger(Passenger passenger) {
        //id passenger is not in db
        Passenger person = passengerDAO.getPassenger(passenger);
        if (person != null)
            return ticketDAO.getTicketsByPassenger(person);
        else
            return new ArrayList<Ticket>();
    }

    @Transactional
    public synchronized void addTicket(Ticket ticket) {
        Passenger fromDb = passengerDAO.getPassenger(ticket.getPassenger());
        if (fromDb == null) {
            passengerDAO.addPassenger(ticket.getPassenger()); //we get our ticket->passenger persisted and pas.id set
        } else {
            ticket.setPassenger(fromDb); //we get passenger from db so we have to reset link
        }
        ticketDAO.add(ticket);
    }

    @Transactional
    public synchronized List<Ticket> getAllTickets() {
        return ticketDAO.getAll();
    }

    @Transactional
    public synchronized boolean addStation(String content) {
        Station station = new Station();
        station.setName(content);
        stationDao.add(station);
        return station.getStationId() > 0;
    }

    @Transactional
    public synchronized boolean hasStation(String content) {
        return stationDao.getStation(content) != null;
    }

    @Transactional
    public synchronized void addTrain(Train train) {
        trainDAO.add(train);
    }

    @Transactional
    public synchronized void removeStation(Station actualStation) {
        stationDao.remove(actualStation);
    }

    @Transactional
    public synchronized void removeTrain(Train train) {
        trainDAO.remove(train);
    }

    @Transactional
    public synchronized void removeTicket(Ticket toDelete) {
        ticketDAO.remove(toDelete);
    }

    @Transactional
    public Ticket getTicket(String ticketId) {
        return ticketDAO.getTicket(ticketId);
    }
}
