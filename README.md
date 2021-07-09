# mod

#### 介绍
分布式和单体式自由组装框架，基于dubbo的简单实现，技术可行性研究。

#### 软件架构
软件架构说明

#####
目录结构:
```
mod
├── mod-api    mod相关的API
├── mod-autoconfiguration    // 基于spring的mod自动配置
├── mod-distribute    // 分布式实现
├── mod-monolith    // 单体式实现
└── mod-sample    // 使用示例，包括单体式和分布式
    ├── application-distribute    // 分布式示例，包含启动类、生产者service和消费者service
    │         ├── application-distribute-consumer    // 消费者启动应用service
    │         └── application-distribute-provider    // 生产者启动应用service
    ├── application-monolith    // 单体式应用
    ├── mod-sample-consumer    // 业务模块 消费者
    ├── mod-sample-provider    // 业务模块 生产者
    └── mod-sample-provider-api    // 业务模块 生产者api 
```

`mod-api`,`mod-distribute`, `mod-monolith`, `mod-autoconfiguration`是mod相关的实现和配置,

`mod-sample`是使用示例，包括分布式和单体式的示例.


#### 使用说明

1.打包本工程 `mvn install`

2.引入依赖, 2选一:
```xml
<!--分布式-->
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>mod-distribute-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
<!--单体式-->
    <dependency>
        <groupId>org.example</groupId>
        <artifactId>mod-monolith-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

3.使用注解 `@ModService`和 `@ModInject`

##### 注解使用示例
**生产者**
```java
@ModService
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public String send(String receiver, String msg) {
        logger.info("send msg: {} to : {}", msg, receiver);
        return "send msg: " + msg;
    }
}

```

**消费者**
```java
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @ModInject
    private EmailService emailService;

    @Override
    public String sayHi(String to, String msg) {
        logger.info("say hi: {}, msg: {}", to, msg);
        emailService.send(to, msg);
        return "ok";
    }
}
```

Demo见`mod-sample包`

#### Demo

mod-sample中 `mod-sample-consumer`是业务功能的消费者，`mod-sample-provider`是业务功能提供者, `application-*`是2个进程启动包，分别是单体式和分布式。

在`application-monolith`中，引入了`mod-sample-consumer`和`mod-sample-provider`，配合`mod-monolith-spring-boot-starter`。

在`application-distribute`中，`application-distribute-consumer`引入了`mod-sample-consumer`; `application-distribute-provider`引入了`mod-sample-provider`，配合`mod-distribute-spring-boot-starter`。

##### 运行
1. 验证单体式
运行`application-monolith`的`MonolithApp`,启动完成后运行,返回ok:

```shell script
curl --request GET -sL \
     --url 'http://localhost:8082/user/sayHi'
```

可以在控制台看到
```
2021-07-09 14:11:21.042  INFO 8140 --- [nio-8082-exec-2] c.e.m.s.c.service.UserServiceImpl        : say hi: jack, msg: hello
2021-07-09 14:11:21.044  INFO 8140 --- [nio-8082-exec-2] c.e.m.sample.provider.EmailServiceImpl   : send msg: hello to : jack
```
2. 验证分布式

a. 启动consul(默认使用了consul,可使用[consul-docker-compose](./consul-docker-compose.yml))

b. 运行生产者服务`application-distribute-provider`的`DistributeProviderApp`和消费者服务`application-distribute-consumer`的`DistributeConsumerApp`


启动完成后运行, 返回ok
```shell script
curl --request GET -sL \
     --url 'http://localhost:28877/user/sayHi'
```
可以在消费者服务中看到日志:
```
2021-07-09 13:44:23.065  INFO 2036 --- [io-28877-exec-8] c.e.m.s.c.service.UserServiceImpl        : say hi: jack, msg: hello
```
在提供者服务中看到日志
```
2021-07-09 13:44:23.067  INFO 25788 --- [:20880-thread-3] c.e.m.sample.provider.EmailServiceImpl   : send msg: hello to : jack
```



#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
