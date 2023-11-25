package com.fallsea.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 1
 * 2	* @author FallSea
 * 3	* @date 2023年11月25日 14:00
 * 4
 */
@Slf4j
@Configuration//定义一个Return回调
public class CommonConfig implements ApplicationContextAware {

    //为RabbitTemplate设置路由到队列失败是调用的方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            //从容器中获取bean对象
        RabbitTemplate bean = applicationContext.getBean(RabbitTemplate.class);

        bean.setReturnCallback((message,replyCode,replyTest,exchange,routingKey) ->{
            log.info("消息发送失败，答应码：,原因：,交换机,路由key",replyCode,replyTest,exchange,routingKey,message.toString());
            System.out.println("消息发送失败，答应码："+replyCode+"原因："+replyTest+"交换机"+exchange+"路由key"+routingKey+"消息："+message.toString());
        });
    }
}
