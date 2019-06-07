package com.paymentscanada.repository;

import com.paymentscanada.model.Event;

import java.util.Map;

public interface EventRepository {

    void save(Event event);
    Event get(String eventId);
    Map<String, Event> getAll();
    void deleteAll();

}
