package com.fallsea.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月25日 14:33
 * 4
 */
@Configuration
public class MqConfig {

    //交换机持久化
  /*  @Bean
    public DirectExchange exchangeDirect(){
        //参数：交换机名称，是否持久化，是否自动删除
        return new DirectExchange("simple.direct",true,false);
    }*/

    //队列持久化 【默认是持久化】

    //默认发送的消息也是持久化

}
