package com.fallsea;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.MessageConverter;

@SpringBootApplication
public class SpringAmqpCousumerDemoApplication {

	//消息转换器配置
	@Bean
	public MessageConverter jsonConverter(){
		return new Jackson2JsonMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpCousumerDemoApplication.class, args);
	}

}
