package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.stereotype.Component;

@Component
public class RejectReceiver {
    public void rejectMessage(String message) throws Exception {
        throw new Exception("Reject message: " + message);
    }
}
