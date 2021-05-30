# [Home](../README.md)

### Springboot - ActiveMQ
```xml
<!--ActiveMq-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
    <version>1.5.0.RELEASE</version>
</dependency>
<!--消息队列连接池-->
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-pool</artifactId>
    <version>5.15.0</version>
</dependency>
```
```yml
server:
  port: 8080

spring:
  activemq:
    broker-url: tcp://127.0.0.1:61616
    user: admin
    password: admin
    close-timeout: 15s   # 在考虑结束之前等待的时间
    in-memory: true      # 默认代理URL是否应该在内存中。如果指定了显式代理，则忽略此值。
    non-blocking-redelivery: false  # 是否在回滚回滚消息之前停止消息传递。这意味着当启用此命令时，消息顺序不会被保留。
    send-timeout: 0     # 等待消息发送响应的时间。设置为0等待永远。
    queue-name: active.queue
    topic-name: active.topic.name.model
```
```java
@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String broketUrl;

	@Value("${spring.activemq.user}")
	private String username;

	@Value("${spring.activemq.password}")
	private String password;

	@Value("${spring.activemq.queue-name}")
	private String queueName;

	@Value("${spring.activemq.topic-name}")
	private String topicName;

	@Bean(name = "queue")
	public Queue queue() {
		return new ActiveMQQueue(queueName);
	}

	@Bean(name = "topic")
	public Topic topic() {
		return new ActiveMQTopic(topicName);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(username, password, broketUrl);
	}
	
	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate() {
		return new JmsMessagingTemplate(connectionFactory());
	}

	/**
	 * 在Queue模式中，对消息的监听需要对containerFactory进行配置
	 * @param connectionFactory
	 * @return
	 */
	@Bean("queueListener")
	public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPubSubDomain(false);
		return factory;
	}

	/**
	 * 在Topic模式中，对消息的监听需要对containerFactory进行配置
	 * @param connectionFactory
	 * @return
	 */
	@Bean("topicListener")
	public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory){
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}
}
```
```java
@Component
public class QueueConsumerListener {
	@JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
	public void readactiveQueue(String message) {
		System.out.println("Queue Message: " + message);
	}
}
```
```java
@Component
public class TopicConsumerListener {

	@JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
	public void readActiveQueue(String message) {
		System.out.println("Topic message: " + message);
	}
}
```