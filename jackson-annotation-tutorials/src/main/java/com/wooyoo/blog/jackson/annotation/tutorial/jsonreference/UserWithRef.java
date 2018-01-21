package com.wooyoo.blog.jackson.annotation.tutorial.jsonreference;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.LinkedList;
import java.util.List;

public class UserWithRef {
    public int id;
    public String name;

    @JsonBackReference
    public List<ItemWithRef> userItems;

    public UserWithRef(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addItem(ItemWithRef item) {
        if (userItems == null) {
            userItems = new LinkedList<>();
        }

        userItems.add(item);
    }
}