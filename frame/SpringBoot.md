# [Home](../README.md)
## Spring Boot 

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


## [Shiro](https://www.docs4dev.com/docs/zh/apache-shiro/1.5.3/reference/introduction.html)
> pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-core</artifactId>
        <version>1.4.0</version>
    </dependency>

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.22</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.1</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```
```java
package com.example.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class Test {

	public static void main(String[] args) {
		// 1. 获取一个SecurityManager工厂对象
		// IniSecurityManagerFactory 已被弃用
		//Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
		defaultSecurityManager.setRealm(iniRealm);
		// 2. 通过SecurotyManager工厂对象创建一个SecurityManager对象
		//SecurityManager securityManager = factory.getInstance();
		// 2.将defaultSecurityManager绑定到setSecurityManager
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		// 3. 将SecurityManager对象添加到当前的运行环境中去
		SecurityUtils.getSecurityManager();
		// 4. 获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		// 5. 获取用户提交的认证账号密码
		String username = "root";
		String password = "123456";
		// 6. 将用户提交的账号密码封装成一个Token对象
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		// 7. 完成认证操作 login
		try {
			subject.login(token);
			System.out.println("登录成功...");
		} catch (UnknownAccountException e) {
			System.out.println("账号错误...");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误...");
		}
	}
}

```

## [Spring Security](https://www.docs4dev.com/docs/zh/spring-security/5.1.2.RELEASE/reference/community.html)
### CSRF

