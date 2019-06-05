package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventDetails;
import com.paymentscanada.model.EventSummary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final String EVENT_SUMMARY = "event_summary";
    private static final String EVENT_DETAILS = "event_details";

    private RedisTemplate<String, EventSummary> redisTemplate;
    private HashOperations hashOperations;

    public EventRepositoryImpl(RedisTemplate<String, EventSummary> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Event event) {
        hashOperations.put(EVENT_SUMMARY, event.getId(), new EventSummary(event));
        hashOperations.put(EVENT_DETAILS, event.getId(), event.getDetails());
    }

    @Override
    public EventDetails get(String id) {
        EventSummary summary = (EventSummary) hashOperations.get(EVENT_SUMMARY, id);
        String details = (String) hashOperations.get(EVENT_DETAILS, id);
        return new EventDetails(summary, details);
    }

    @Override
    public Map<String, EventSummary> getAll() {
        return hashOperations.entries(EVENT_SUMMARY);
    }
}
