package com.rabbitmq.demo.boot;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit configuration
 */
@Configuration
public class RabbitConfig {

    public static final String SPRING_QUEUE_NAME = "spring_queue";
    public static final String DEAD_LETTER_QUEUE_NAME = "dead_letter";

    /** 测试队列 */
    @Bean
    Queue testQueue() {
        //.withArgument("x-message-ttl", 10 * 1000)// 消息存活时间 10s
        return QueueBuilder.durable(SPRING_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "amq.direct")// 死信 交换器 名称
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_NAME)// 死信 队列 名称
                .autoDelete()// 无人使用 自动删除
                .build();
    }

    /** 死信队列 */
    @Bean
    Queue deadLetterQueue () {
        return new Queue(DEAD_LETTER_QUEUE_NAME,true,false,true);
    }

    /** 直连 交换机 */
    @Bean
    DirectExchange directExchange () {
        return new DirectExchange("amq.direct");
    }
    /** 直连交换机 绑定队列 */
    @Bean
    Binding bindingDirectExchange () {
        return BindingBuilder.bind(testQueue()).to(directExchange()).with(testQueue().getName());
    }
    @Bean
    Binding leadLetterExchange () {
        return BindingBuilder.bind(deadLetterQueue()).to(directExchange()).with(deadLetterQueue().getName());
    }


    /** 话题 交换机 */
    @Bean
    TopicExchange topicExchange () {
        return new TopicExchange("amq.topic");
    }
    /** 话题交换机 绑定队列 */
    @Bean
    FanoutExchange fanoutExchange () {
        return new FanoutExchange("amq.fanout");
    }

    /** 广播 交换机 */
    @Bean
    Binding bindingTopicExchange () {
        return BindingBuilder.bind(testQueue()).to(topicExchange()).with("topic.#");
    }
    /** 广播交换机 绑定队列 */
    @Bean
    Binding bindingFanoutExchange () {
        return BindingBuilder.bind(testQueue()).to(fanoutExchange());
    }

}
