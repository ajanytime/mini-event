package com.paymentscanada.controller;

import com.paymentscanada.model.command.EventCommand;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
import com.paymentscanada.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// had to make do with this implementation for now as spring HATEOAS is buggy
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
public class EventResource {

    @Autowired
    EventService eventService;

    @GetMapping(path = "/{eventId}", produces = {"application/hal+json"})
    public Resource<EventDetailsDTO> get(@PathVariable String eventId) {
        EventDetailsDTO event = eventService.get(eventId);
        event.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(eventId)).withRel("target_url"));
        return new Resource<>(event);
    }

    @PostMapping(produces = {"application/hal+json"})
    public Resources<EventSummaryDTO> search(@RequestBody EventCommand command) {

        LocalDate start = command.getStart();
        LocalDate end = command.getEnd();

        List<EventSummaryDTO> summaries = eventService.find(start, end);

        //add hyper links
        summaries.forEach((summary) ->
                summary.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(summary.getEventId())).withRel("target_url"))
        );

        Link link = linkTo(EventResource.class).withSelfRel();

        return new Resources<EventSummaryDTO>(summaries, link);
    }
}
