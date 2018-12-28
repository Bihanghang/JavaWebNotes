在使用SpringCloud的时候需要在SpringBoot工程的pom.xml中引入
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Dalston.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
此时就要注意SpringCloud与SpringBoot的兼容问题，像上述的SpringCloud版本为Dalston，那么与之对应的SpringBoot版本就应该为1.5.x,像这样
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.10.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```
此时SpringBoot版本为1.5.10,是兼容的。

版本兼容表：
```
Spring Cloud	                    Spring Boot

Finchley	                    兼容Spring Boot 2.0.x，不兼容Spring Boot 1.5.x
Dalston和Edgware	            兼容Spring Boot 1.5.x，不兼容Spring Boot 2.0.x
Camden	                        兼容Spring Boot 1.4.x，也兼容Spring Boot 1.5.x
Brixton	                        兼容Spring Boot 1.3.x，也兼容Spring Boot 1.4.x
Angel	                        兼容Spring Boot 1.2.x
```