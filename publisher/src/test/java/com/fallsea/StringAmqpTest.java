package com.fallsea;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月25日 14:17
 * 4
 */
@SpringBootTest
public class StringAmqpTest {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    //默认发送的消息也是持久化
    @Test
    void name2() {
    /*    //构建一个消息体
        Message message = MessageBuilder.withBody("Hello， world! ".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        //发送消息
        rabbitTemplate.convertAndSend("simple.queue " , message);*/

    }

    @Test
    void name() throws InterruptedException {
/*
        //发送内容
        String message = "今天可以晒太阳";

        //回调函数策略
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        correlationData.getFuture().addCallback(
                result -> {
                    //在连接正常的情况下接受MQ队列返回应答消息
                    if (result.isAck()){
                        //正常ack情况
                        System.out.println("消息发送成功！消息id为：" +correlationData.getId());
                    } else {
                        //nack
                        System.out.println("消息发送失败！！！消息id为：" +correlationData.getId());
                    }
                },ex -> {
                    //连接MQ异常
                    System.out.println("消息发送失败！！！连接MQ异常，消息id为：" +correlationData.getId());
                }
        );

        //发送消息
        rabbitTemplate.convertAndSend("simple.direct","simple.test",message,correlationData);
        Thread.sleep(4000);
       *//* 发送消息失败
        此时，将交换机的名称改成一个错误不存在的：
        rabbitTemplate.convertAndSend("ssimple.direct","simple.test",message,correlationData);*/

    }

}