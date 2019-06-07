package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;

import java.util.Map;

public interface EventRepository {

    void save(Event event);
    Event get(String eventId);
    Map<String, Event> getAll();
    void deleteAll();

}
