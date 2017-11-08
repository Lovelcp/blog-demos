package com.wooyoo.blog.spring_boot.rabbitmq.dlx;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ProcessReceiver {
    public static CountDownLatch latch;

    public void processMessage(String message) {
        System.out.println("Received <" + message + ">");
        if (latch != null) {
            latch.countDown();
        }
    }
}
