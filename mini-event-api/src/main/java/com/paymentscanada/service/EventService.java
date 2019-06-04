package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventSummary;

import java.util.List;

public interface EventService {

    List<EventSummary> getAll();
    Event get(String id);
    void save(Event event);

}
