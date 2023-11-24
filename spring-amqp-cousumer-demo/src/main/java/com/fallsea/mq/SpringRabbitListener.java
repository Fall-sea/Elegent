package com.fallsea.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月23日 20:04
 * 4
 */
@Component
public class SpringRabbitListener {
  /*  官方提供了5种消息类型
    -   基本消息队列【简单模式】-
        工作消息队列
-       发布订阅模式（不同的交换机exdhange）-
            1、fanout广播
            2、direct路由-
            3、topic主题
     */

    //基础消息队列模式
    @RabbitListener(queues = "amqp.simple")
    public void simple(String msg){

        System.out.println("消费者接收的消息为："+msg);
    }
    //使用map发送消息
    @RabbitListener(queues = "amqp.map")
    public void Map(String msg){

        System.out.println("消费者接收的【map】消息为："+msg);
    }
    //工作消息队列模式

    @RabbitListener(queues = "amqp.simple2")
    public void WorkQueue(String msg) throws InterruptedException {

        System.out.println("消费者1】接收的消息为："+msg+"\t时间："+ LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "amqp.simple2")
    public void WorkQueue2(String msg) throws InterruptedException {

        System.out.println("消费者2】接收的消息为："+msg+"\t时间："+ LocalTime.now());
        Thread.sleep(200);
    }

    //1、fanout广播
    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueue(String msg) throws InterruptedException {
        System.out.println("消费者1】接收的fanout消息为："+msg+"\t时间："+ LocalTime.now());
    }

    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2】接收的fanout消息为："+msg+"\t时间："+ LocalTime.now());
    }

   /* #####Direct消息类型
    在Fanout模式中，一条消息，会被所有订阅的队列都消费。
    但是，如果希望不同的消息被不同的队列消费。【普通用户、会员、vip、管理员等


    fanout与direct对比：
            Fanout交换机将消息路由给每一个与之绑定的队列
            Direct交换机将根据RoutingKey判断路由给哪个队列
            如果多个对垒具有相同的RoutingKey，则与Fanout的功能类似
    */

    //绑定队列名字name
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "Direct.queue1"),
                    //绑定交换机名字 name         设置交换机类型type = ExchangeTypes.DIRECT
                    exchange = @Exchange(name = "fallsea.direct",type = ExchangeTypes.DIRECT),
                    //绑定key
                    key = {"red","aaa"}))
    public void DirectQueue2(String msg){
        System.out.println("消费者接收Direct.queue1】消息为："+msg+"\t时间："+ LocalTime.now());
    }

    //绑定队列名字name
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "Direct.queue2"),
            //绑定交换机名字 name         设置交换机类型type = ExchangeTypes.DIRECT
            exchange = @Exchange(name = "fallsea.direct",type = ExchangeTypes.DIRECT),
            //绑定key
            key = {"red","bbb"}))
    public void DirectQueue1(String msg){
        System.out.println("消费者接收Direct.queue2】消息为："+msg+"\t时间："+ LocalTime.now());
    }

    /*
    * Topic 主题类型【通配符】
    * 根据RoutinKey把消息路由不同队列【topic的交换机可以让队列在绑定key的时候使用通配符】
    * RoutingKey：一般都是由一个或者多个单词组成【多个单词使用 进行隔离】
    *
    * 通配符规则：
    * 1、 # 匹配一个或者多个词
    * 2、 * 匹配一个
    *
    * 如:
        item.# 能够匹配 item.user.insert item.user item.vip

        item.* 只能匹配item.user item.vip
    * */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "Topic.queue1"),
                    exchange = @Exchange(name = "fallsea.topic",type = ExchangeTypes.TOPIC),
                    key = {"user.#"}))
    public void TopicQueue1(String msg){
        System.out.println("消费者接收Topic.queue1】消息为："+msg+"\t时间："+ LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "Topic.queue2"),
            exchange = @Exchange(name = "fallsea.topic",type = ExchangeTypes.TOPIC),
            key = {"#.all"}))
    public void TopicQueue2(String msg){
        System.out.println("消费者接收Topic.queue2】消息为："+msg+"\t时间："+ LocalTime.now());
    }
}
