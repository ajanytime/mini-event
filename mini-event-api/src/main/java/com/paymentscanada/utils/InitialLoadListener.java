package com.paymentscanada.utils;

import com.paymentscanada.model.Event;
import com.paymentscanada.model.dto.EventSummaryDTO;
import com.paymentscanada.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class InitialLoadListener {

    @Value("${data.file}")
    private String dataFile;

    private static final Logger logger = LoggerFactory.getLogger(InitialLoadListener.class);

    private EventRepository eventRepository;

    public InitialLoadListener(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        init();
    }

    private void init() {
        Map<String, Event> map = eventRepository.getAll();
        if (map == null || map.size() == 0) {
            logger.info("Data not loaded, start initial load ...");
            try {
                URL dataUrl = InitialLoadListener.class.getClassLoader().getResource(dataFile);
                List<Event> events = JSONUtils.fromJsonFile(dataUrl);
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
