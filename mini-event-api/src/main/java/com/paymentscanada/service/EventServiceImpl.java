package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
import com.paymentscanada.repository.EventRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.paymentscanada.config.AsyncConfig.ASYNC_EXECUTOR_SERVICE_LAYER;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Async(ASYNC_EXECUTOR_SERVICE_LAYER)
    @Override
    public CompletableFuture<List<EventSummaryDTO>> find(LocalDate start, LocalDate end) {
        Map<String, Event> map = eventRepository.getAll();
        List<EventSummaryDTO> events = this.toListHelper(map);
        return CompletableFuture.completedFuture(this.searchHelper(events, start, end));
    }

    @Async(ASYNC_EXECUTOR_SERVICE_LAYER)
    @Override
    public CompletableFuture<EventSummaryDTO> getSummary(String eventId) throws InterruptedException {
        return CompletableFuture.completedFuture(new EventSummaryDTO(eventRepository.getSummary(eventId)));
    }

    @Async(ASYNC_EXECUTOR_SERVICE_LAYER)
    @Override
    public CompletableFuture<String> getDetails(String eventId) throws InterruptedException {
        return CompletableFuture.completedFuture(eventRepository.getDetails(eventId));
    }

    /**
     * This method could have been deprecated after adding
     * async rest controller support, but we are keeping it for now
     * to support test cases
     * */
    @Override
    public EventDetailsDTO get(String eventId) {
        return new EventDetailsDTO(eventRepository.get(eventId));
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    private List<EventSummaryDTO> searchHelper(List<EventSummaryDTO> events, LocalDate start, LocalDate end) {
        if (start == null && end == null) {
            return events;
        } else if (start != null && end == null) {
            return events.stream().filter((EventSummaryDTO event) -> event.getDate().isEqual(start)).collect(Collectors.toList());
        } else
            return events.stream().filter((EventSummaryDTO event) ->
                    event.getDate().isAfter(start) && event.getDate().isBefore(end)
            ).collect(Collectors.toList());
    }

    private List<EventSummaryDTO> toListHelper(Map<String, Event> map) {
        List<EventSummaryDTO> list = new ArrayList<>();
        //convert to a list for UI to render
        map.forEach((k, v) -> {
            // had to perform this id assignment step because spring HATEOAS clashes with id field and remove its value
            v.setEventId(k);
            list.add(new EventSummaryDTO(v));
        });
        return list;
    }
}
