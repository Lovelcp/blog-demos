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
     * 发送到该队列的message会被consumer拒绝，且不会被requeue，因为requeue被设置为false。被拒绝后会进入delay_process_queue
     */
    final static String DELAY_QUEUE_REJECT_NAME = "delay_queue_reject";

    /**
     * 发送到该队列的message个数超过队列的最大长度之后，新的message会进入到delay_process_queue
     */
    final static String DELAY_QUEUE_MAX_LENGTH_NAME = "delay_queue_max_length";

    /**
     * message失效后进入的队列，也就是实际的消费队列
     */
    final static String DELAY_PROCESS_QUEUE_NAME = "delay_process_queue";

    /**
     * DLX
     */
    final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    /**
     * 创建DLX
     *
     * @return
     */
    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 创建delay_queue_per_message队列
     *
     * @return
     */
    @Bean
    Queue delayQueuePerMessage() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX，dead letter发送到的exchange
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .build();
    }

    /**
     * 创建delay_queue_per_queue队列
     *
     * @return
     */
    @Bean
    Queue delayQueuePerQueue() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .withArgument("x-message-ttl", QUEUE_EXPIRATION) // 设置队列的过期时间
                           .build();
    }

    /**
     * 创建delay_queue_reject队列
     *
     * @return
     */
    @Bean
    Queue delayQueueReject() {
        return QueueBuilder.durable(DELAY_QUEUE_REJECT_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .build();
    }

    @Bean
    Queue delayQueueMaxLength() {
        return QueueBuilder.durable(DELAY_QUEUE_MAX_LENGTH_NAME)
                           .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                           .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                           .withArgument("x-max-length", 3)
                           .build();
    }

    /**
     * 创建delay_process_queue队列，也就是实际消费队列
     *
     * @return
     */
    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME)
                           .build();
    }

    /**
     * 将DLX绑定到实际消费队列
     *
     * @param delayProcessQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding binding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                             .to(delayExchange)
                             .with(DELAY_PROCESS_QUEUE_NAME);
    }

    /**
     * 定义delay_process_queue队列的Listener Container
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

    /**
     * 定义delay_queue_reject队列的Listener Container
     *
     * @param connectionFactory
     * @param rejectListenerAdapter
     * @return
     */
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
