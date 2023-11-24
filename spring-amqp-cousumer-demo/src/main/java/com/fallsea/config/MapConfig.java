package com.fallsea.config;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月24日 20:05
 * 4
 */
@Configuration
public class MapConfig {

    public static final String QUEUE_NAME= "amqp.map";

    //实现一个队列放在容器中
    @Bean
    public Queue MapConfig(){
        return new Queue(QUEUE_NAME,true);
    }
}
