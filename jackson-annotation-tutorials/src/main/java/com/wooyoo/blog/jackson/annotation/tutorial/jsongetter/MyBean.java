package com.wooyoo.blog.jackson.annotation.tutorial.jsongetter;

import com.fasterxml.jackson.annotation.JsonGetter;

public class MyBean {
    public int id;
    private String name;

    @JsonGetter
    public String getTheName() {
        return name;
    }

    public MyBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
