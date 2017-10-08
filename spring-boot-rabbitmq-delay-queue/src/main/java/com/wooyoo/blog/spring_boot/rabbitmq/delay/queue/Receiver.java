package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
