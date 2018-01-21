package com.wooyoo.blog.spring.circular.dependency.constructor;

import org.springframework.stereotype.Component;

@Component
public class ConA {

    private ConB conB;

    public ConA(ConB conB) {
        this.conB = conB;
    }
}
