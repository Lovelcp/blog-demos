package com.wooyoo.blog.jackson.annotation.tutorial.jsondeserialize;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wooyoo.blog.jackson.annotation.tutorial.jsonserialize.CustomDateSerializer;

import java.util.Date;

public class Event {
    public String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date eventDate;
}
