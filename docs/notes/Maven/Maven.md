# 下载安装
**1**.下载Binary zip archive
**2**.新建环境变量`MAVEN_HOME`，复制Maven安装所在的目录 
**3**.修改环境变量PATH，追加`%MAVEN_HOME%\bin`

# 基本概念
groupId 

定义了项目属于哪个组，举个例子，如果你的公司是`mycom`，有一个项目为`myapp`，那么groupId就应该是`com.mycom.myapp`. 

artifacted 

定义了当前maven项目在组中唯一的ID,比如，`myapp-util`,`myapp-domain`,`myapp-web`等。 

# 子模块共享版本号
可以在父模块中的`dependencyManagement`定义公共依赖, 然后子模块中依赖相应的dependency时，就不用写version字段了，只写groupId跟artifactId就可以了

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
<font color=4>如果引入依赖的时候不加版本号，回到引入失败.</font>
# VSCode安装java项目
四个插件
* Language Support for Java(TM) by Red Hat：通过 Eclipse ™ JDT Language Server 提供 Java 语言支持
* Debugger for Java：基于 Java Debug Server 的轻量级调试工具。
* Java Test Runner：执行和调试 Java 测试用例。
* Maven for Java：Maven 插件。

在新建的文件下右击选择Generate from Maven Archetype

# 将Oracle数据库连接jar包加载到Maven本地仓库
在本地仓库中运行以下命令：
```
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.1.0 -Dpackaging=jar -Dfile=ojdbc14.jar
```
在pom.xml添加依赖
```
<dependency>
	<groupId>com.oracle</groupId>
	<artifactId>ojdbc14</artifactId>
	<version>10.2.0.1.0</version>
</dependency>
```
# Eclipse的Maven项目，无法显示src/main/java资源文件夹
在项目上右键选择properties，然后点击java build path，在Librarys下，编辑JRE System Library，选择workspace default jre就可以了。

# Maven中-DskipTests和-Dmaven.test.skip=true的区别
在使用mvn package进行编译、打包时，Maven会执行src/test/java中的JUnit测试用例，有时为了跳过测试，会使用参数-DskipTests和-Dmaven.test.skip=true，这两个参数的主要区别是：<br>
**-DskipTests**，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。<br>
**-Dmaven.test.skip=true**，不执行测试用例，也不编译测试用例类。<br>
`mvn -Dmaven.test.skip=true clean package`就是先清理再生成Jar包