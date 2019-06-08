package com.paymentscanada.service;

import com.paymentscanada.config.EmbeddedRedis;
import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventDetailsDTO;
import com.paymentscanada.model.dto.EventSummaryDTO;
import com.paymentscanada.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes = EmbeddedRedis.class)
public class EventServiceTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;


    @Test
    public void testGetOne() {

        EventDetailsDTO one = eventService.get("test_id1");
        assertNotNull(one);
        assertEquals("test_id1", one.getEventId());
        assertEquals("test_details1", one.getDetails());
        assertEquals(LocalDate.of(2011, Month.JANUARY, 1), one.getDate());
    }

    @Test
    public void testFindWithOnlyStartDate() throws InterruptedException, ExecutionException {
        LocalDate start = LocalDate.of(2011, Month.JANUARY, 1);
        List<EventSummaryDTO> events = eventService.find(start, null);
        assertNotNull(events);
        assertEquals(1, events.size());
    }

    @Test
    public void testFindWithBothStartAndEndDate() throws InterruptedException, ExecutionException  {
        LocalDate start = LocalDate.of(2011, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2014, Month.DECEMBER, 1);
        List<EventSummaryDTO> events = eventService.find(start, end);
        assertNotNull(events);
        assertEquals(3, events.size());
    }

    @Test
    public void testFindWithOutOfRangeDates() throws InterruptedException, ExecutionException {
        LocalDate start = LocalDate.of(2019, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2020, Month.DECEMBER, 1);
        List<EventSummaryDTO> events = eventService.find(start, end);
        assertNotNull(events);
        assertEquals(0, events.size());
    }

    @Test
    public void testFindWithNullStartNullEnd() throws InterruptedException, ExecutionException {
        List<EventSummaryDTO> events = eventService.find(null, null);
        assertNotNull(events);
        assertEquals(5, events.size());
    }


    @Before
    public void before() {
        Event event1 = new Event();
        event1.setEventId("test_id1");
        event1.setDetails("test_details1");
        event1.setSize(6);
        event1.setType("test_type1");
        event1.setDate(LocalDate.of(2011, Month.JANUARY, 1));

        Event event2 = new Event();
        event2.setEventId("test_id2");
        event2.setDetails("test_details2");
        event2.setSize(6);
        event2.setType("test_type2");
        event2.setDate(LocalDate.of(2012, Month.JANUARY, 1));

        Event event3 = new Event();
        event3.setEventId("test_id3");
        event3.setDetails("test_details3");
        event3.setSize(6);
        event3.setType("test_type3");
        event3.setDate(LocalDate.of(2013, Month.JANUARY, 1));

        Event event4 = new Event();
        event4.setEventId("test_id4");
        event4.setDetails("test_details4");
        event4.setSize(6);
        event4.setType("test_type4");
        event4.setDate(LocalDate.of(2014, Month.JANUARY, 1));

        Event event5 = new Event();
        event5.setEventId("test_id5");
        event5.setDetails("test_details5");
        event5.setSize(6);
        event5.setType("test_type5");
        event5.setDate(LocalDate.of(2015, Month.JANUARY, 1));

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
        eventRepository.save(event4);
        eventRepository.save(event5);
    }

    @After
    public void after() {
        eventRepository.deleteAll();
    }

}
