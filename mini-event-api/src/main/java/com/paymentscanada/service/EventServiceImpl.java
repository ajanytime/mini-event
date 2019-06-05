package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventDetails;
import com.paymentscanada.model.EventSummary;
import com.paymentscanada.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventSummary> find(LocalDate start, LocalDate end) {
        // find all hashed events from redis
        Map<String, EventSummary> map = eventRepository.getAll();

        // convert map to list
        List<EventSummary> events = new ArrayList<>();
        map.forEach((k, v) -> events.add(v));

        // apply search date ranges
        if (start == null && end == null) {
            return events;
        } else if (start != null && end == null) {
            return events.stream().filter((EventSummary event) -> event.getDate().isEqual(start)).collect(Collectors.toList());
        } else
            return events.stream().filter((EventSummary event) ->
                    event.getDate().isAfter(start) && event.getDate().isBefore(end)
            ).collect(Collectors.toList());
    }

    @Override
    public EventDetails get(String id) {
        return eventRepository.get(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }
}
