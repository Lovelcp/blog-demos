package com.wooyoo.blog.jackson.annotation.tutorial.jsonformat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class JsonFormatTest {

    @Test
    public void whenSerializingUsingJsonFormat_thenCorrect()
            throws JsonProcessingException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);
        Event event = new Event("party", date);

        String result = new ObjectMapper().writeValueAsString(event);

        assertThat(result, containsString(toParse));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event));
    }
}