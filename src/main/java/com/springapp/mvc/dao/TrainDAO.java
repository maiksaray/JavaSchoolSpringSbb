package com.springapp.mvc.dao;

import com.springapp.mvc.model.Train;

import java.util.List;

public interface TrainDAO {

    public Train getTrain(String id);

    public List<Train> getAll();

    public void add(Train train);

    public void remove(Train train);
}
