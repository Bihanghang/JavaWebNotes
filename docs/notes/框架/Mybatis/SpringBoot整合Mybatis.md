## application.properties
```xml
#mybatis
mybatis.mapper-locations=classpath*:mapper/**/*.xml
mybatis.type-aliases-package=classpath:com.ceair.car.order.repository/*
```
## SpringBoot启动类
```java
@SpringBootApplication
@SpringBootConfiguration
@EnableApolloConfig
@ComponentScan(basePackages = {"com.ceair.car.order.*"})
@MapperScan(value = "com.ceair.car.order.repository.mapper")
public class CarOrderWebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CarOrderWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CarOrderWebApplication.class, args);
	}
}
```
## 注意点
在Spring中，如果自动引入加上static，那么mapper就不能用了。

## Mybatis插入Date类型到Mysql
插入的时候：需要将util转为sql-->new java.sql.Date(System.currentTimeMillis())<br>
如果是updateByExampleSelective，直接就可以使用util下的Date。如果此时还是使用sql的Date，精度就会从Timestamp自动转为Date，导致损失精度，很奇怪。