package com.wooyoo.blog.jackson.annotation.tutorial.jsonreference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonReferenceTest {

    /**
     * 解决循环依赖的问题
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect()
            throws JsonProcessingException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book", user);
        user.addItem(item);

        String result = new ObjectMapper().writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item));
    }
}