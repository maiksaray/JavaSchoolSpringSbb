package com.springapp.mvc.dao.daoimpl;




import com.springapp.mvc.dao.CommonDAO;
import com.springapp.mvc.dao.PassengerDAO;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerDaoImpl extends CommonDAO<Passenger> implements PassengerDAO{
    public PassengerDaoImpl() {
        super(Passenger.class);
    }

    public List<Passenger> getAll() {
        return super.doGetAll(Passenger.class);
    }

    public void addPassenger(Passenger passenger) {
        super.persist(passenger);
    }

    public Passenger getPassenger(Passenger passenger) {
       /* currentSession.getTransaction().begin();
        Query query = currentSession.createQuery("select pas from model.model.Passenger pas where pas.firstName=?1 and  pas.lastName=?2 and pas.birthDate=?3");
        query.setParameter(1, passenger.getFirstName());
        query.setParameter(2, passenger.getLastName());
        query.setParameter(3, passenger.getBirthDate());
        Object result = query.getSingleResult();
        currentSession.getTransaction().commit();
        */
        for (Passenger person : this.getAll()) {
            if(person.equals(passenger)){
                return person;
            }
        }
        return null;
    }
}
