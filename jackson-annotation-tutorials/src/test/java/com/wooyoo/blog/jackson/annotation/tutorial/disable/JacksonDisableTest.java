package com.wooyoo.blog.jackson.annotation.tutorial.disable;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class JacksonDisableTest {

    @Test
    public void whenDisablingAllAnnotations_thenAllDisabled()
            throws IOException {
        MyBean bean = new MyBean(1, null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        String result = mapper.writeValueAsString(bean);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("name"));

        System.out.println(result);
    }

}