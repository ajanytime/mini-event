package com.paymentscanada.service;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EventService {

    CompletableFuture<List<EventSummaryDTO>> find(LocalDate start, LocalDate end);
    CompletableFuture<EventSummaryDTO> getSummary(String eventId) throws InterruptedException;
    CompletableFuture<String> getDetails(String eventId) throws InterruptedException;
    EventDetailsDTO get(String eventId);
    void save(Event event);

}
