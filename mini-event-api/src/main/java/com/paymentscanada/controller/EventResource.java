package com.paymentscanada.controller;

import com.paymentscanada.model.Command;
import com.paymentscanada.model.EventDetails;
import com.paymentscanada.model.EventSummary;
import com.paymentscanada.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
public class EventResource {

    @Autowired
    EventService eventService;

    @GetMapping("/{id}")
    public EventDetails get(@PathVariable String id) {
        return eventService.get(id);
    }

    @PostMapping
    public List<EventSummary> search(@RequestBody Command command) {

        LocalDate start = command.getStart();
        LocalDate end = command.getEnd();

        return eventService.find(start, end);
    }
}
