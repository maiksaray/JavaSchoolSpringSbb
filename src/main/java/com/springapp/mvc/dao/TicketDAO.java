package com.springapp.mvc.dao;

import com.springapp.mvc.model.Passenger;
import com.springapp.mvc.model.Ticket;

import java.util.List;

public interface TicketDAO {

    public List<Ticket> getAll();

    public void add(Ticket ticket);

    public void rcurrentSessionove(Ticket ticket);

    public List<Ticket> getTicketsByPassenger(Passenger passenger);

    public void remove(Ticket toRemove);

    public Ticket getTicket(String ticketId);
}
