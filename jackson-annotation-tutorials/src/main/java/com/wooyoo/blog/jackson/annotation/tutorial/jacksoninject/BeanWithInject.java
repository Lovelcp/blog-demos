package com.wooyoo.blog.jackson.annotation.tutorial.jacksoninject;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {
    @JacksonInject
    public int id;
    public String name;
}
