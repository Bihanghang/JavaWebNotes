1，如果你的POM是继承spring-boot-starter-parent的话，只需要下面的指定就行。

```xml
<properties>
    <!-- The main class to start by executing java -jar -->
    <start-class>com.mycorp.starter.HelloWorldApplication</start-class>
</properties>
```
2，如果你的POM不是继承spring-boot-starter-parent的话，需要下面的指定。
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <layout>ZIP</layout>
        <executable>true</executable>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

另外，打包相关的名字一定要相同，不然找不到路径。