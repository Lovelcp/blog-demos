package com.wooyoo.blog.jackson.annotation.tutorial.jsonunwrapped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonUnwrappedTest {

    /**
     * 打平属性
     *
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect()
            throws JsonProcessingException, ParseException {
        UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
        UnwrappedUser user = new UnwrappedUser(1, name);

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("name")));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
    }

}