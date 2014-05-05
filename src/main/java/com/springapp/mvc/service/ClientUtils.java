package com.springapp.mvc.service;


import com.springapp.mvc.model.Station;

import java.sql.Time;
import java.util.Map;

public class ClientUtils {
    public static Station getFirstStationInSchedule(Map<Station, Time> schedule) {
        try {
            Map.Entry<Station, Time> first = (Map.Entry<Station, Time>) schedule.entrySet().toArray()[0];
            for (Map.Entry<Station, Time> station : schedule.entrySet()) {
                if (station.getValue().before(first.getValue())) {
                    first = station;
                }
            }
            return first.getKey();
        } catch (ClassCastException ex) {
            return null;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public static Station getLastStationInSchedule(Map<Station, Time> schedule) {
        try {
            Map.Entry<Station, Time> last = (Map.Entry<Station, Time>) schedule.entrySet().toArray()[0]; //WELL, dont't know, how else
            for (Map.Entry<Station, Time> station : schedule.entrySet()) {
                if (station.getValue().after(last.getValue())) {
                    last = station;
                }
            }
            return last.getKey();
        } catch (ClassCastException ex) {
            return null;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
}
