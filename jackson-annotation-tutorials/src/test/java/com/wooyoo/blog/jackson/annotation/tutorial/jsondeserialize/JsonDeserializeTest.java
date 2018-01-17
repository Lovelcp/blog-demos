package com.wooyoo.blog.jackson.annotation.tutorial.jsondeserialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class JsonDeserializeTest {

    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect()
            throws IOException {

        String json
                = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        SimpleDateFormat df
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Event event = new ObjectMapper()
                .readerFor(Event.class)
                .readValue(json);

        assertEquals(
                "20-12-2014 02:30:00", df.format(event.eventDate));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event));
    }
}