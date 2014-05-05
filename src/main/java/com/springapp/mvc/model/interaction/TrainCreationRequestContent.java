package com.springapp.mvc.model.interaction;

import java.io.Serializable;
import java.sql.Time;
import java.util.Map;

/**
 * Created by maiks_000 on 3/31/14.
 */
public class TrainCreationRequestContent implements Serializable{
    private Map<String, Time> inputs;
    private String numberOfSeats;

    public TrainCreationRequestContent(Map<String, Time> inputs, String numberOfSeats) {
        this.inputs = inputs;
        this.numberOfSeats = numberOfSeats;
    }

    public Map<String, Time> getInputs() {
        return inputs;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }
}
