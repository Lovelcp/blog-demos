package com.wooyoo.blog.spring_rabbitmq;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        context.start();
    }
}
