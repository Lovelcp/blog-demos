package com.wooyoo.blog.jackson.annotation.tutorial.jsonignoretype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooyoo.blog.jackson.annotation.tutorial.jsonignore.BeanWithIgnore;
import org.junit.Test;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class JsonIgnoreTypeTest {

    @Test
    public void whenSerializingUsingJsonIgnoreType_thenCorrect()
            throws JsonProcessingException, ParseException {

        User.Name name = new User.Name("John", "Doe");
        User user = new User(1, name);

        String result = new ObjectMapper()
                .writeValueAsString(user);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
        assertThat(result, not(containsString("John")));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
    }
}