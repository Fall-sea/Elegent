##   RabbitMQ  消息模型

官方提供了5种消息类型
- 基本消息队列【简单模式】
- 工作消息队列
- 发布订阅模式（不同的交换机exchange）
    - 1、fanout广播
    - 2、direct路由
    - 3、topic主题


####  基本消息队列【简单模式】

- publisher 消息的生产者
    - 基本的思路
        - 建立连接
        - 创建Channel
        - 声明队列
        - 发送消息
        - 关闭连接以及channel【释放资源】

- consumer   消息的消费者
    - 基本的思路
        - 建立连接
        - 创建Channel
        - 声明队列
        - 订阅消息
        - 关闭连接以及channel【释放资源】


##   SpringAMQP

- 1、自动声明队列、交换机以及其绑定关系
- 2、基于注解的监听器模式，异步接收消息
- 3、封装了RabbitTemplate工具，用于发送消息




####  发布订阅模式（不同的交换机exchange）
- 1、fanout广播
    - 将消息交给所有绑定到交换机的队列中
- 2、direct路由（定向）
    - 把消息交给符合指定的routing key的队列中
- 3、topic主题
    - 通配符，把消息交给符合routing pattern（路由模式）的队列

Exchange交换机：只负责转发消息，不具备存储消息的能力

#####  Fanout 扇出

- 1、可以有多个队列
- 2、每个队列都要绑定交换机
- 3、生产则的发送的消息，只能发送到交换机，由交换机决定要发送到哪一个队列（生产者无法决定）
- 4、交换机把消息发送给绑定过的所有的队列
- 5、订阅队列的消费者都能获取到消息

创建一个交换机【xuguoguo.fanout】

创建多个队列【两个】
fanout.queue1   fanout.queue2

将队列绑定到同一个交换机上


#####   Direct消息类型

在Fanout模式中，一条消息，会被所有订阅的队列都消费。
但是，如果希望不同的消息被不同的队列消费。【普通用户、会员、vip、管理员等】



######   fanout与direct对比：

- Fanout交换机将消息路由给每一个与之绑定的队列
- Direct交换机将根据RoutingKey判断路由给哪个队列
- 如果多个对垒具有相同的RoutingKey，则与Fanout的功能类似


#####  Topic主题类型【通配符】

根据RoutingKey把消息路由到不同的队列【topic的交换机可以让队列在绑定key的时候使用通配符】

RoutingKey：
一般都是由一个或者多个单词组成【多个单词使用.进行隔开】

- 通配符的规则
  #:匹配一个或者多个词
  *:匹配不多不少1个词

如:
- item.#     能够匹配 item.user.insert    item.user  item.vip

- item.*     只能匹配item.user   item.vip


######    Direct与Topic交换机的区别

- Topic交换机接收的消息RoutingKey必须是多个单词，以**.**分割
- Topic交换机与队列绑定的时候bindingKey可以指定通配符
    - #:代表0个或者多个词
    - *:代表一个词


####   消息转换器

spring会把发送的消息序列化成字节发送给MQ，接收消息的时候会把字节反序列化为Java对象

采用JDK的序列化方式：
- 数据体积过大
- 安全漏洞
- 可读性差

##### JSON转换器

在pom.xml文件中
```
  <!--引入json转换器-->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.9.10</version>
        </dependency>

```
配置消息转换器
   ```
//消息转换器配置
	@Bean
	public MessageConverter jsonConverter(){
		return new Jackson2JsonMessageConverter();
	}
   ```

### 服务的异步通信
- 1、消息可靠性的问题
  - 如何确保发送的消息至少被消费一次
- 2、延迟消息问题
  - 如何实现延迟的投递
- 3、高可用问题
  - 如何避免MQ的单点故障（一台机器)
- 4、消息堆积问题
  - 如何解决百万消息的堆积，无法及时消费
  
##### 1、消息可靠性的问题
- 如何确保发送的消息至少被消费一次
- 1、发送时丢失
  - 1.1、生产者发送的消息未送达exchange
  - 1.2、消息到达exchange后未到达queue
- 2、MQ服务器宕机，queue将消息丢失
- 3、consumer接收到消息后未消费宕机

###### MQ解决方案
- 生产者确认机制
  - ack 消息成功投递到交换机
  - nack 消息未成功投递到交换机
- mq持久化【默认是非持久化】
  - 交换机持久化
  - 队列持久化
  - 消息持久化
- 消费者确认机制
- 失败重试机制







