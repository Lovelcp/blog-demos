package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqDelayQueueApplication {

    final static String queueName = "test1";

    /**
     * 发送到该队列的job会在一段时间后过期进入到delay_process_queue
     */
    final static String delayQueueName = "delay_queue";

    /**
     * 进入到该队列的任务会被消费
     */
    final static String delayProcessQueueName = "delay_process_queue";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange("delay_exchange");
    }

    @Bean
    Queue delayQueue() {
        return QueueBuilder.durable(delayQueueName)
                           .withArgument("x-dead-letter-routing-exchange", "delay_exchange")
                           .withArgument("x-dead-letter-routing-key", "delay_process_queue")
                           .build();
    }

    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(delayProcessQueueName)
                           .build();
    }

    @Bean
    Binding newBinding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                             .to(delayExchange)
                             .with("delay_process_queue");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                             .to(exchange)
                             .with(queueName);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitmqDelayQueueApplication.class, args);
    }
}
