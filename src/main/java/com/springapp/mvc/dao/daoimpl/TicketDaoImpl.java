package com.springapp.mvc.dao.daoimpl;



import java.util.List;

import com.springapp.mvc.dao.CommonDAO;
import com.springapp.mvc.dao.TicketDAO;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TicketDaoImpl extends CommonDAO<Ticket> implements TicketDAO{

    public TicketDaoImpl() {
        super(Ticket.class);
    }

    public List<Ticket> getAll() {
        return doGetAll(Ticket.class);
    }

    public void add(Ticket ticket) {
       super.persist(ticket);
    }

    public void rcurrentSessionove(Ticket ticket) {
        currentSession.getTransaction().begin();
        //Ticket fromDb = currentSession.find(Ticket.class, ticket.getTicketId()); //since it's detached
        //currentSession.rcurrentSessionove(fromDb);
        currentSession.getTransaction().commit();
    }

    @Transactional
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        String query = "FROM Ticket WHERE passenger=:passenger";
        return sf.getCurrentSession().createQuery(query).setParameter("passenger", passenger).list();
    }

    @Override
    public Ticket getTicket(String ticketId) {
        for(Ticket ticket:this.getAll()){
            if(ticket.getTicketId()==Integer.valueOf(ticketId))
                return ticket;
        }
        return null;
    }



}