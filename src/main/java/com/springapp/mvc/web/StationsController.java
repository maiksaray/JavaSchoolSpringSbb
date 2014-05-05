package com.springapp.mvc.web;

import com.springapp.mvc.service.DataProvider;
import com.springapp.mvc.model.Station;
import com.springapp.mvc.service.RequestProcessor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationsController {

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private RequestProcessor requestProcessor;

    @RequestMapping(method = {RequestMethod.GET})
    public String showStations(ModelMap model) {
        List<Station> stations = dataProvider.getAllStations();
        List<String> names = new ArrayList<String>();
        for (Station station : stations) {
            names.add(station.getName());
        }
        model.addAttribute("stations", names);
        return "stations";
    }

    @RequestMapping(value = "/stationListJSON", method = {RequestMethod.GET})
    public
    @ResponseBody
    String getStationListJSON(@RequestParam("query") String station, ModelMap model) {
        JSONObject response = new JSONObject();
        try {
            response.put("query", station);
            JSONArray values = new JSONArray();
            for (Station stationfromDB : dataProvider.getAllStations()) {
                if (stationfromDB.getName().startsWith(station))
                    values.put(stationfromDB.getName());
            }
            response.put("suggestions", values);

        } catch (JSONException ex) {
        }
        return response.toString();
    }

    @RequestMapping(value = "/schedule/{stationName}", method = {RequestMethod.GET})
    public String showSchedule(@PathVariable String stationName, ModelMap model) {
        Station station = dataProvider.getStation(stationName);
        model.addAttribute("stationName", station.getName());
        model.addAttribute("Schedules", station.getSchedule().entrySet());

        return "stationSchedule";
    }

    @Secured ({"ROLE_ADMIN"})
    @RequestMapping("/remove/{stationName}")
    public String removeStation(@PathVariable String stationName, ModelMap model) {
        if(requestProcessor.removeStation(stationName))
            return "redirect:/stations";
        else
        return "stationDeleteConfirm";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/create")
    public String showCreationForm(@RequestParam("stationName") String stationName,
                                   ModelMap model) {
        if (requestProcessor.addStation(stationName))
            return "redirect:/stations";
        else
            return "redirect:/stations/creationFailure";
    }

    @RequestMapping("/creationFailure")
    public String creationFailure(ModelMap model){
        return "creationFailed";
    }
}
