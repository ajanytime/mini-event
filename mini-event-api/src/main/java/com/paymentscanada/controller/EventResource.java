package com.paymentscanada.controller;

import com.paymentscanada.model.command.EventCommand;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
import com.paymentscanada.service.EventService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// had to make do with this implementation for now as spring HATEOAS is buggy
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin
public class EventResource {

    private EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/{eventId}", produces = {"application/hal+json"})
    public Resource<EventDetailsDTO> get(@PathVariable String eventId) throws InterruptedException, ExecutionException {

        // toying with Java 8 CompletableFuture
        CompletableFuture<EventSummaryDTO> summary = eventService.getSummary(eventId);
        CompletableFuture<String> details = eventService.getDetails(eventId);
        CompletableFuture.allOf(summary, details).join();
        EventDetailsDTO event = new EventDetailsDTO(summary.get(), details.get());
        //add hyper media links
        event.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(eventId)).withRel("target_url"));

        return new Resource<>(event);
    }

    @PostMapping(produces = {"application/hal+json"})
    public Resources<EventSummaryDTO> search(@RequestBody EventCommand command) throws InterruptedException, ExecutionException {

        LocalDate start = command.getStart();
        LocalDate end = command.getEnd();

        List<EventSummaryDTO> summaries = eventService.find(start, end);

        // add hyper media links
        addDetailsLinkHelper(summaries);
        Link link = linkTo(EventResource.class).withSelfRel();

        return new Resources<>(summaries, link);
    }

    private void addDetailsLinkHelper(List<EventSummaryDTO> summaries) throws InterruptedException, ExecutionException {
        // use good old for loop here as lambda expression has scope issue when working with exception handling
        for (EventSummaryDTO summary : summaries) {
            summary.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(summary.getEventId()))
                    .withRel("target_url"));
        }
    }
}
