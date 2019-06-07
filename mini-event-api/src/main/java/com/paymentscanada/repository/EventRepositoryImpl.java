package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventSummaryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Value("${event.summary.key}")
    private String EVENT_SUMMARY_KEY;

    @Value("${event.details.key}")
    private String EVENT_DETAILS_KEY;

    private RedisTemplate<String, EventSummaryDTO> redisTemplate;
    private HashOperations hashOperations;

    public EventRepositoryImpl(RedisTemplate<String, EventSummaryDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Event event) {
        hashOperations.put(EVENT_DETAILS_KEY, event.getEventId(), event.getDetails());
        event.setDetails(null);
        hashOperations.put(EVENT_SUMMARY_KEY, event.getEventId(), event);
    }

    /**
     * This method could have been deprecated after adding
     * async rest controller support, but we are keeping it for now
     * to support test cases
     * */
    @Override
    public Event get(String eventId) {
        Event event = (Event) hashOperations.get(EVENT_SUMMARY_KEY, eventId);
        String details = (String) hashOperations.get(EVENT_DETAILS_KEY, eventId);
        if (event != null && details != null) {
            event.setDetails(details);
        }
        return event;
    }

    @Override
    public String getDetails(String eventId) {
        return (String) hashOperations.get(EVENT_DETAILS_KEY, eventId);
    }

    @Override
    public Event getSummary(String eventId) {
        return (Event) hashOperations.get(EVENT_SUMMARY_KEY, eventId);
    }

    @Override
    public Map<String, Event> getAll() {
        return hashOperations.entries(EVENT_SUMMARY_KEY);
    }

    @Override
    public void deleteAll() {
        redisTemplate.delete(EVENT_SUMMARY_KEY);
        redisTemplate.delete(EVENT_DETAILS_KEY);
    }
}
