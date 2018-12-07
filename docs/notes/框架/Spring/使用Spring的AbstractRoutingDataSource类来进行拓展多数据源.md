## 1.继承抽象类`AbstractRoutingDataSource`
通过重写这个抽象类的`determineCurrentLookupKey()`方法来决定返回哪个数据库。

## 2.配置多个数据库
```
<bean id="dataSource" class="com.bihang.util.DynamicDataSource">
    <property name="targetDataSources">
        <map key-type="java.lang.String">
            <entry key="ssm1DataSource" value-ref="ssm1DataSource"/>
            <entry key="ssm2DataSource" value-ref="ssm2DataSource"/>
        </map>
    </property>
    <!--默认数据源-->
    <property name="defaultTargetDataSource" ref="ssm1DataSource"/>
</bean>
```
因为`com.bihang.util.DynamicDataSource`这个类继承了`AbstractRoutingDataSource`这个抽象类，所以<font color=red>也继承了父类的成员</font>，所以利用依赖注入将数据源名称和对应的数据库注入进去。

当`Mybatis`用到数据库就会找这个叫`dataSource`的数据源，而此时`dataSource`是有多个数据源的，具体用哪一个它会去找刚刚继承实现的`determineCurrentLookupKey()`这个方法返回的数据源名称，从而决定使用那个数据源。数据源名称是使用[ThreadLocal](https://www.cnblogs.com/bihanghang/p/10032820.html)来保存的。

总而言之，我们现在控制`determineCurrentLookupKey()`这个抽象方法返回的数据源名称就可以决定用哪个数据库。

但是这样每次想要调用数据库时要提供对应的数据源名称，很繁琐。

我们采用将不同数据库的业务方法存放在不同的包下，然后利用反射获取包名，再用aop动态的赋值数据源名称。

aop里面会利用JoinPoint对象来获取目标对象从而获取包名。

## JoinPoint 对象
JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象. 
```
String toString();         //连接点所在位置的相关信息
String toShortString();     //连接点所在位置的简短相关信息
String toLongString();     //连接点所在位置的全部相关信息
Object getThis();         //返回AOP代理对象
Object getTarget();       //返回目标对象
Object[] getArgs();       //返回被通知方法参数列表
Signature getSignature();  //返回当前连接点签名
SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
String getKind();        //连接点类型
StaticPart getStaticPart(); //返回连接点静态部分
```
具体代码:
```java
Logger logger = Logger.getLogger(this.getClass());
public void before(JoinPoint point){
    Class<?> targetClass  = point.getTarget().getClass();
    String targetPackageName =targetClass.getName();
    logger.debug(targetPackageName);
    if (targetPackageName.contains("ssmone")){
        DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
    }else {
        DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
    }
}

public void after() {
    DataSourceHolder.setDataSources(null);
}
```

