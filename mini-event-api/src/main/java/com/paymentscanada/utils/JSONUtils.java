package com.paymentscanada.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.paymentscanada.model.Event;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public final class JSONUtils {

    public static List<Event> fromJson(URL url) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);

        List<Event> events = mapper.readValue(url.openStream(), new TypeReference<List<Event>>(){});

        events.forEach((event -> {
            event.setEventId(Hashing.murmur3_32().hashBytes(event.getDetails().getBytes()).toString());
        }));

        return events;
    }
}
