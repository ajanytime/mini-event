package com.paymentscanada.model.dto;

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

    public EventDetailsDTO(EventSummaryDTO eventSummary) {
        this.eventSummary = eventSummary;
        this.setEventId(eventSummary.getEventId());
        this.setDate(eventSummary.getDate());
        this.setType(eventSummary.getType());
        this.setSize(eventSummary.getSize());
        this.setSummary(eventSummary.getSummary());
    }

    public EventDetailsDTO(EventSummaryDTO eventSummary, String details) {
        this.eventSummary = eventSummary;
        this.setEventId(eventSummary.getEventId());
        this.setDate(eventSummary.getDate());
        this.setType(eventSummary.getType());
        this.setSize(eventSummary.getSize());
        this.setSummary(eventSummary.getSummary());
        this.setDetails(details);
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
