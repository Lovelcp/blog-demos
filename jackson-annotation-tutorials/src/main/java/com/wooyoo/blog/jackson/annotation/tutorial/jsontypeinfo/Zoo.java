package com.wooyoo.blog.jackson.annotation.tutorial.jsontypeinfo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Zoo {
    public Animal animal;

    public Zoo() {
    }

    public Zoo(Animal animal) {
        this.animal = animal;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({@JsonSubTypes.Type(value = Dog.class, name = "dog"), @JsonSubTypes.Type(value = Cat.class, name = "cat")})
    public static class Animal {
        public String name;

        public Animal() {
        }

        public Animal(String name) {
            this.name = name;
        }
    }

    @JsonTypeName("dog")
    public static class Dog extends Animal {
        public double barkVolume;

        public Dog(String name) {
            super(name);
        }
    }

    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;

        public Cat() {
        }

        public Cat(String name) {
            super(name);
        }
    }
}
