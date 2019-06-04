package com.paymentscanada.service;

import com.paymentscanada.model.EventSummary;
import com.paymentscanada.repository.EventRepository;
import com.paymentscanada.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventSummary> getAll() {
        Map<String, EventSummary> map = eventRepository.getAll();
        List<EventSummary> events = new ArrayList<>();
        map.forEach((k, v) -> events.add(v));
        return events;
    }

    @Override
    public Event get(String id) {
        return eventRepository.get(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }
}
