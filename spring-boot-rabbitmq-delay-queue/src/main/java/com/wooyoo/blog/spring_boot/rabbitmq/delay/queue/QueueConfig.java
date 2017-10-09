package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    /**
     * 发送到该队列的job会在一段时间后过期进入到delay_process_queue
     */
    final static String delayQueueName = "delay_queue";

    /**
     * 进入到该队列的任务会被消费
     */
    final static String delayProcessQueueName = "delay_process_queue";

    final static String delayExchangeName = "delay_exchange";

    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(delayExchangeName);
    }

    @Bean
    Queue delayQueue() {
        return QueueBuilder.durable(delayQueueName)
                           .withArgument("x-dead-letter-exchange", delayExchangeName) // dead letter发送到的exchange
                           .withArgument("x-dead-letter-routing-key", delayProcessQueueName) // dead letter携带的routing key
                           .build();
    }

    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(delayProcessQueueName)
                           .build();
    }

    @Bean
    Binding binding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                             .to(delayExchange)
                             .with(delayProcessQueueName);
    }

    /**
     * Listener即消费者
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(delayProcessQueueName); // 监听delay_process_queue
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
