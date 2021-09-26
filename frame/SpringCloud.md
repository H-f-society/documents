# [Home](../README.md)
## [Spring Cloud](https://spring.io/projects/spring-cloud) / [中文文档](https://www.springcloud.cc/)

### eureka
![eureka](../images/eureka1.jpg)

- Eureka SErver
	- 提供服务注册和发现
- Service Provider
	- 服务提供方
	- 将自身服务注册到Eureka，从而使服务消费方能够找到
- Service Consumer
	- 服务消费方
	- 从Eureka获取注册服务列表，从而能够消费

```
# 是否将自己注册到Eureka Server，默认为true
eureka.client.register-with-eureka=false

# 是否从Eureka Server获取注册信息，默认为true。
eureka.client.fetch-registry=false

# 设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址
# 默认是http://localhost:8761/eureka ；多个地址可使用 "," 分隔
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```
### feign
### rabbit
### zuul
### hystrix
### hystrix dashboard
### turbine
### sleuth
### oath2