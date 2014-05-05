package com.springapp.mvc.model.interaction;

import java.io.Serializable;

/**
 * Created by maiks_000 on 4/1/14.
 */
public class TwoStationNames implements Serializable{
    private String source;
    private String target;

    public TwoStationNames(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
