package com.wooyoo.blog.spring_rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RabbitMQConfig.class)
public class RabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.working_name}")
    private String workingQueueName;

    @Test
    public void test() {
        for (int i = 0; i < 2; i++) {
            rabbitTemplate.convertAndSend(workingQueueName, i);
        }

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
