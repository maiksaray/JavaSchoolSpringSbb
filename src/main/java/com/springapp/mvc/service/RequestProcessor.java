package com.springapp.mvc.service;

import com.springapp.mvc.model.interaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

import com.springapp.mvc.model.*;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestProcessor {


    @Autowired
    private DataProvider dataProvider;

    //region admin
    @Transactional
    public boolean removeTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        try {
            dataProvider.removeTicket(ticket);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean removeTrain(String trainId) {
        Train train = dataProvider.getTrain(trainId);
        if (train == null) {
            return false;
        }
        List<Ticket> tickets = dataProvider.getAllTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getTrain().equals(train)) {
                return false;
            }
        }
        try {
            dataProvider.removeTrain(train);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean removeStation(String stationName){
        Station station = dataProvider.getStation(stationName);
        if (station.getTrains().size() > 0) {
            return false;
        }
        try{
            dataProvider.removeStation(station);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    @Transactional
    public List<Ticket> getAllTrainTickets(Train train){
        List<Ticket> tickets = dataProvider.getAllTickets();
        for (int i = 0; i < tickets.size(); i++) {
            if (!tickets.get(i).getTrain().equals(train)) {
                tickets.remove(i);
                i--;
            }
        }
        return tickets;
    }

    @Transactional
    public boolean addStation(String name){
        return dataProvider.addStation((String) name);
    }

    @Transactional
    public boolean addTrain(TrainCreationRequestContent content){
        Map<String, Time> data = content.getInputs();
        Train train = new Train();
        train.setSchedule(new HashMap<Station, Time>());
        train.setStations(new HashSet<Station>());
        train.setStationTimes(new HashSet<Schedule>());
        train.setSeatsNumber(Integer.valueOf(content.getNumberOfSeats()));
        for (Map.Entry<String, Time> entry : data.entrySet()) {
            if (dataProvider.hasStation(entry.getKey())) {
                Station station = dataProvider.getStation(entry.getKey());
                train.getStations().add(station);
                Schedule schedule = new Schedule();
                schedule.setStation(station);
                schedule.setTrain(train);
                schedule.setTime(entry.getValue());
                train.getStations().add(station);
                train.getStationTimes().add(schedule);
                train.getSchedule().put(station, entry.getValue());
                station.getTrains().add(train);
                station.getTrainTimes().add(schedule);
                station.getSchedule().put(train, entry.getValue());
            } else {
                return false;
            }
        }
        dataProvider.addTrain(train);
        return true;
    }

    //endregion

    //region client

    @Transactional
    public List<Station> getAllStation() {
        return dataProvider.getAllStations();
    }

    @Transactional
    public List<Train> getTrainsByStation(String stationName) {
        List<Train> allTrains = dataProvider.getAllTrains();
        Station station = dataProvider.getStation(stationName);
        for (int i = 0; i < allTrains.size(); i++) {
            if (!allTrains.get(i).hasStation(station)) {
                allTrains.remove(i);
                i--;
            }
        }
        return allTrains;
    }

    @Transactional
    public List<Train> getTrainsByTwoStations(String fromName, String toName) {
        Station from = dataProvider.getStation(fromName);
        Station to = dataProvider.getStation(toName);
        List<Train> trains = dataProvider.getAllTrains();
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            if (!train.hasStation(from) || !train.hasStation(to)) {
                trains.remove(i);
                i--;
                continue;
            }
            if (!trainHasStationsInOrder(train, from, to)) {
                trains.remove(i);
                i--;
            }
        }
        return trains;
    }

    @Transactional
    public List<Train> getTrainsByStationsAndTime(String fromName, String toName, Time startTime, Time endTime) {
        List<Train> allTrains = dataProvider.getAllTrains();

        Station from = dataProvider.getStation(fromName);
        Station to = dataProvider.getStation(toName);

        if (from == null && to == null) {
            return new ArrayList<Train>();
        }

        for (int i = 0; i < allTrains.size(); i++) {
            Train train = allTrains.get(i);
            Map<Station, Time> schedule = train.getSchedule();
            if (from == null) {
                if (train.hasStation(to) && schedule.get(to).before(endTime) && schedule.get(to).after(startTime)) {
                    continue;
                }
            }
            if (to == null) {
                if (train.hasStation(from) && schedule.get(from).before(endTime) && schedule.get(from).after(startTime)) {
                    continue;
                }
            }
            try {
                if (train.hasStation(from) && train.hasStation(to)            //has stations
                        && (schedule.get(from).after(startTime) || schedule.get(from).equals(startTime)) // first station passed after or in start time
                        && (schedule.get(to).before(endTime) || schedule.get(to).equals(endTime))   //secondStation passed before or in end time
                        ) {
                    continue;
                }
                allTrains.remove(i);
                i--;
            } catch (NullPointerException ex) {
                allTrains.remove(i);
            }
        }
        return allTrains;
    }

    @Transactional
    public Ticket purchaseTicket(TicketRequestContent content) {
        try {
            if (!content.getTrain().hasStation(content.getStartStation()) || !content.getTrain().hasStation(content.getEndStation())) {
                //Train has no stations
                return null;
            }
            if (passengerIsOnTrain(content)) {
                return null;
            }
            if (!timeIsValid(content.getTrain(), content.getStartStation())) {
                return null;
            }
            if (trainHasStationsInOrder(content.getTrain(), content.getStartStation(), content.getEndStation())) {
                if (trainHasSeats(content.getTrain(), content.getStartStation(), content.getEndStation())) {
                    Ticket ticket = new Ticket();
                    ticket.setPassenger(content.getPassenger());
                    ticket.setTrain(content.getTrain());
                    ticket.setStartStation(content.getStartStation());
                    ticket.setEndStation(content.getEndStation());
                    dataProvider.addTicket(ticket);
                    return ticket;
                }
            }
        } catch (NullPointerException ex) {
            return null;
        }
        return null;
    }

    //endregion

    private boolean trainHasSeats(Train train, Station startStation, Station endStation) {
        int numberOfSeats = train.getSeatsNumber();
        for (Ticket ticket : dataProvider.getAllTickets()) {
            if (!ticket.getTrain().equals(train)) {
                continue;
            }
            if (trainHasStationsInOrder(train, endStation, ticket.getStartStation())
                    || trainHasStationsInOrder(train, ticket.getStartStation(), startStation)) {
                continue;
            }
            numberOfSeats--;
        }
        return numberOfSeats > 0;
    }

    private boolean trainHasStationsInOrder(Train train, Station from, Station to) {
        try {
            return train.getSchedule().get(from).before(train.getSchedule().get(to));
        } catch (NullPointerException ex) {
            return false;
        }
    }

    private boolean timeIsValid(Train train, Station startStation) {
        if (train.getSchedule().get(startStation).after(Calendar.getInstance().getTime())) {
            return false;
        }
        Date now = Calendar.getInstance().getTime();
        Time trainTime = train.getSchedule().get(startStation);
        java.util.Date local = new Date(now.getTime());
        //TODO:REDO
        java.util.Date trainDate = new Date(now.getYear(), now.getMonth(), now.getDay(), trainTime.getHours(), trainTime.getMinutes(), 0);

        long diff = trainDate.getTime() - local.getTime();
        long diffMinutes = diff / (60 * 1000);
        return true;
        //return diffMinutes >= 10;
    }

    private boolean passengerIsOnTrain(TicketRequestContent content) {
        List<Ticket> tickets = dataProvider.getTicketsByPassenger(content.getPassenger());
        for (Ticket ticket : tickets) {
            if (ticket.getTrain().equals(content.getTrain())) {
                Train train = ticket.getTrain();
                if (trainHasStationsInOrder(train, content.getStartStation(), ticket.getEndStation())
                        && trainHasStationsInOrder(train, ticket.getStartStation(), content.getEndStation())
                        ) {
                    return true;
                }
                if (trainHasStationsInOrder(train, ticket.getStartStation(), content.getStartStation())
                        && trainHasStationsInOrder(train, content.getEndStation(), ticket.getEndStation())) {
                    return true;
                }
            }
        }
        return false;
    }
}
