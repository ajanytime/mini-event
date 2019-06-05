package com.paymentscanada.model;

import java.time.LocalDate;

public class EventDetails {

    private EventSummary eventSummary;
    private String details;
    private String id;
    private String type;
    private LocalDate date;
    private int size;
    private String summary;

    public EventDetails(EventSummary eventSummary) {
        this.eventSummary = eventSummary;
        this.setId(eventSummary.getId());
        this.setDate(eventSummary.getDate());
        this.setType(eventSummary.getType());
        this.setSize(eventSummary.getSize());
        this.setSummary(eventSummary.getSummary());
    }

    public EventDetails(EventSummary eventSummary, String details) {
        this.eventSummary = eventSummary;
        this.setId(eventSummary.getId());
        this.setDate(eventSummary.getDate());
        this.setType(eventSummary.getType());
        this.setSize(eventSummary.getSize());
        this.setSummary(eventSummary.getSummary());
        this.setDetails(details);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
