# [Home](../README.md)
```json
    |--- pom                                        // acblog配置文件
    |--- blog-plus.sql                              // acblog数据库设计
    |--- src                                        // 源代码
    |--- |--- main                                  // 代码页
              |--- Java                             // 后台代码
                 |--- common                        // 公共类
                       |--- config                  // 配置类(以.config后缀结尾)
                       |--- utils                   // 工具类
                            |--- phoneVerify        // 腾讯云短信验证代码
                 |--- modules                       // 服务端代码
                      |--- controller               // 表现层
                      |--- dao                      // 持久层
                      |--- entity                   // 实体层
                      |--- service                  // 业务逻辑层
                      |--- shiro                    // shiro配置类
                      |--- AcblogApplication        // spring boot启动类
    |--- |--- resources                             // 资源
                  |--- mappering                    // 持久层xml文件
                  |--- static                       // 静态文件
                  |--- templates                    // 前端页面
                  |--- application.properties       // 全局配置类
                  |--- application.yml              // 全局配置类
                  |--- application-dev.yml          // 全局配置类(开发者模式)
                  |--- application-test.yml         // 全局配置类(测试者模式)
                  |--- application-prod.yml         // 全局配置类(生产者模式)

    xJavaFxTool
    ├─ images 项目截图
    ├─ pom.xml  maven配置文件
    ├─ README.md  说明文件
    ├─ src
    │  ├─ main
    │  │  ├─ java
    │  │  │  └─ com
    │  │  │   └─ xwintop
    │  │  │    └─ xJavaFxTool
    │  │  │     ├─ common     第三方工具类
    │  │  │     ├─ controller javafx控制层
    │  │  │     │  └─ index   首页控制层
    │  │  │     ├─ model      基础bean类层
    │  │  │     ├─ services   工具服务层
    │  │  │     │  └─ index   首页工具服务层
    │  │  │     ├─ utils      系统工具类
    │  │  │     └─ view       javafx视图层
    │  │  │        └─ index   首页工具视图层
    │  │  └─ resources
    │  │   ├─ com
    │  │   │  └─ xwintop
    │  │   │   └─ xJavaFxTool
    │  │   │    └─ fxmlView     .fxml文件
    │  │   ├─ config  配置文件
    │  │   │  └─ toolFxmlLoaderConfiguration.xml  系统菜单加载配置文件
    │  │   ├─ css     样式资源
    │  │   ├─ images  图片资源
    │  │   ├─ locale  国际化
    │  │   ├─ banner.txt  启动banner图片
    │  │   └─ logback.xml logback日志配置文件
    │  └─ test  测试类
    │   ├─ java
    │   └─ resources
```
#### ########################################
## maven操作
### 1、打包jar war(需要配置pom.xml)
    mvn -Dmaven.test.skip -U clean package

### 2、运行 springboot
    mvn spring-boot:run

### 3、运行 JavaFX
    mvn javafx:run

### #######

### 1.命令行创建普通java项目
    mvn archetype:generate -DgroupId=com.demo -DartifactId=demo -DarchetypeArtifactId=maven-archetype-quickstart

### 2.命令行创建javaWeb项目
    mvn archetype:generate -DgroupId=com.demo -DartifactId=demo -DarchetypeArtifactId=maven-archetype-webapp

### 3.运行maven项目
    mvn clean && mvn exec:java -Dexec.mainClass="com.demo.App"

### 4.创建Springboot项目 (需要手动修改application启动类，增加pom)
- mvn archetype:generate -DinteractiveMode=false -DgroupId=com.example -DartifactId=springboot -Dversion=1.0.0-SNAPSHOT

```Java
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class App {
        public static void main( String[] args ) {
            SpringApplication.run(App.class,args);
        }
    }
```
```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```

#### ########################################
### MAVEN命令 ###
### maven 命令的格式为 mvn [plugin-name]:[goal-name]，可以接受的参数如下，
    -D 指定参数，如 -Dmaven.test.skip=true 跳过单元测试；
    -P 指定 Profile 配置，可以用于区分环境；
    -e 显示maven运行出错的信息；
    -o 离线执行命令,即不去远程仓库更新包；
    -X 显示maven允许的debug信息；
    -U 强制去远程更新snapshot的插件或依赖，默认每天只更新一次。

### 常用maven命令
    创建maven项目：mvn archetype:create
    指定 group： -DgroupId=packageName
    指定 artifact：-DartifactId=projectName
    创建web项目：-DarchetypeArtifactId=maven-archetype-webapp
    创建maven项目：mvn archetype:generate
    验证项目是否正确：mvn validate
    maven 打包：mvn package
    只打jar包：mvn jar:jar
    生成源码jar包：mvn source:jar
    产生应用需要的任何额外的源代码：mvn generate-sources
    编译源代码： mvn compile
    编译测试代码：mvn test-compile
    运行测试：mvn test
    运行检查：mvn verify
    清理maven项目：mvn clean
    生成eclipse项目：mvn eclipse:eclipse
    清理eclipse配置：mvn eclipse:clean
    生成idea项目：mvn idea:idea
    安装项目到本地仓库：mvn install
    发布项目到远程仓库：mvn:deploy
    在集成测试可以运行的环境中处理和发布包：mvn integration-test
    显示maven依赖树：mvn dependency:tree
    显示maven依赖列表：mvn dependency:list
    下载依赖包的源码：mvn dependency:sources
    安装本地jar到本地仓库：mvn install:install-file -DgroupId=packageName -DartifactId=projectName -Dversion=version -Dpackaging=jar -Dfile=path

### web项目相关命令
    启动tomcat：mvn tomcat:run
    启动jetty：mvn jetty:run
    运行打包部署：mvn tomcat:deploy
    撤销部署：mvn tomcat:undeploy
    启动web应用：mvn tomcat:start
    停止web应用：mvn tomcat:stop
    重新部署：mvn tomcat:redeploy
    部署展开的war文件：mvn war:exploded tomcat:exploded