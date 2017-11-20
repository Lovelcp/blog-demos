package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 设置队列消息的过期时间
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {
    private final Long ttl; // 毫秒

    public ExpirationMessagePostProcessor(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties()
               .setExpiration(ttl.toString()); // 设置per-message的失效时间
        return message;
    }
}
