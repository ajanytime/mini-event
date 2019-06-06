package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventSummaryDTO> find(LocalDate start, LocalDate end);
    EventDetailsDTO get(String eventId);
    void save(Event event);

}
