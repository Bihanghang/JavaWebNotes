添加以下代码并且保证项目可以扫描到:
```java
@Configuration
public class OrikaConfig {
    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }
}
```