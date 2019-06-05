package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventDetails;
import com.paymentscanada.model.EventSummary;

import java.util.Map;

public interface EventRepository {

    void save(Event event);
    EventDetails get(String id);
    Map<String, EventSummary> getAll();

}
