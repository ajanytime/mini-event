package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventDetails;
import com.paymentscanada.model.EventSummary;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventSummary> find(LocalDate start, LocalDate end);
    EventDetails get(String id);
    void save(Event event);

}
