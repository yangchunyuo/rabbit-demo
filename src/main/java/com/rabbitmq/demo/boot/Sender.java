package com.rabbitmq.demo.boot;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * spring boot producer
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息到队列
     * @param queueName 队列名称
     * @param body 消息体
     */
    public void send (String queueName, String body) {
        amqpTemplate.convertAndSend(queueName, body);
    }

    /**
     * 发送 设定有存活时间的消息
     * @param queueName 队列
     * @param body 消息体
     * @param timeout 过期时间 毫秒
     */
    public void send (String queueName, String body, Integer timeout) {    	
        // 消息配置
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设定 存活时间
                message.getMessageProperties().setExpiration(timeout.toString());
                return message;
            }
        };
        // 发送消息
        amqpTemplate.convertAndSend(queueName, (Object) body, processor);
        
        /**
        amqpTemplate.convertAndSend(exchangeName, routingKey, message, (msg) -> {
            msg.getMessageProperties().setExpiration(expiration.toString());// 设定过期时间
            return msg;
        });
        */
    }

    /**
     * 指定通道和队列去发送消息
     * @param exchangeName 通道名称
     * @param queueName 队列名称
     * @param body 消息
     */
    public void send (String exchangeName, String queueName, String body) {
        amqpTemplate.convertAndSend(exchangeName, queueName, body);
    }

}
