# 版本号
可以将版本号定义在properties中
```
<properties>
    <!-- spring版本号 -->
		<spring.version>4.0.2.RELEASE</spring.version>
</properties>
```
在依赖中就可以直接用了
```
<dependencies>
   <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
  </dependencies>
```