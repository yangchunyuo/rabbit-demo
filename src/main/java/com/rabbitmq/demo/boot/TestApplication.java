package com.rabbitmq.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * spring boot test main
 */
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(TestApplication.class);

        Sender sender = (Sender) context.getBean("sender");
        sender.send(RabbitConfig.SPRING_QUEUE_NAME, "default exchange: Hello Word");

        // 定时消息
        //sender.send(RabbitConfig.SPRING_QUEUE_NAME, "哈喽 我的" + new Date().toString(), 10 * 1000);

        //sender.send("amq.direct", RabbitConfig.SPRING_QUEUE_NAME, "direct exchange: Hello Word");
        //sender.send("amq.fanout", "", "fanout exchange: Hello Word");
        //sender.send("amq.topic", "topic", "topic exchange: Hello Word");
    }

}
