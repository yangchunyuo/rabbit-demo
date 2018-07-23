package com.rabbitmq.demo.java;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * customer 消费 封装
 */
public class Customer {

    /**
     * customer demo
     * */
    public static void main (String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection conn = RabbitMqConnectionFactory.getRabbitMqConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        /// 创建 customer
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到消息：" + new String(body, "UTF-8"));
            }
        };
        // 自动回复队列应答
        channel.basicConsume(Configuration.QUEUE_NAME, true, consumer);
    }

}
