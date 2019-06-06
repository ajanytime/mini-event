package com.paymentscanada.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.paymentscanada.model.Event;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

public class EventSummaryDTO extends ResourceSupport {

    private String eventId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String type;
    private String summary;
    private int size;

    public EventSummaryDTO(){}

    public EventSummaryDTO(Event event) {
        setEventId(event.getEventId());
        setDate(event.getDate());
        setType(event.getType());
        setSummary(event.getSummary());
        setSize(event.getSize());
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
