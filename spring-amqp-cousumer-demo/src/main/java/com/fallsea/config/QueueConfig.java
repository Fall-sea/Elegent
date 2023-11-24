package com.fallsea.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月23日 20:10
 * 4
 */
@Configuration
public class QueueConfig {

    public static final String QUEUE_NAME= "amqp.simple2";

    //实现一个队列放在容器中
    @Bean
    public Queue QueueConfig(){
        return new Queue(QUEUE_NAME,true);
    }
}
