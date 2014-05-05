package com.springapp.mvc.web;

import com.springapp.mvc.model.Passenger;
import com.springapp.mvc.model.Station;
import com.springapp.mvc.model.Ticket;
import com.springapp.mvc.model.Train;
import com.springapp.mvc.model.interaction.Request;
import com.springapp.mvc.model.interaction.TicketRequestContent;
import com.springapp.mvc.service.DataProvider;
import com.springapp.mvc.service.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private RequestProcessor requestProcessor;

    @RequestMapping(method = {RequestMethod.GET})
    public String showTicket(ModelMap model) {
        return "ticket";
    }

    @RequestMapping(value = "/purchase/{trainId}", method = {RequestMethod.GET})
    public String purchaseTicket(@PathVariable String trainId, ModelMap model) {
        Train train = dataProvider.getTrain(trainId);
        model.addAttribute("trainId", trainId);
        model.addAttribute("schedule", train.getSchedule().entrySet());
        model.addAttribute("stations", train.getStations());
        return "purchaseTicket";
    }

    @RequestMapping(value = "/purchaseTicket")
    public String processTicketPurchase(@RequestParam("startStation") String fromName,
                                        @RequestParam("endStation") String toName,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("trainId") String trainId,
                                        @RequestParam("birthDate") Date birthDate,
                                        ModelMap model) {
        Passenger passenger = new Passenger();
        passenger.setLastName(lastName);
        passenger.setFirstName(firstName);
        passenger.setBirthDate(birthDate);
        Train train = dataProvider.getTrain(trainId);
        Station from = dataProvider.getStation(fromName);
        Station to = dataProvider.getStation(toName);

        TicketRequestContent content = new TicketRequestContent(passenger, train, from, to);

        Ticket ticket = requestProcessor.purchaseTicket(content);

        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @RequestMapping("/showfortrain/{trainId}")
    public String showTicketsForTrain(@PathVariable String trainId, ModelMap model) {
        List<Ticket> tickets = requestProcessor.getAllTrainTickets(dataProvider.getTrain(trainId));
        model.addAttribute("tickets", tickets);
        return "ticketList";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/remove/{ticketId}")
    public String removeTicket(@PathVariable String ticketId, ModelMap model) {
        Ticket ticket = dataProvider.getTicket(ticketId);
        if (ticket == null) return "ticketRemovalFail";
        String trainId = String.valueOf(ticket.getTrain().getTrainId());
        if (requestProcessor.removeTicket(ticket))
            return "redirect:/showfortrain/" + trainId;
        else
            return "ticketRemovalFail";
    }
}
