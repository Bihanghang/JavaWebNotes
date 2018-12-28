# Eureka简介
SpringBoot简化了Spring工程的复杂度，之前复杂的Spring工程被拆分成了一个个小的SpringBoot工程。那么SpringBoot之间如何通讯，相互获取信息呢？这就用到了SpringCloud的Eureka小弟来组合管理这一个个小的SpringBoot,称其为微服务架构。Eureka就相当于管家，SpringBoot工程通过他来相互调用。当然Eureka也是一个SpringBoot工程，通过修改配置文件来让他变身成管家。那我们现在就需要创建3个SpringBoot工程，一个用来当作管家，两个用当作奴隶，由管家来统治他们。

# 管家Eureka的创建

### 添加依赖
通过Idea创建一个普通的SpringBoot工程，不要再用Eclipse了，Idea更好用。
SpringCloud与SpringBoot的版本问题还是得注意，我公司使用SpringBoot版本为1.5.10.RELEASE那么与其相对应的SpringCloud版本就为Dalston.RELEASE.[其他相对应得版本](https://www.cnblogs.com/bihanghang/p/10186807.html)
所以在pom.xml文件中添加SpringCloud的依赖以及eureka依赖:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
    </dependency>
</dependencies>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Dalston.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
### 修改application.properties文件
```xml
server.port=8888

# 不向注册中心注册自己
eureka.client.register-with-eureka=false

# 不需要检索服务
eureka.client.fetch-registry=false

eureka.client.serviceUrl.defaultZone=http://localhost:${server.port}/eureka/
```
### 添加@EnableEurekaServer注解
```java
@SpringBootApplication
@EnableEurekaServer
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
```
此时访问http://localhost:8888/  就能看到eureka界面了.因为目前没有服务注册，所以Application是空的。

# 用户user和订单order的建立
### 添加依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
    </dependency>
</dependencies>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Dalston.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
### 在application.properties配置文件中加上注册中心的配置：
```xml
eureka.client.service-url.defaultZone=http://localhost:8888/eureka/
# 以自身ip注册,不然默认注册本机
# instance:
#   prefer-ip-address: true
#   ip-address: 10.68.29.152
server.port=8282
```
### 并在主类中加入@EnableDiscoveryClient注解即可完成注册服务。

# 服务间的调用
既然这两个服务都注册，那么我们现在就在order里面调用一下user，这个是很厉害的，两个服务之间通过Eureka就可以远程调用了。

### 使用ribbon来做客户端的负载
客户端的负载就是Ribbon从Eureka注册中心去获取服务端列表，然后进行轮询访问以到达负载均衡的作用。
首先引入依赖:
```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
```
然后在order主类中开启@LoadBalanced客户端负载：
```java
@SpringBootApplication
@EnableDiscoveryClient
public class DisOrderApplication {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(DisOrderApplication.class, args);
    }
}
```
这样就可以在order类中调用了:
```java
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/getOrder")
    public Order getOrder() {
        User user = restTemplate.getForObject("http://dis-user/user/getUser", User.class);
        Order order = new Order(1001, "酸牛奶", user);
        logger.info(order.toString());
        return order;
    }
}
```
`User user = restTemplate.getForObject("http://dis-user/user/getUser", User.class);`这句代码就是远程调用user服务获取信息。

代码比较简单，都放在Github上了https://github.com/Bihanghang/Dis-SpringBoot，有问题希望一起探讨。