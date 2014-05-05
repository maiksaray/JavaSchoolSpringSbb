package com.springapp.mvc.web;

import com.springapp.mvc.model.Station;
import com.springapp.mvc.model.Train;
import com.springapp.mvc.model.interaction.*;
import com.springapp.mvc.service.DataProvider;
import com.springapp.mvc.service.RequestProcessor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trains")
public class TrainsController {

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private RequestProcessor requestProcessor;

    @RequestMapping(method = {RequestMethod.GET})
    public String showTrains(ModelMap model) {

        model.addAttribute("trains", dataProvider.getAllTrains());
        return "trains";
    }

    @RequestMapping(value = "/schedule/{trainId}", method = {RequestMethod.GET})
    public String showSchedule(@PathVariable String trainId, ModelMap model) {
        Train train = dataProvider.getTrain(trainId);
        model.addAttribute("trainId", train.getTrainId());
        model.addAttribute("schedule", train.getSchedule().entrySet());
        return "trainSchedule";
    }

    @RequestMapping(value = "/filtered")
    public String showFilteredSchedule(@RequestParam("fromStation") String from,
                                       @RequestParam("toStation") String to,
                                       @RequestParam("fromTime") String start,
                                       @RequestParam("toTime") String end,
                                       ModelMap model) {
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        List<Train> trains;
        if (start.equals("") && end.equals("")) {
            trains = requestProcessor.getTrainsByTwoStations(from, to);
        } else {
            Time startTime = Time.valueOf(start + ":00");
            Time endTime = Time.valueOf(end + ":00");
            trains = requestProcessor.getTrainsByStationsAndTime(from, to, startTime, endTime);
        }
        model.addAttribute("trains", trains);
        return "trains";
    }

    @RequestMapping(value = "/trainJSON", method = {RequestMethod.GET})
    public
    @ResponseBody
    String getStationListJSON(@RequestParam("id") String station, ModelMap model) {
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

    @Secured ({"ROLE_ADMIN"})
    @RequestMapping(value = "/remove/{trainId}")
    public String removeTrainConfirm(@PathVariable("trainId") String trainId,
                                     ModelMap model) {

        if (requestProcessor.removeTrain(trainId))
            return "redirect:/trains";
        return "trainDeleteConfirm";
    }

    @Secured ({"ROLE_ADMIN"})
    @RequestMapping("/create")
    public String showCreationForm(ModelMap model) {
        model.addAttribute("stations", dataProvider.getAllStations());
        return "createTrain";
    }

    @Secured ({"ROLE_ADMIN"})
    @RequestMapping("/create/submit")
    public
    @ResponseBody
    String submitCreation(@RequestParam("train") String json, ModelMap model) {
        try {
            JSONObject data = new JSONObject(json);
            String numberOfSeats = data.getString("seats");
            JSONArray scheduleJSON = data.getJSONArray("schedule");
            Map<String, Time> schedule = new HashMap<String, Time>();
            for (int i = 0; i < scheduleJSON.length(); i++) {
                JSONObject item = scheduleJSON.getJSONObject(i);
                schedule.put(item.getString("station"), Time.valueOf(item.getString("time") + ":00"));
            }
            if (requestProcessor.addTrain(new TrainCreationRequestContent(schedule, numberOfSeats)))
                return "ok";//return "{\"status\":\"ok\"}";
            else {
                return "fail";//return "{\"status\":\"fail\",reason:\"db error\"}";
            }
        } catch (JSONException ex) {
            return "fail";//"{'status':'fail', reason:'json error'}";
        }

    }
}
