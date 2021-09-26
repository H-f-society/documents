# [Home](../README.md)
## [Spring Cloud](https://spring.io/projects/spring-cloud) / [中文文档](https://www.springcloud.cc/) / [实操案例](https://github.com/H-f-society/spring-cloud)

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

# 是否从Eureka Server获取注册信息，默认为true
eureka.client.fetch-registry=false

#logging.level.com.netflix.eureka=OFF
#logging.level.com.netflix.discovery=OFF

# 设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址
# 默认是http://localhost:8761/eureka ；多个地址可使用","分隔
eureka.client.serviceUrl.defaultZone=http://localhost:8081/eureka/
```
### feign
### rabbit
### zuul
### hystrix
> 当Hystrix Command请求后端服务失败数量超过一定比例(默认50%), 断路器会切换到开路状态(Open). 这时所有请求会直接失败而不会发送到后端服务. 断路器保持在开路状态一段时间后(默认5秒), 自动切换到半开路状态(HALF-OPEN). 这时会判断下一次请求的返回情况, 如果请求成功, 断路器切回闭路状态(CLOSED), 否则重新切换到开路状态(OPEN). 
> Hystrix的断路器就像我们家庭电路中的保险丝, 一旦后端服务不可用, 断路器会直接切断请求链, 避免发送大量无效请求影响系统吞吐量, 并且断路器有自我检测并恢复的能力.

### hystrix dashboard
### turbine
### sleuth
### oath2