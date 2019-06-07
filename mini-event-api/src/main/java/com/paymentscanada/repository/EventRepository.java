package com.paymentscanada.repository;

import com.paymentscanada.model.Event;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EventRepository {

    void save(Event event);
    Event get(String eventId);
    Event getSummary(String eventId);
    String getDetails(String eventId);
    Map<String, Event> getAll();
    void deleteAll();
    void load(List<Event> events) throws InterruptedException;

}
