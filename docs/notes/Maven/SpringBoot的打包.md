打包是根据pom.xml文件来打的包。

如果使用Maven的默认打包，只会将src/main下的`java`和`resources`里面的内容打进Jar包中。

通过`maven-assembly-plugin`这个插件可以将所有依赖引入Jar包或者是Zip包等等。

通过`spring-boot-maven-plugin`这个插件可以将使得SpringBoot项目可以启动，最直观的就是打好的包下，META-INF这个文件下的MANIFEST.MF文件添加了启动类，并且打好的包下多了BOOT-INF这个文件夹。

`**/*` : 所有路径下的所有文件
`../`  : 上一层目录

## SpringBoot工程Maven打包显示包不存在
如果各个模块都是SpringBoot工程，需要将各个模块的pom.xml文件中的SpringBoot的maven插件删除掉。
```xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
```
## 配置文件配置在启动类中报错配置文件违法自动导入
重新在报错的模块下新建configure文件，引入bean，如下。
```java
@Configuration
public class Pkejkj {
    @Bean
    @ConfigurationProperties(prefix = "global")
    public CarGlobalSetting carGlobalSetting() {
        return new CarGlobalSetting();
    }
}
```

如果项目报错**无法加载主类**，clean一下。