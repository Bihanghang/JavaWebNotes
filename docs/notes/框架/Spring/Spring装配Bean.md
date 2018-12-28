##  @ContextConfiguration的意思
当一个类添加了注解`@Component`,那么他就自动变成了一个bean，就不需要再Spring配置文件中显示的配置了。把这些bean收集起来通常有两种方式，Java的方式和XML的方式。当这些bean收集起来之后，当我们想要在某个类使用`@Autowired`注解来引入他们时，只需要给这个类添加`@ContextConfiguration`注解就可以将它们引入。

### XML
我们先看看老年人使用的XML方式:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans  
	http://www.springframework.org/schema/beans/spring-beans-3.1.xsd  
	http://www.springframework.org/schema/context  
	http://www.springframework.org/schema/context/spring-context-3.1.xsd  >

	<!-- 自动扫描该包 -->
	<context:component-scan base-package="com" />
</beans>
```
这个XML文件通过`<context:component-scan base-package="com" />`标签将com包下的bean全都自动扫描进来。<br>

下面我们就可以测试了。

`@ContextConfiguration`这个注解通常与`@RunWith(SpringJUnit4ClassRunner.class)`联合使用:<br>
一般这么写:
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class CDPlayerTest {

}
```
`@ContextConfiguration`括号里的`locations = {"classpath*:/*.xml"}`就表示将class路径里的所有.xml文件都包括进来，那么刚刚创建的那么XML文件就会包括进来，那么里面自动扫描的bean就都可以拿到了，此时就可以在测试类中使用`@Autowired`注解来获取之前自动扫描包下的所有bean

`classpath和classpath*区别:`

* classpath：只会到你的class路径中查找找文件。

* classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。

### Java
如果使用Java的方式就会很简单了，我们就不用写XML那么繁琐的文件了，我们可以创建一个Java类来代替XML文件，只需要在这个类上面加上`@Configuration`注解,然后再加上`@ComponentScan`注解就开启了自动扫描，如果注解没有写括号里面的东西，@ComponentScan默认会扫描与配置类相同的包。
```java
@Configuration
@ComponentScan
public class CDPlayConfig {

}
```
此时如果想要测试的话，就可以这么写：
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayConfig.class)
public class CDPlayerTest {

}
```
相较于XML,是不是很酷炫，这也是官方提倡的方式。
