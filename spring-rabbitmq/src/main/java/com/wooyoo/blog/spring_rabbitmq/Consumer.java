package com.wooyoo.blog.spring_rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener {
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
