0.添加依赖
```
<!--druid连接池-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.0.18</version>
</dependency>
```
1.属性文件:jdbc.properties(DataSource连接参数)
```
jdbc.driverClass=com.mysql.jdbc.Driver

#第一个数据源
jdbc.url=jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&characterEncoding=utf8
jdbc.user=root
jdbc.password=721214wan..

#第二个数据源
jdbc.url2=jdbc:mysql://127.0.0.1:3306/ssm2?useUnicode=true&characterEncoding=utf8
jdbc.user2=root
jdbc.password2=721214wan..
```
2.spring配置文件,spring-mybatis.xml
```
<!-- 引入配置文件 -->
<bean id="propertyConfigurer"
        class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="location" value="classpath:jdbc.properties" />
</bean>
<bean id="ssm1DataSource" class="com.alibaba.druid.pool.DruidDataSource"
        init-method="init" destroy-method="close">
    <!-- 指定连接数据库的驱动 -->
    <property name="driverClassName" value="${jdbc.driverClass}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.user}" />
    <property name="password" value="${jdbc.password}" />

    <!-- 配置初始化大小、最小、最大 -->
    <property name="initialSize" value="3" />
    <property name="minIdle" value="3" />
    <property name="maxActive" value="20" />

    <!-- 配置获取连接等待超时的时间 -->
    <property name="maxWait" value="60000" />

    <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
    <property name="timeBetweenEvictionRunsMillis" value="60000" />

    <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
    <property name="minEvictableIdleTimeMillis" value="300000" />

    <property name="validationQuery" value="SELECT 'x'" />
    <property name="testWhileIdle" value="true" />
    <property name="testOnBorrow" value="false" />
    <property name="testOnReturn" value="false" />

    <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
    <property name="poolPreparedStatements" value="true" />
    <property name="maxPoolPreparedStatementPerConnectionSize"
                value="20" />

    <!-- 配置监控统计拦截的filters，去掉后监控界面sql无法统计 -->
    <property name="filters" value="stat" />
</bean>

<bean id="ssm2DataSource" class="com.alibaba.druid.pool.DruidDataSource"
        init-method="init" destroy-method="close">
    <!-- 指定连接数据库的驱动 -->
    <property name="driverClassName" value="${jdbc.driverClass}"/>
    <property name="url" value="${jdbc.url2}"/>
    <property name="username" value="${jdbc.user2}"/>
    <property name="password" value="${jdbc.password2}"/>
    <property name="initialSize" value="3"/>
    <property name="minIdle" value="3"/>
    <property name="maxActive" value="20"/>
    <!-- 配置获取连接等待超时的时间 -->
    <property name="maxWait" value="60000"/>
    <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
    <property name="timeBetweenEvictionRunsMillis" value="60000"/>
    <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
    <property name="minEvictableIdleTimeMillis" value="300000"/>
    <property name="validationQuery" value="SELECT 'x'"/>
    <property name="testWhileIdle" value="true"/>
    <property name="testOnBorrow" value="false"/>
    <property name="testOnReturn" value="false"/>
    <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
    <property name="poolPreparedStatements" value="true"/>
    <property name="maxPoolPreparedStatementPerConnectionSize"
                value="20"/>
    <!-- 配置监控统计拦截的filters，去掉后监控界面sql无法统计 -->
    <property name="filters" value="stat"/>
</bean>

```
3.WEB方式监控配置,在web.xml中添加
```
<!-- druid 监控 -->
<filter>
    <filter-name>DruidWebStatFilter</filter-name>
    <filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
    <init-param>
        <param-name>exclusions</param-name>
        <param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/download/*</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>DruidWebStatFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
<servlet>
    <servlet-name>DruidStatView</servlet-name>
    <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
    <init-param>
        <!-- 用户名 -->
        <param-name>loginUsername</param-name>
        <param-value>druid</param-value>
    </init-param>
    <init-param>
        <!-- 密码 -->
        <param-name>loginPassword</param-name>
        <param-value>druid</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>DruidStatView</servlet-name>
    <url-pattern>/druid/*</url-pattern>
</servlet-mapping>
```
浏览器输入 http://IP:PROT/druid.