package com.fallsea;

import com.fallsea.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月26日 12:27
 * 4
 */
@SpringBootTest
public class RabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    void name2() {

        //默认模式 定义一个returnedMessage的回调函数

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            //message 是一个消息对象
            //replyCode 错误码
            //replyText 错误信息
            //exchange 交换机
            // routing 路由键
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routing) {
                //System.out.println(message);
                System.out.println("消息发送失败，错误码："+replyCode+"原因："+replyText+"交换机"+exchange+"路由key:"+routing+"消息："+message);
            }
        });

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "test.bbb", "测试回退模式");



    }

    @Test
    void name() {

        //默认模式 定义一个confirm的回调函数
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("消息发送成功");
                } else {
                    System.out.println("消息发送失败原因："+cause);
                    //重启发起或者是其他的操作
                }
            }
        });

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "test.bbb", "测试确认回调模式");
      /* // 发送消息失败
       // 此时，将交换机的名称改成一个错误不存在的：
          rabbitTemplate.convertAndSend("gdfgfg", "test.bbb", "测试确认回调模式");*/

    }
}
