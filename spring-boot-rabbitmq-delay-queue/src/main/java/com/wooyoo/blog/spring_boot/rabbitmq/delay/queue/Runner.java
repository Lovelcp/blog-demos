package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void run(String... args) throws Exception {
        testDelayQueuePerMessage();
        testDelayQueuePerQueue();
    }

    public void testDelayQueuePerMessage() {
        System.out.println("Sending message to delay_queue_per_message...");
        for (int i = 1; i < 4; i++) {
            int expiration = i * 1000;
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_MESSAGE_NAME,
                    (Object) ("Message From delay_queue_per_message with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
        }
    }

    public void testDelayQueuePerQueue() {
        System.out.println("Sending message to delay_queue_per_queue...");
        for (int i = 1; i < 4; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_QUEUE_NAME,
                    ("Message From delay_queue_per_queue with expiration " + QueueConfig.QUEUE_EXPIRATION));
        }
    }
}
