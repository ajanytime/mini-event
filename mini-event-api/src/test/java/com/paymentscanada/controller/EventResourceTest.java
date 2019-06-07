package com.paymentscanada.controller;

import com.paymentscanada.config.EmbeddedRedis;
import com.paymentscanada.model.Event;
import com.paymentscanada.model.command.EventCommand;
import com.paymentscanada.repository.EventRepository;
import com.paymentscanada.utils.JSONUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes = EmbeddedRedis.class)
public class EventResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testGetOne() throws Exception {
        mockMvc.perform(
        get("/api/v1/events/test_id1")
                .accept(MediaTypes.HAL_JSON)
                .contentType(MediaTypes.HAL_JSON))
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetAll() throws Exception {
        EventCommand command = new EventCommand(null, null);
        String commandJson = JSONUtils.toJson(command);
        mockMvc.perform(
                post("/api/v1/events")
                        .accept(MediaTypes.HAL_JSON)
                        .content(commandJson)
                        .contentType(MediaTypes.HAL_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testMethodNotAllowed() throws Exception {
        mockMvc.perform(
                get("/api/v1/events")
                        .accept(MediaTypes.HAL_JSON)
                        .contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testResourceNotFound() throws Exception {
        mockMvc.perform(
                get("/api/v2/events")
                        .accept(MediaTypes.HAL_JSON)
                        .contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isNotFound());
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
