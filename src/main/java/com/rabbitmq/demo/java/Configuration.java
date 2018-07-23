package com.rabbitmq.demo.java;

/**
 * rabbit mq configuration
 */
public interface Configuration {

    /** rabbit mq 地址 */
    String HOST = "127.0.0.1";
    /** 端口 */
    Integer PORT = 5672;
    /** 用户名 */
    String USER_NAME = "chuck";
    /** 密码 */
    String PASSWORD = "123456";
    /** 虚拟主机 */
    String VIRTUAL_HOST = "/";

    /** 队列名称 */
    String QUEUE_NAME = "test_queue";
}
