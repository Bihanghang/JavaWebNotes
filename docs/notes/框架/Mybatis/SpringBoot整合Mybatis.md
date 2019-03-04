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
## 