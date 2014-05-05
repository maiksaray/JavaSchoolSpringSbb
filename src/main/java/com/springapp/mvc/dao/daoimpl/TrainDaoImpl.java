package com.springapp.mvc.dao.daoimpl;


import com.springapp.mvc.dao.CommonDAO;
import com.springapp.mvc.dao.TrainDAO;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Repository
public class TrainDaoImpl extends CommonDAO<Train> implements TrainDAO {


    public TrainDaoImpl() {
        super(Train.class);
    }

    @Override
    public Train getTrain(String entityId) {
        int id = Integer.valueOf(entityId);
        String query = "FROM Train WHERE trainId=" + "\'" + id + "\'";
        List list = sf.getCurrentSession().createQuery(query).list();
        return (Train) list.get(0);
    }

    public List<Train> getAll() {
        return super.doGetAll(Train.class);
    }

    @Override
    public Train find(String entityId) {
        return null;
    }

    public void add(Train train) {
        super.persist(train);
        //FIXING TIME PERSISTENCE, TODO:REDO
        List<Schedule> schedules = sf.getCurrentSession().createQuery("FROM Schedule").list();
        for (Schedule schedule : schedules) {
            if (schedule.getTrain().equals(train)) {
                for (Map.Entry<Station, Time> entry : train.getSchedule().entrySet()) {
                    if (entry.getKey().getStationId() == schedule.getStation().getStationId()) {
                        schedule.setTime(entry.getValue());

                    }
                }
            }
        }
    }

    public void remove(Train train) {
        //weird, but exceptions keep popping without it
        //removind dependencies explicitly, so the entity can be deleted without restoring it by cascade
        train.setStations(new HashSet<Station>());
        train.setStationTimes(new HashSet<Schedule>());
        super.merge(train);
        //now we can delete it
        super.remove(train);
    }
}
