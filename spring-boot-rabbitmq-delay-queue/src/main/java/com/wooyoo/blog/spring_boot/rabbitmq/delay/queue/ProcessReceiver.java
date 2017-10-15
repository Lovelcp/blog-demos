package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.stereotype.Component;

@Component
public class ProcessReceiver {
    public void processMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
