package com.rabbitmq.demo.java;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit mq connection factory 封装
 */
public class RabbitMqConnectionFactory {

    /** connection factory */
    private static ConnectionFactory factory = new ConnectionFactory();
    /** configuration */
    static {
        factory.setHost(Configuration.HOST);
        factory.setPort(Configuration.PORT);
        factory.setUsername(Configuration.USER_NAME);
        factory.setPassword(Configuration.PASSWORD);
        factory.setVirtualHost(Configuration.VIRTUAL_HOST);
    }

    /**
     * 获取 rabbit mq connection
     * @return
     */
    static Connection getRabbitMqConnection () throws IOException, TimeoutException {
        return factory.newConnection();
    }

}
