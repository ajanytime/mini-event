package com.paymentscanada.model.dto;

import com.paymentscanada.model.Event;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

public class EventDetailsDTO extends ResourceSupport {

    private EventSummaryDTO eventSummary;
    private String details;
    private String eventId;
    private String type;
    private LocalDate date;
    private int size;
    private String summary;

    public EventDetailsDTO() {}

    public EventDetailsDTO(Event event) {
        this.setEventId(event.getEventId());
        this.setDate(event.getDate());
        this.setType(event.getType());
        this.setSize(event.getSize());
        this.setSummary(event.getSummary());
        this.setDetails(event.getDetails());
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
