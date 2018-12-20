# 单模块
1.在pom.xml中添加依赖
```
<build>
    <plugins>
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
            <configuration>
                <verbose>true</verbose>
                <overwrite>true</overwrite>
            </configuration>
        </plugin>
    </plugins>
</build>
```
2.创建generatorConfig.xml文件
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <classPathEntry location="E:\M2_Repository\mysql\mysql-connector-java\5.1.10\mysql-connector-java-5.1.10.jar"/>

    <context id="context">
        <commentGenerator>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <jdbcConnection
                connectionURL="jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8"
                driverClass="com.mysql.jdbc.Driver" password="721214wan.." userId="root"/>
        <javaModelGenerator targetPackage="com.bibang.pojo"
                            targetProject="src/main/java"/>
        <sqlMapGenerator targetPackage="mapping"
                         targetProject="src/main/resources"/>
        <javaClientGenerator targetPackage="com.bibang.dao"
                             targetProject="src/main/java" type="XMLMAPPER"/>
        <!-- 有些表的字段需要指定java类型
        <table schema="" tableName="">
            <columnOverride column="" javaType="" />
        </table> -->
    </context>
</generatorConfiguration>
```
3.RUN->Edit Configurations...

在Command line:填入mybatis-generator:generate

在回到界面点及RUN。

# Idea多模块逆向

## 1.添加插件
```xml
<build>
    <plugins>
    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
            <configurationFile>ceair-sampledemo-user-repository\src\main\resources\db_user_gen.xml</configurationFile>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
        </configuration>
    </plugin>
    </plugins>
</build>
```
## 2.配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!--
  ~ Copyright (c) 2018 www.ceair.com Inc. All rights reserved.
  -->

<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>

    <classPathEntry location="/Users/bihang/.m2/repository/mysql/mysql-connector-java/5.1.45/mysql-connector-java-5.1.45.jar"/>

    <context id="context" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressAllComments" value="false"/>
            <property name="suppressDate" value="true"/>
        </commentGenerator>

        <jdbcConnection userId="root" password="111111" driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://pc.ceair.com:3306/db_sample_demo_user?useSSL=false"/>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="com.ceair.sampledemo.user.model.pojo.auto" targetProject="ceair-sampledemo-user-model/src/main/java">
            <property name="enableSubPackages" value="false"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="mappers.auto" targetProject="ceair-sampledemo-user-repository/src/main/resources">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

        <javaClientGenerator targetPackage="com.ceair.sampledemo.user.repository.auto" type="XMLMAPPER" targetProject="ceair-sampledemo-user-repository/src/main/java">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>

        <table schema="db_sample_demo_user" tableName="td_user" enableCountByExample="true"
               enableDeleteByExample="true" enableSelectByExample="true" enableUpdateByExample="true"/>

    </context>
</generatorConfiguration>
```
## 3.添加文件夹src/main/resources