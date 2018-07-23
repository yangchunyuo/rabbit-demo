package com.rabbitmq.demo.java;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * producer 生产者 封装
 */
public class Producer {

    /**
     * 生产者 demo
     * */
    public static void main (String[] args) throws IOException, TimeoutException {
        // 创建 连接
        Connection conn = RabbitMqConnectionFactory.getRabbitMqConnection();
        // 创建 通道
        Channel channel = conn.createChannel();
        // 创建 队列
        channel.queueDeclare(Configuration.QUEUE_NAME, false, false, false, null);
        // 声明一个消息
        String message = "Hello Word";
        // 发送消息到队列
        channel.basicPublish("", Configuration.QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("send message [" + message + "]");
        // 关闭通道
        channel.close();
        // 关闭连接
        conn.close();
    }

}
