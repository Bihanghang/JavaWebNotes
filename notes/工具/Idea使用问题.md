## 启动tomcat之后访问报404
选择菜单栏“Run-->Edit Configuration...-->Deployment”,选择右上角绿色“+”，选择“External Source...”，将Apache-tomcat-8.0.33的webapps目录下的ROOT文件夹选中，点击OK，及完成Tomcat的首页的工程的部署。
## Idea无法运行Maven项目
导入项目到tomcat的时候要选择Arifact 如果maven项目没有这个选项，
```
<groupId>com.bihang</groupId>
<artifactId>SSM2</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>war</packaging>
```
注意添加`<packaging>war</packaging>`这句代码。