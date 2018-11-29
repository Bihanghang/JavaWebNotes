## 解决使用相对路径时出现的问题
比如我的项目名称是SpringMVC 在浏览器中输入为http://localhost:8080/SpringMVC/login.jsp

`${pageContext.request.contextPath}`或`<%=request.getContextPath()%>`取出来的就是`/SpringMVC`.这里要注意，假如`<base href="<%=basePath%>">`这句代码是`<base href="http://localhost:8888/">`,但是他们二者结合就变成了`http://localhost:8888/SpringMVC`,多的`/`会自动去掉。
## basePath
```
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
```
这段代码的意思是获取当前项目的路径，如：http://localhost:8080/项目名称。
```
<base href="<%=basePath%>">
```
这是设置基础路径的,basePath为变量，简单的静态网页的话你设置比如：`<base href="http://www.baidu.com">`，那你下面的href属性就会以你上面设的为基准，如：`<a href="http://www.baidu.com/xxx.htm"></a>`你现在就只需要写`<a href="xxx.htm"></a>`.

## 两个工程同名css文件夹
当有两个工程同名的css文件夹时，可能会冲突，猜测是缓存的原因。