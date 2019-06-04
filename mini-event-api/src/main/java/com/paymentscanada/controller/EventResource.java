package com.paymentscanada.controller;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventSummary;
import com.paymentscanada.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventResource {

    @Autowired
    EventService eventService;

    @RequestMapping("")
    public List<EventSummary> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable String id) {
        return eventService.get(id);
    }
}
