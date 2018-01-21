package com.wooyoo.blog.jackson.annotation.tutorial.jacksonmixinannotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooyoo.blog.jackson.annotation.tutorial.jsonignoretype.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MixInForIgnoreTypeTest {

    @Test
    public void whenSerializingUsingMixInAnnotation_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(1, "book", null);

        String result = new ObjectMapper().writeValueAsString(item);
        assertThat(result, containsString("owner"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(User.class, MyMixInForIgnoreType.class); // 动态添加注解

        result = mapper.writeValueAsString(item);
        assertThat(result, not(containsString("owner")));
    }

}