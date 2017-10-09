package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private static final int EXPIRATION = 4000;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(QueueConfig.delayQueueName, (Object) "Hello from RabbitMQ!", new ExpirationMessagePostProcessor(EXPIRATION));
    }
}
