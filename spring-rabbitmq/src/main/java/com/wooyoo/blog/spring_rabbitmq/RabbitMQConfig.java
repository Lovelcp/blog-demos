package com.wooyoo.blog.spring_rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com.wooyoo.blog.spring_rabbitmq")
public class RabbitMQConfig {

    @Value("${queue.host}")
    private String host;

    @Value("${queue.port}")
    private Integer port;

    @Value("${queue.username}")
    private String username;

    @Value("${queue.password}")
    private String password;

    @Value("${queue.working_name}")
    private String workingQueueName;

    @Value("${queue.failed_name}")
    private String failedQueueName;

    @Value("${queue.concurrency}")
    private Integer consumeConcurrency;

    @Value("${queue.connection.timeout}")
    private Integer connectionTimeout;

    @Value("${queue.connection.min_threads}")
    private Integer connectionMinThreads;

    @Value("${queue.connection.max_threads}")
    private Integer connectionMaxThreads;

    @Value("${queue.connection.max_queued}")
    private Integer connectionMaxQueued;

    private static final String CONNECTION_THREAD_PREFIX = "rabbitmq-connection-thread";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setConnectionTimeout(connectionTimeout);

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(CONNECTION_THREAD_PREFIX);
        executor.setCorePoolSize(connectionMinThreads);
        executor.setMaxPoolSize(connectionMaxThreads);
        executor.setQueueCapacity(connectionMaxQueued);
        executor.initialize();
        connectionFactory.setExecutor(executor);
        return connectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, Consumer consumer) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueueNames(workingQueueName);
        messageListenerContainer.setMessageListener(consumer);
        messageListenerContainer.setConcurrentConsumers(consumeConcurrency); // 并发消费信息的数量
        return messageListenerContainer;
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * 工作队列，默认正常情况下从该队列消费消息
     *
     * @return
     */
    @Bean
    Queue workingQueue() {
        return new Queue(workingQueueName, true);
    }

    /**
     * 失败队列，消息消费失败则入该队列
     *
     * @return
     */
    @Bean
    Queue failedQueue() {
        return new Queue(failedQueueName, true);
    }
}
