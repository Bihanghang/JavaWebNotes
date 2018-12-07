# Eclipse创建Spring Boot项目

### 1.访问http://start.spring.io/ 解压引入Maven项目

###  2.建议的目录结构

```
com
  +- example
    +- myproject
      +- Application.java
      |
      +- domain
      |  +- Customer.java
      |  +- CustomerRepository.java
      |
      +- service
      |  +- CustomerService.java
      |
      +- controller
      |  +- CustomerController.java
      |
```

### 3. pom.xml中添加支持web的模块：

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
 ```
 
pom.xml文件中默认有两个模块：

spring-boot-starter ：核心模块，包括自动配置支持、日志和YAML；

spring-boot-starter-test ：测试模块，包括JUnit、Hamcrest、Mockito。

### 4.编写controller内容：

```
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
```
@RestController 的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！

在application.properties添加server.port=8899(自己的端口号)

###  @SpringRunner的解释

SpringRunner is an alias for the SpringJUnit4ClassRunner.

To use this class, simply annotate a JUnit 4 based test class with @RunWith(SpringRunner.class).

# Idea创建项目

一键生成

web的模块已经自动装在pom.xml之中


关键是选择Web项目
[详情请点击](https://www.cnblogs.com/black-spike/p/8017768.html)



