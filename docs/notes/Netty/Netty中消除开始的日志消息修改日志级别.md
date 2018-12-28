我是使用logback作为日志打印，之前使用slf4j,日志打印不出，就干脆换掉了。<br>
1.首先引入依赖
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-access</artifactId>
    <version>${logback.version}</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>${logback.version}</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>${logback.version}</version>
</dependency>
```
2.然后在src/main/resources下添加logback.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<configuration>
    <appender name="consoleLog" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %msg%n
            </pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="consoleLog"></appender-ref>
    </root>
</configuration>
```
虽然引入的是logback,但是里面获得的logger还是slf4j。。。