@Transactional(propagation = Propagation.REQUIRED) 报错 可能是导错包

自动注入失败，一般来说是<context:component-scan base-package="com" />的问题，但是还可能是配置路径有问题导致找不到想要的东西
# Cannot change version of project facet Dynamic Web Module to 2.5.
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