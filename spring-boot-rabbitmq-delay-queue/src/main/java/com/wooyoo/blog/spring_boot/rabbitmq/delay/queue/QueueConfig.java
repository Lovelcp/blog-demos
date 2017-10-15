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
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 每个message可以控制自己的失效时间
     */
    final static String DELAY_QUEUE_PER_MESSAGE_NAME = "delay_queue_per_message";

    /**
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 队列里所有的message都有统一的失效时间
     */
    final static String DELAY_QUEUE_PER_QUEUE_NAME = "delay_queue_per_queue";
    final static int QUEUE_EXPIRATION = 4000;

    /**
     * 发送到该队列的message会被consumer拒绝，且不会被requeue，因为requeue被设置为false
     */
    final static String DELAY_QUEUE_REJECT_NAME = "delay_queue_reject";

    /**
     * message失效后进入到的队列
     */
    final static String DELAY_PROCESS_QUEUE_NAME = "delay_process_queue";

    final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    @Bean
    Queue delayQueuePerMessage() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // dead letter发送到的exchange
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .build();
    }

    @Bean
    Queue delayQueuePerQueue() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // dead letter发送到的exchange
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .withArgument("x-message-ttl", QUEUE_EXPIRATION)
                           .build();
    }

    @Bean
    Queue delayQueueReject() {
        return QueueBuilder.durable(DELAY_QUEUE_REJECT_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // dead letter发送到的exchange
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .build();
    }

    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME)
                           .build();
    }

    @Bean
    Binding binding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                             .to(delayExchange)
                             .with(DELAY_PROCESS_QUEUE_NAME);
    }

    /**
     * Listener即消费者
     *
     * @param connectionFactory
     * @param processListenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory, MessageListenerAdapter processListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_PROCESS_QUEUE_NAME); // 监听delay_process_queue
        container.setMessageListener(processListenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer rejectContainer(ConnectionFactory connectionFactory, MessageListenerAdapter rejectListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_QUEUE_REJECT_NAME); // 监听delay_queue_reject
        container.setMessageListener(rejectListenerAdapter);
        container.setDefaultRequeueRejected(false); // 关键!
        return container;
    }

    @Bean
    MessageListenerAdapter processListenerAdapter(ProcessReceiver processReceiver) {
        return new MessageListenerAdapter(processReceiver, "processMessage");
    }

    @Bean
    MessageListenerAdapter rejectListenerAdapter(RejectReceiver rejectReceiver) {
        return new MessageListenerAdapter(rejectReceiver, "rejectMessage");
    }
}
