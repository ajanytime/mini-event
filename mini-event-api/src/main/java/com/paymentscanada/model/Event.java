package com.paymentscanada.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.paymentscanada.model.dto.EventSummaryDTO;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

public class Event {

    @Indexed
    private String eventId;
    @JsonProperty(value = "event_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    @JsonProperty(value = "event_type")
    private String type;
    @JsonProperty(value = "event_summary")
    private String summary;
    @JsonProperty(value = "event_size")
    private int size;
    @JsonProperty(value = "event_details")
    private String details;

    public Event(){}

    public Event(EventSummaryDTO summary) {
        setEventId(summary.getEventId());
        setDate(summary.getDate());
        setType(summary.getType());
        setSize(summary.getSize());
        setSummary(summary.getSummary());
    }

    public Event(EventSummaryDTO summary, String details) {
        setEventId(summary.getEventId());
        setDate(summary.getDate());
        setType(summary.getType());
        setSize(summary.getSize());
        setSummary(summary.getSummary());
        setDetails(details);
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
