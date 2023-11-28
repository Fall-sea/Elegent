package com.fallsea.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月28日 17:21
 * 4
 */
@Component
public class Ack2Listener implements ChannelAwareMessageListener {

    //消费者确认机制  手动确认模式

  /*  @RabbitListener(queues = "queue.aaa")
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //模拟操作业务时间
        Thread.sleep(1000);
        //获取传递的标签【用于消息确认标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //接收转换消息
            System.out.println(new String(message.getBody()));
            //模拟处理业务
            Thread.sleep(1000);
            //没有问题进行手动确认接收
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //如果有问题拒接接收消息
            //requeue表示是否重回队列
            channel.basicNack(deliveryTag,true,true);
        }

    }*/


    //消费者确认机制  手动异常确认模式

    @RabbitListener(queues = "queue.aaa")
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //模拟操作业务时间
        Thread.sleep(1000);
        //获取传递的标签【用于消息确认标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //接收转换消息
            System.out.println(new String(message.getBody()));
            //模拟处理业务
            Thread.sleep(1000);
            int i=10/0;
            //没有问题进行手动确认接收
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //如果有问题拒接接收消息
            //requeue表示是否重回队列
            channel.basicNack(deliveryTag,true,true);
        }

    }
}
