package com.fallsea;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月23日 15:05
 * 4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAMQPTest {

    @Autowired
   private RabbitTemplate rabbitTemplate;

    @Test
    void TestMap() {
        //队列名称
        String queueName= "amqp.map";

        //消息
        //String msg = "你好";
        HashMap<Object, Object> msg = new HashMap<>();
        msg.put("name","张三");
        msg.put("like","red");
        //发送消息
        rabbitTemplate.convertAndSend(queueName,msg);
        System.out.println("发现成功");
    }

    @Test
    void TestTopic() {
        //交换机
        String exchangename="fallsea.topic";

        //发送内容
        String msg="今天晚上吃什么？";

        //发送消息
        rabbitTemplate.convertAndSend(exchangename,"user1.all",msg);
    }

    @Test
    void TestDirect() {
        //交换机
        String exchangename="fallsea.direct";

        //发送内容
        String msg="今天吃什么？";

        //发送消息
        rabbitTemplate.convertAndSend(exchangename,"bbb",msg);
    }

    @Test
    void TestFanout() {
        //交换机
        String exchangename="fallsea.fanout";

        //发送内容
        String msg="发布订阅模式的广播模式";

        //发送消息
        rabbitTemplate.convertAndSend(exchangename,"",msg);
    }

    @Test
    void name1() {
        //队列名称
        String queueName= "amqp.simple2";

        //消息
        String msg = "你好";

        for (int i =0; i < 50; i++){
            //发送消息
            rabbitTemplate.convertAndSend(queueName,msg+i);
        }

    }

    @Test
    void name() {
        //队列名称
        String queueName= "amqp.simple";

        //消息
        String msg = "你好";

        //发送消息
        rabbitTemplate.convertAndSend(queueName,msg);
    }
}
