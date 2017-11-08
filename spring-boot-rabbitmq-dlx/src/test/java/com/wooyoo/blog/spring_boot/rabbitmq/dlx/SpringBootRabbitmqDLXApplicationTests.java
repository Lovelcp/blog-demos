package com.wooyoo.blog.spring_boot.rabbitmq.dlx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqDLXApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDelayQueuePerMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            int expiration = i * 1000;
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_MESSAGE_NAME,
                    (Object) ("Message From delay_queue_per_message with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testDelayQueuePerQueue() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_QUEUE_NAME,
                    ("Message From delay_queue_per_queue with expiration " + QueueConfig.QUEUE_EXPIRATION));
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testDelayQueueReject() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_REJECT_NAME, "Message From delay_queue_reject");
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testDelayQueueMaxLength() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 6; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_MAX_LENGTH_NAME, "Message From delay_queue_max_length");
        }
        ProcessReceiver.latch.await();
    }
}
