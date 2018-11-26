## 通過表生成代碼只需要以下3個步驟
1.在Eclipse中新建Maven工程在pom.xml中引入代碼生成器插件
```pom
<build>
    <finalName>generator</finalName>
    <plugins>
 <plugin>
  <groupId>org.mybatis.generator</groupId>
  <artifactId>mybatis-generator-maven-plugin</artifactId>
  <version>1.3.2</version>
  <configuration>
  <configurationFile>src\main\java\generatorConfig.xml</configurationFile>
  <verbose>true</verbose>
  <overwrite>true</overwrite>
  </configuration>
 </plugin>
 </plugins>
  </build>
  ```
2.配置剛剛由代碼生成器引入的generatorConfig.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
 
<generatorConfiguration>
	<classPathEntry location="E:\M2_Repository\mysql\mysql-connector-java\5.1.10\mysql-connector-java-5.1.10.jar"/>
	<!-- context元素用于指定生成一组对象的环境。targetRuntime:此属性用于指定生成的代码的运行时环境。MyBatis3:*这是默认值*-->
	<context id="testTables" targetRuntime="MyBatis3">
		<commentGenerator>
			<!-- 是否去除自动生成的注释 true：是 ： false:否 -->
			<property name="suppressAllComments" value="true" />
		</commentGenerator>
		<!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
		<jdbcConnection driverClass="com.mysql.jdbc.Driver"
			connectionURL="jdbc:mysql://127.0.0.1:3306/test" userId="root"
			password="721214wan..">
		</jdbcConnection>
		<!-- 如使用oracle请参考如下 -->
		<!-- <jdbcConnection driverClass="oracle.jdbc.driver.OracleDriver"
			connectionURL="jdbc:oracle:thin:@127.0.0.1:1521:orcl" 
			userId="scott"
			password="wcy675600920">
		</jdbcConnection> -->
 
		<!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和 
			NUMERIC 类型解析为java.math.BigDecimal -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>
 
		<!-- targetProject:生成PO类的位置 -->
		<javaModelGenerator targetPackage="model"
			targetProject=".\src">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
			<!-- 从数据库返回的值被清理前后的空格 -->
			<property name="trimStrings" value="true" />
		</javaModelGenerator>
        <!-- targetProject:mapper映射文件生成的位置 -->
		<sqlMapGenerator targetPackage="mapper" 
			targetProject=".\src">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
		</sqlMapGenerator>
		<!-- targetPackage：mapper接口生成的位置 -->
		<javaClientGenerator type="XMLMAPPER"
			targetPackage="mapper" 
			targetProject=".\src">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
		</javaClientGenerator>
		
		<!-- 指定数据库表 -->
		<table tableName="t_role" schema="" enableCountByExample="false"
				enableDeleteByExample="false" enableUpdateByExample="false"
				enableSelectByExample="false" selectByExampleQueryId="false"></table>
				
	
		<!-- 有些表的字段需要指定java类型
		 <table schema="" tableName="">
			<columnOverride column="" javaType="" />
		</table> -->
	</context>
</generatorConfiguration>
```
3.在src/main目錄下新建一個名稱為resources的文件夾（雖然不知道是幹嘛用的，不建的就會報錯).

4.右擊run as –>maven build，在Goals:中填入mybatis-generator:generate再點擊run(運行)，右鍵刷新項目就可以了。
