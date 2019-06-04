package com.paymentscanada.utils;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.EventSummary;
import com.paymentscanada.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class InitialLoadListener {

    public static final String dataFile = "assignment_data_short.json";
    private static final Logger logger = LoggerFactory.getLogger(InitialLoadListener.class);

    @Autowired
    private EventRepository eventRepository;

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        init();
    }

    private void init() {
        Map<String, EventSummary> map = eventRepository.getAll();
        if (map == null || map.size() == 0) {
            logger.info("Data not loaded, start initial load ...");
            try {
                URL dataUrl = InitialLoadListener.class.getClassLoader().getResource(dataFile);
                List<Event> events = JSONUtils.fromJson(dataUrl);
                events.forEach((event -> {
                    eventRepository.save(event);
                }));
                logger.info("Done ...");
            } catch (Exception e) {
                logger.error("Error occured while loading initial data ...");
                e.printStackTrace();

            }

        }
    }
}
