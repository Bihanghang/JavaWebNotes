# Servlet
![](https://raw.githubusercontent.com/Bihanghang/JavaWebNotes/master/notes/img/servlet生命周期.PNG)
Servlet生命周期包括四个阶段:
* 加载与实例化
  > servlet容器加载和实例化servlet，因为容器是通过反射来创建Servlet实例，所以会调用Servlet的默认构造方法(不带参数的构造方法)，所以在编写Servlet类的时候不应该提供带参数的方法。
* 初始化
  > Servlet的init()方法初始化这个对象
* 请求处理
  > Servlet容器调用Servlet的service()方法对请求进行处理,service是由web容器来调用的，我们无需对service具体内容做任何处理，service会自动的根据客户端的请求类型去调用doGet、doPost等方法，所以我们只需要做好doGet、doPost方法的实现.
* 服务终止
  > 当需要释放内存或者容器关闭时，容器就会调用Servlet实例的destroy()方法。
# 过滤器，监听器，servlet标签的加载顺序
监听器-->过滤器-->servlet标签
# ajax最简单写法
```javascript
$.post("loginServlet.do",{
        username:葫芦娃
        },
        function(data){
        });
```