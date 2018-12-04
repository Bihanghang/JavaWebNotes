## `@Transactional(propagation = Propagation.REQUIRED)` 报错 
可能是导错包,自动注入失败，一般来说是`<context:component-scan base-package="com" />`的问题，但是还可能是配置路径有问题导致找不到想要的东西

## Cannot change version of project facet Dynamic Web Module to 2.5.
在目录工程下找到.settings文件夹，里面有个org.eclipse.wst.common.project.facet.core文件
作如下修改:
```
<installed facet="jst.web" version="3.0"/>
```
这里应该与web.xml下的version一致
```
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">
```
右击项目找到Maven->Update Project,点击更新。

## @RunWith(SpringJUnit4ClassRunner.class)  // 此句报错
将JUnit版本换为4.12版本

## JoinPoint找不到包
添加依赖
```
<dependency>
<groupId>org.aspectj</groupId>
<artifactId>aspectjweaver</artifactId>
<version>1.8.6</version>
</dependency>
```
## Mybaits依赖注入失败
先前是逆向生成的简单类，后来添加了example，就报错，将example类都删除，重新换回简单类就行，如果一开始就是example也不会报错。


