package com.wooyoo.blog.jackson.annotation.tutorial.jacksonmixinannotation;

import com.wooyoo.blog.jackson.annotation.tutorial.jsonignoretype.User;

public class Item {
    public int id;
    public String itemName;
    public User owner;

    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}