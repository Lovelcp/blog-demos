package com.wooyoo.blog.spring.circular.dependency.constructor;

import org.springframework.stereotype.Component;

@Component
public class ConB {

    private ConA conA;

    public ConB(ConA conA) {
        this.conA = conA;
    }
}
