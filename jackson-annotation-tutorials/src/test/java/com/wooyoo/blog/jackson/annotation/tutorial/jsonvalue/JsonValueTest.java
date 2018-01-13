package com.wooyoo.blog.jackson.annotation.tutorial.jsonvalue;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonValueTest {
    @Test
    public void whenSerializingUsingJsonValue_thenCorrect()
            throws JsonParseException, IOException {

        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);

        assertThat(enumAsString, is("\"Type A\""));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(TypeEnumWithValue.TYPE1));
    }
}