package com.wooyoo.blog.jackson.annotation.tutorial.jsonview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonViewTest {

    /**
     * 定义多种输出视图，可以用在DTO简单转换等场景上
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonView_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(2, "book", "John");

        String result = new ObjectMapper()
                .writerWithView(Views.Public.class)
                .writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("2"));
        assertThat(result, not(containsString("John")));

        System.out.println(result);
    }

}