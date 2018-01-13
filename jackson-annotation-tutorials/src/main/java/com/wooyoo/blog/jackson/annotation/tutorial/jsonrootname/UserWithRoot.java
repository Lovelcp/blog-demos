package com.wooyoo.blog.jackson.annotation.tutorial.jsonrootname;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public class UserWithRoot {
    public int id;
    public String name;

    public UserWithRoot(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
