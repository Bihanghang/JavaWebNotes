# Redis添加密码Windows下
在redis的安装目录下找到 redis.windows-service.conf 文件。用文本编辑器打开,
找到requirepass所在行,回车另起一行,输入requirepass 你的密码,
```
# requirepass foobared
requirepass yourpassword  //此处注意，行前不能有空格
```
这样就设置了redis的密码,设置好保存后,若要使设置起作用,需要重启redis服务。

由于没有找到windows cmd窗口下重启redis服务的命令,所以使用的是windows服务来重启。

找到redis所在的目录，将redis注册为服务
```
redis-server.exe --service-install redis.windows.conf 
```

windows+R打开运行，在运行中输入
```
services.msc
```
打开windows下的服务

重启redis服务，这时才能生效。

这里还有个坑
```
redis-cli.exe -h 127.0.0.1 -p 6379 -a 密码
```
无论密码正确与否，这个命令都是可以进入redis的，但是如果密码错误，命令是不会执行的。

像这样
```
127.0.0.1:6379> keys *
(error) NOAUTH Authentication required.
127.0.0.1:6379>
```
只有密码正确，指令才能执行。
# Redis缓存在Spring的使用
## 具体思路
思路很简单，就是在查询数据的时候，先检查redis数据库中有没有，要是有就把它拿出来，没有就先从mysql中取出来，再存到redis中。主要是利用aop的advisor在查mysql之前做一下判断。

## 1.下载Redis到windows并且修改密码
[具体请看](https://www.cnblogs.com/bihanghang/p/10018327.html)
## 2.整合spring与redis
1.添加依赖
```
<!--redis-->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
    <version>1.6.1.RELEASE</version>
</dependency>
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.7.3</version>
</dependency>
```
2.在spring配置文件中添加
```
<!-- jedis 配置 -->
<bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
    <property name="maxIdle" value="${redis.maxIdle}"/>
    <property name="maxWaitMillis" value="${redis.maxWait}"/>
    <property name="testOnBorrow" value="${redis.testOnBorrow}"/>
</bean>
<!-- redis服务器中心 -->
<bean id="connectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
    <property name="poolConfig" ref="poolConfig"/>
    <property name="port" value="${redis.port}"/>
    <property name="hostName" value="${redis.host}"/>
    <property name="password" value="${redis.password}"/>
    <property name="timeout" value="${redis.timeout}"></property>
</bean>
<bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
    <property name="connectionFactory" ref="connectionFactory"/>
    <property name="keySerializer">
        <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
    </property>
    <property name="valueSerializer">
        <bean class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
    </property>
</bean>
```
## 3.编写redis工具类来存取数据
```
@Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //设置过期时间单位秒
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public Object get(final String key){
        Object value = null;
        ValueOperations<Serializable,Object> operations = redisTemplate
                .opsForValue();
        value = operations.get(key);
        return  value;
    }
```
## 4.编写MethodCacheInterceptor来写逻辑
```java
@Component("methodCacheInterceptor")
public class MethodCacheInterceptor implements MethodInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    private Long defaultCacheExpireTime; // 缓存默认的过期时间
    private Long xxxRecordManagerTime; //
    private Long xxxSetRecordManagerTime; //

    public MethodCacheInterceptor() {
            // 加载过期时间设置
            defaultCacheExpireTime = 360L;
    }
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object value = null;
        String targetName = methodInvocation.getThis().getClass().getName();
        String methodName = methodInvocation.getMethod().getName();
        //获取参数
        Object[] arguments = methodInvocation.getArguments();
        String key = getCacheKey(targetName, methodName, arguments);

        try {
            if (redisUtil.exists(key)) {
                return redisUtil.get(key);
            }
            value = methodInvocation.proceed();

            if (value != null) {
                final String fkey = key;
                final Object fvalue = value;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        redisUtil.set(fkey, fvalue, defaultCacheExpireTime);
                    }
                }).start();
            }
        }catch (Exception e){
            e.printStackTrace();
            if (value == null){
                return methodInvocation.proceed();
            }
        }
        return value;
    }

    public String getCacheKey(String targetName,String methodName,Object[] arguments){
        StringBuffer key = new StringBuffer();
        key.append(targetName).append("_").append(methodName);
        for (Object o:arguments) {
            key.append(o).append("_");
        }
        return key.toString();
    }
}
```
## 5.配置一下advisor
```
<aop:config proxy-target-class="false">

<aop:pointcut id="redisMethodePointcut"
                expression="execution(* com.bihang.service.*.select*(..))" />
<aop:advisor advice-ref="methodCacheInterceptor" pointcut-ref="redisMethodePointcut"/>

</aop:config>
```

