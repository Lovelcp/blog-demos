package com.wooyoo.blog.jackson.annotation.tutorial.jsonignoreproperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonIgnorePropertiesTest {

    @Test
    public void whenSerializingUsingJsonIgnoreProperties_thenCorrect()
            throws JsonProcessingException {

        BeanWithIgnore bean = new BeanWithIgnore(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean));
    }

}