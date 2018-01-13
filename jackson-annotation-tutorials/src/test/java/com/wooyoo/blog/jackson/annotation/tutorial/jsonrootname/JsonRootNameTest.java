package com.wooyoo.blog.jackson.annotation.tutorial.jsonrootname;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class JsonRootNameTest {
    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
            throws JsonProcessingException {

        UserWithRoot user = new UserWithRoot(1, "John");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);

        assertThat(result, containsString("John"));
        assertThat(result, containsString("user"));

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
    }
}