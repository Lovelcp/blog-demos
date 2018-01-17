package com.wooyoo.blog.jackson.annotation.tutorial.jsonsetter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonSetterTest {

    @Test
    public void whenDeserializingUsingJsonSetter_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"name\":\"My bean\"}";

        MyBean bean = new ObjectMapper()
                .readerFor(MyBean.class)
                .readValue(json);
        assertEquals("My bean", bean.getTheName());
    }

}