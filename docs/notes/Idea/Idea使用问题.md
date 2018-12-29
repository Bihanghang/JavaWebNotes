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

## Idea热部署
1.确保tomcat下工程项目名称后面有 exploded，如果没有将其移除，选择有这个的。

2.`on ‘update‘ action：` `on frame deactication:`这两个选项都设为Update classes and resources。

# IntelliJ IDEA 注册码
`C:\Windows\System32\drivers\etc`<br>
如果在验证上述注册码的过程中，显示注册码已经被取消，则可以通过在hosts文件中，添加：
```
0.0.0.0 account.jetbrains.com
```
修改文件需要用Notepad++才可以修改成功。