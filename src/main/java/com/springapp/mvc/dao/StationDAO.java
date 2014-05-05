package com.springapp.mvc.dao;

import com.springapp.mvc.model.Station;
import java.util.List;

public interface StationDAO {

    public List<Station> getAll();

    public void remove(Station station);

    public void add(Station station);

    public Station getStation(String startStation);

}

