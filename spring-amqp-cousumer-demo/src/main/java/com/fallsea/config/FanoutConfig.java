package com.fallsea.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月23日 22:53
 * 4
 */
@Configuration
public class FanoutConfig {

    /*
    #发布订阅模式（不同的交换机exchange)-
         1、fanout广播
            -将消息交给所有绑定到交换机的队列中-
         2、direct路由（定向)
            -把消息交给符合指定的routing key的队列中-
         3、topic主题
            -通配符，把消息交给符合routing pattern（路由模式）的队列
    Exchange交换机:只负责转发消息，不具备存储消息的能力
    */

    //发布订阅模式（不同的交换机exdhange）-
    //1、fanout  扇出】广播
        /*    可以有多个队列
               每个队列要绑定交换机
               生产者发送的消息只能发送给交换机，由交换机决定要发送给哪一个队列（生产者无法决定）
               交换机把发送的消息发给绑定过的所有的队列
               订阅过队列的消费者都能获得消息

   1、创建一个交换机【fallsea.fanout】
   2、创建多个队列【n个】
    fanout.queue1   fanout.queue2
   3、将队列绑定到同一个交换机上

*/

    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fallsea.fanout");
    }

    //创建多个队列【n个】
        //第一个
    @Bean
    public Queue FanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    //跟第一个队列绑定交换机
    @Bean
    public Binding binding(Queue FanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(FanoutQueue1).to(fanoutExchange);
    }

        //第二个
    @Bean
    public Queue FanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    //跟第二个队列绑定交换机
    @Bean
    public Binding binding1(Queue FanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(FanoutQueue2).to(fanoutExchange);
    }
}
