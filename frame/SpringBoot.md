# [Home](../README.md)
# Spring Boot :

## LEARNING ROUTE :
### 注解 :
```Java
	@Configuration 				将配置类注入容器
	@Bean						将对象注入容器
	@Import						给容器中导入组件
	@Confitional				条件装配，例如存在a组件时让b主键注入容器
	@ImportResource				可以将xml配置的bean注入到springboot容器中
	@ConfigurationProperties	配置属性，将bean和properties中的属性绑定

	@Component					
	@Autowired					
	@Repository					
```
### Spring Boot自动配置原理 :
```
	@SpringBootApplication
		@SpringBootConfiguration	核心配置类
			@Configuration
		@EnableAutoConfiguration
			@AutoConfigurationPackage	自动配置包，利用Registrar给容器中导入MainApplication所在包下的所有组件
				@Import(AutoConfigurationPackage.Registrar.class)	给容器导入组件
			@Import(AutoConfigurationImportSelector.class)		
				利用getAutoConfigurationEntry(annotationMetadata);给容器中导入一些组件
		@ComponentScan()			执行扫描哪些
```
## Json Web Token (JWT) :
![jwt](../images/jwt.png)

### JWT结 :
1. 标头(Header), Base64

```json
	{
		"alg" : "HS256",
		"typ" : "JWT"
	}
```
2. 有效载荷(Payload), Base64

```json
	{
		"sub" : "123456",
		"name" : "root",
		"admin" : true
	}
```
3. 签名(Signature)

```Java
HMACSHA256(base64UrkEncode(header) + "." + base64UrlEncode(payload), secret)
```

- pom.xml依赖

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>
```
- 过滤器
这个类声明了一个JWT过滤器类，从Http请求中提取JWT的信息，并使用了secretkey这个密匙对JWT进行验证。


## Shiro :

## Security :

