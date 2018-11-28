## 建议
我的建议是尽可能地使用自动配置的机制。显式配置越少越好。当你必须要显式配置 bean 的时候（比如，有些源码不是由你来维护
的，而当你需要为这些代码配置 bean 的时候），我推荐使用类型安全并且比 XML 更加强大的 JavaConfig 。最后，只有当你想要使用便利的 XML
命名空间，并且在 JavaConfig 中没有同样的实现时，才应该使用 XML 。

##  @ContextConfiguration的意思

1.`@ContextConfiguration(class=CDPlayerConfig.class)`

注解 @ContextConfiguration 会告诉它需要在 CDPlayerConfig 中加载配置。

CDPlayConfig.class的写法
```java
@Configuration
@ComponentScan
public class CDPlayConfig {

}
```
@Configuration表示这是一个配置类，@ComponentScan这个注解能够在 Spring 中启用组件扫描，如果没有其他配置的话，@ComponentScan 默认会扫描与配置类相同的包。

如果使用 XML 来启用组件扫描的话，那么可以使用 Spring context 命名空间的 <context:component-scan> 元素。

换句话说`@ContextConfiguration(class=CDPlayerConfig.class)`就表示测试单元可以使用CDPlayerConfig扫描到到所有bean。

2.`@ContextConfiguration(locations = {"classpath*:/*.xml"})`

classpath和classpath*区别： 

* classpath：只会到你的class路径中查找找文件。

* classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。

# 防止因为没有bean抛异常
如果没有匹配的 bean ，那么在应用上下文创建的时候， Spring 会抛出一个异常。为了避免异常的出现，你可以将 @Autowired 的 required 属性设置为 false。
# 默认情况下， Spring 中的 bean 都是单例的
