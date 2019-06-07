package com.paymentscanada.repository;

import com.paymentscanada.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void before() {
        eventRepository.deleteAll();
    }

    @Test
    public void testSaveSingle() {
        Event event = new Event();
        event.setEventId("test_id");
        event.setDetails("test_details");
        event.setSize(6);
        event.setType("test_type");
        event.setDate(LocalDate.now());

        eventRepository.save(event);

        Event saved = eventRepository.get(event.getEventId());

        assertNotNull(saved);
        assertEquals(saved.getEventId(), event.getEventId());
    }

    @Test
    public void testSaveMultiple() {
        Event event1 = new Event();
        event1.setEventId("test_id1");
        event1.setDetails("test_details1");
        event1.setSize(6);
        event1.setType("test_type1");
        event1.setDate(LocalDate.now());

        Event event2 = new Event();
        event2.setEventId("test_id2");
        event2.setDetails("test_details2");
        event2.setSize(6);
        event2.setType("test_type3");
        event2.setDate(LocalDate.now());

        eventRepository.save(event1);
        eventRepository.save(event2);

        Event saved1 = eventRepository.get(event1.getEventId());
        Event saved2 = eventRepository.get(event2.getEventId());

        int size = eventRepository.getAll().size();

        assertNotNull(saved1);
        assertNotNull(saved2);

        assertEquals(2, size);

    }

    @Test
    public void testGetOne() {
        Event event = new Event();
        event.setEventId("test_id");
        event.setDetails("test_details");
        event.setSize(6);
        event.setType("test_type");
        event.setDate(LocalDate.now());

        eventRepository.save(event);

        Event saved = eventRepository.get(event.getEventId());

        assertEquals(event.getEventId(), saved.getEventId());
        assertEquals(event.getSize(), saved.getSize());
        assertEquals(event.getType(), saved.getType());
    }

    @Test
    public void testDeleteAll() {
        Event event1 = new Event();
        event1.setEventId("test_id1");
        event1.setDetails("test_details1");
        event1.setSize(6);
        event1.setType("test_type1");
        event1.setDate(LocalDate.now());

        Event event2 = new Event();
        event2.setEventId("test_id2");
        event2.setDetails("test_details2");
        event2.setSize(6);
        event2.setType("test_type3");
        event2.setDate(LocalDate.now());

        eventRepository.save(event1);
        eventRepository.save(event2);

        Event saved1 = eventRepository.get(event1.getEventId());
        Event saved2 = eventRepository.get(event2.getEventId());

        int size = eventRepository.getAll().size();

        assertEquals(2, eventRepository.getAll().size());

        eventRepository.deleteAll();

        assertEquals(0, eventRepository.getAll().size());
    }


}
