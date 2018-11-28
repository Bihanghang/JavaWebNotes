Spring 提供了三个这样的数据源类（均位于 org.springframework.jdbc.datasource 包中）供选择：
* DriverManagerDataSource ：在每个连接请求时都会返回一个新建的连接。与 DBCP 的 BasicDataSource 不同，由 DriverManagerDataSource 提供的连接并没有进行池化管理；
* SimpleDriverDataSource ：与 DriverManagerDataSource 的工作方式类似，但是它直接使用 JDBC 驱动，来解决在特定环境下
的类加载问题，这样的环境包括 OSGi 容器；
* SingleConnectionDataSource ：在每个连接请求时都会返回同一个的连接。尽管SingleConnectionDataSource 不是严格意义上的连接池数据源，但是你可以将其视为只有一个连接的池。

以上三个并不具备连接池功能所以推荐用第四个,注意BasicDataSource提供了close()方法关闭数据源，所以必须设定destroy-method=”close”属性， 以便Spring容器关闭时，数据源能够正常关闭。
```xml
<!-- 引入配置文件 -->
<bean id="propertyConfigurer"
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="location" value="classpath:jdbc.properties" />
</bean>

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
    destroy-method="close">
    <property name="driverClassName" value="${driver}" />
    <property name="url" value="${url}" />
    <property name="username" value="${username}" />
    <property name="password" value="${password}" />
    <!-- 初始化连接大小 -->
    <property name="initialSize" value="${initialSize}"></property>
    <!-- 连接池最大数量 -->
    <property name="maxActive" value="${maxActive}"></property>
    <!-- 连接池最大空闲 -->
    <property name="maxIdle" value="${maxIdle}"></property>
    <!-- 连接池最小空闲 -->
    <property name="minIdle" value="${minIdle}"></property>
    <!-- 获取连接最大等待时间 -->
    <property name="maxWait" value="${maxWait}"></property>
</bean>
```
## 关于JNDI数据源介绍
在Tomcat4.1.27之后，在服务器上就直接增加了数据源的配置选项，可以直接在服务器上配置号数据源连接池。

客户端通过名称找到在JNDI树上绑定的DataSource，再由DataSource找到一个连接。[具体请看](https://www.cnblogs.com/xdp-gacl/p/3951952.html)
