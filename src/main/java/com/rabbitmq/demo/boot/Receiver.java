package com.rabbitmq.demo.boot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * spring boot consumer
 */
@Component
public class Receiver {

    @RabbitListener(queues = RabbitConfig.DEAD_LETTER_QUEUE_NAME)
    @RabbitHandler
    void process (String body) {
        System.out.println("process 收到消息：" + body);
    }

    @RabbitListener(queues = RabbitConfig.SPRING_QUEUE_NAME)
    @RabbitHandler
    void processHelloWord(String message) {
        System.out.println("processHelloWord 收到消息：" + message);
        Integer.valueOf("ss");
    }

}
