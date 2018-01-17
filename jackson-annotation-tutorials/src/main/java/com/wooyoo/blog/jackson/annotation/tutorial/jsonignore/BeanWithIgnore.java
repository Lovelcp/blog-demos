package com.wooyoo.blog.jackson.annotation.tutorial.jsonignore;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BeanWithIgnore {
    @JsonIgnore
    public int id;

    public String name;

    public BeanWithIgnore(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
