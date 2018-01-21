package com.wooyoo.blog.jackson.annotation.tutorial.jsontypeinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class JsonTypeInfoTest {

    @Test
    public void whenSerializingPolymorphic_thenCorrect()
            throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("lacy");
        Zoo zoo = new Zoo(dog);

        String result = new ObjectMapper()
                .writeValueAsString(zoo);

        assertThat(result, containsString("type"));
        assertThat(result, containsString("dog"));

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(zoo));
    }

    /**
     * 反序列化的时候需要默认构造函数，否则会有问题
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingPolymorphic_thenCorrect()
            throws IOException {
        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

        Zoo zoo = new ObjectMapper()
                .readerFor(Zoo.class)
                .readValue(json);

        assertEquals("lacy", zoo.animal.name);
        assertEquals(Zoo.Cat.class, zoo.animal.getClass());

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(zoo));
    }
}