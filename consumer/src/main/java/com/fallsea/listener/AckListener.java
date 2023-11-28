package com.fallsea.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.MessageAckListener;
import org.springframework.stereotype.Component;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月26日 20:31
 * 4
 */
//@Component
public class AckListener implements MessageListener {

    //消费者确认机制  自动确认模式
    @RabbitListener(queues = "queue.aaa")
    @Override
    public void onMessage(Message message) {
        System.out.println("获取消息为："+message.getBody().toString());
    }
}
