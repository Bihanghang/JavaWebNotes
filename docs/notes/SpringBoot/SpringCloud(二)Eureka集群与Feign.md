# 两个Eureka
上一篇是两个服务像一个Eureka注册，如果Eureka宕掉了就不好了，现在来搭建两个Eureka，两个服务分别像其注册。两个Eureka都用本机来模拟，用两个端口号来表示。
首先修改C:\Windows\System32\drivers\etc的hosts文件，在最下面添加`127.0.0.1 node1 node2`,然后将dis-manager工程的application.properties一个变成两个
```xml
server.port=8888

eureka.instance.hostname=node1
# 不向注册中心注册自己
eureka.client.register-with-eureka=false

# 不需要检索服务
eureka.client.fetch-registry=false

eureka.client.serviceUrl.defaultZone=http://node2:${server.port}/eureka/
```
```xml
server.port=9999

eureka.instance.hostname=node2
# 不向注册中心注册自己
eureka.client.register-with-eureka=false

# 不需要检索服务
eureka.client.fetch-registry=false

eureka.client.serviceUrl.defaultZone=http://node1:${server.port}/eureka/
```
接下来使用`mvn clean package`命令将整个工程打成jar包，并且切换到jar包的位置使用`java -jar dis-manager-0.0.1-SNAPSHOT.jar --spring.profiles.active=node1`分别启动两个Eureka，就可以了。
哦对了，服务注册的时候需要将两个Eureka都注册一下。
```xml
eureka.client.serviceUrl.defaultZone=http://node1:8888/eureka/,http://node2:9999/eureka/
```
# 使用Feign声明式调用
因为Feign相较ribbon更加简单优雅，我们公司都是用的Feign，由于我们是在order服务中调用user服务，所以在order服务中设置。具体只需要三个步骤：<br>
1.引入依赖<br>
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```
将order服务的主类添加注解@EnableFeignClients

2.在order服务中创建被调用user服务的接口<br>
```java
@FeignClient(name = "dis-user")
public interface UserFeign {

    @RequestMapping("/user/getUser")
    User getUser();

}
```
3.在order服务的controller里调用
```java
@Autowired
private UserFeign userFeign;

Logger logger = LoggerFactory.getLogger(OrderController.class);

@RequestMapping("/getOrderByFeign")
public Order getOrderByFeign() {
    User user = userFeign.getUser();
    Order order = new Order(1001, "酸牛奶", user);
    logger.info(order.toString());
    return order;
}
```

代码比较简单，都放在Github上了[https://github.com/Bihanghang/Dis-SpringBoot](https://github.com/Bihanghang/Dis-SpringBoot)，有问题希望一起探讨。

