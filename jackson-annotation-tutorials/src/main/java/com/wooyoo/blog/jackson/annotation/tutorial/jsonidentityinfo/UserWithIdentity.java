package com.wooyoo.blog.jackson.annotation.tutorial.jsonidentityinfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.LinkedList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserWithIdentity {
    public int id;
    public String name;
    public List<ItemWithIdentity> userItems;

    public UserWithIdentity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addItem(ItemWithIdentity item) {
        if (userItems == null) {
            userItems = new LinkedList<>();
        }

        userItems.add(item);
    }
}