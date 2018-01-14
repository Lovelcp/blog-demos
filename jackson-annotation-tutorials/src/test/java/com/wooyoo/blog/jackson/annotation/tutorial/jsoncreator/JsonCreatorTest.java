package com.wooyoo.blog.jackson.annotation.tutorial.jsoncreator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonCreatorTest {

    @Test
    public void whenDeserializingUsingJsonCreator_thenCorrect()
            throws IOException {
        String json = "{\"id\":1,\"theName\":\"My bean\"}";
        BeanWithCreator bean = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue(json);
        assertEquals("My bean", bean.name);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean));
    }

}