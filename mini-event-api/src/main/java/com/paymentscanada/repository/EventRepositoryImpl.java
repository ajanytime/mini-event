package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String EVENT_SUMMARY = "event_summary";
    private static final String EVENT_DETAILS = "event_details";

    private RedisTemplate<String, EventSummaryDTO> redisTemplate;
    private HashOperations hashOperations;

    public EventRepositoryImpl(RedisTemplate<String, EventSummaryDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Event event) {
        hashOperations.put(EVENT_SUMMARY, event.getEventId(), new EventSummaryDTO(event));
        hashOperations.put(EVENT_DETAILS, event.getEventId(), event.getDetails());
    }

    @Override
    public EventDetailsDTO get(String eventId) {
        EventSummaryDTO summary = (EventSummaryDTO) hashOperations.get(EVENT_SUMMARY, eventId);
        String details = (String) hashOperations.get(EVENT_DETAILS, eventId);
        return new EventDetailsDTO(summary, details);
    }

    @Override
    public Map<String, EventSummaryDTO> getAll() {
        return hashOperations.entries(EVENT_SUMMARY);
    }
}
