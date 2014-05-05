package com.springapp.mvc.model.interaction;

/**
 * Created by maiks_000 on 3/19/14.
 */
public enum ResponseType {
    TicketRequestOK,
    TicketRequestFailed,
    TrainsByRangesList,
    AddingStationOK,
    AuthOK,
    AuthFailed,
    AddingFailed,
    TrainPassengersList,
    TrainsByStationList,
    StationList,
    Tickets,
    TrainsList,
    ConnectionFail,
    LogoutOk,
    RemovalFailed,
    StationRemovalOk,
    AddingTrainOK,
    TrainRemovalOk,
    TicketRemovalOk,
    Fail //Complete fail
}
