package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
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
    public List<EventSummaryDTO> find(LocalDate start, LocalDate end) {

        Map<String, EventSummaryDTO> map = eventRepository.getAll();

        List<EventSummaryDTO> events = toListHelper(map);

        if (start == null && end == null) {
            return events;
        } else if (start != null && end == null) {
            return events.stream().filter((EventSummaryDTO event) -> event.getDate().isEqual(start)).collect(Collectors.toList());
        } else
            return events.stream().filter((EventSummaryDTO event) ->
                    event.getDate().isAfter(start) && event.getDate().isBefore(end)
            ).collect(Collectors.toList());
    }

    @Override
    public EventDetailsDTO get(String eventId) {
        return eventRepository.get(eventId);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    private List<EventSummaryDTO> toListHelper(Map<String, EventSummaryDTO> map) {
        List<EventSummaryDTO> list = new ArrayList<>();
        //convert to a list for UI to render
        map.forEach((k, v) -> {
            // had to perform this id assignment step because spring HATEOAS clashes with id field and remove its value
            v.setEventId(k);
            list.add(v);
        });
        return list;
    }
}
