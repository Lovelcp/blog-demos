package com.wooyoo.blog.jackson.annotation.tutorial.jsoninclude;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonIncludeTest {

    @Test
    public void whenSerializingUsingJsonInclude_thenCorrect()
            throws JsonProcessingException {

        MyBean bean = new MyBean(1, null);

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bean));
    }

}