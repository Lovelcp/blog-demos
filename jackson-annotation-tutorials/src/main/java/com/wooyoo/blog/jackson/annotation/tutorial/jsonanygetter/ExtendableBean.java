package com.wooyoo.blog.jackson.annotation.tutorial.jsonanygetter;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class ExtendableBean {
    public String name;
    private Map<String, String> properties;

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public ExtendableBean(String name) {
        this.name = name;
    }

    public void add(String key, String value) {
        if (properties == null) {
            properties = new HashMap<>();
        }
        properties.put(key, value);
    }
}
