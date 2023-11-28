package com.fallsea.config;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月26日 10:55
 * 4
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME ="queue.aaa";

    public static final String EXCHANGE_NAME = "exchange.aaa";

    //注册队列【将队列交给spring管理
    @Bean("testqueue")
    public Queue registerQueue(){
        //使用QueueBuilider构建一个队列，设置该队列是持久化以及自动删除
        return QueueBuilder.durable(QUEUE_NAME).autoDelete().build();
    }

    //注册交换机
    @Bean("testexchange")
    public Exchange register(){
        //使用ExchangeBuilider构建一个交换机【交换机可以选择{topic，direct，fanout}，设置该交换机自动删除和持久化
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).autoDelete().durable(true).build();
    }

    //绑定交换机和队列
    @Bean("testbinding")
    public Binding bindingExchangeQueue(@Qualifier("testqueue")Queue queue,@Qualifier("testexchange")Exchange exchange){
        //使用BindingBuilider将声明的队列和交换机进行绑定并设置绑定的路由key
        return BindingBuilder.bind(queue).to(exchange).with("test.#").noargs();
    }

}
