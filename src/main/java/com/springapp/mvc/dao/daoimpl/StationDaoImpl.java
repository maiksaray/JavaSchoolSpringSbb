package com.springapp.mvc.dao.daoimpl;

import javax.persistence.Query;
import java.util.List;


import com.springapp.mvc.dao.CommonDAO;
import com.springapp.mvc.dao.StationDAO;
import com.springapp.mvc.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class StationDaoImpl extends CommonDAO<Station> implements StationDAO {

    public StationDaoImpl() {
        super(Station.class);
    }

    public List<Station> getAll() {
        return super.doGetAll(Station.class);
    }

    public void remove(Station station) {
        super.remove(station);
    }

    public void add(Station station) {
        super.persist(station);
    }

    public Station getStation(String startStation) {
        String query = "from Station where name=" + "\'" + startStation + "\'";
        List list = sf.getCurrentSession().createQuery(query).list();
        return list.size() > 0 ? (Station) list.get(0) : null;
    }
}
