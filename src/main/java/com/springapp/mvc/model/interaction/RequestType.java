package com.springapp.mvc.model.interaction;

/**
 * Created by maiks_000 on 3/18/14.
 */
public enum RequestType {
    GetStationList,
    GetTrainsListByStationName,
    PurchaseTicket,
    Authenticate, //Future usage, probably
    GetTrainsListByTimeAndRange,
    Empty,
    LocalRequest,
    GetTrainsListByTwoStations, GetTicketsByTrain,
    GetAllTrains,
    AddStation,
    AddTrain,
    GetTrainsListByTwoStationNames,
    Logout,
    RemoveStation,
    RemoveTrain,
    RemoveTicket,
    Close
}
