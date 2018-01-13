package com.wooyoo.blog.jackson.annotation.tutorial.jsonanygetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JsonAnyGetterTest {
    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect()
            throws JsonProcessingException {

        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("attr1"));
        assertThat(result, containsString("val1"));
    }
}