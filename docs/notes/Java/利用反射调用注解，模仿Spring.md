# 简介
在开发中，我们经常用的就是利用@RequestMapping来调用我们自己的逻辑，现在我们来创建属于自己的注解模仿一下它。

1.新建属于自己的注解@SeayaMapping
```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SeayaMapping {
    String value();
}
```
2.创建类使用@SeayaMapping标注
```java
@SeayaMapping("/say")
public class SeayaTest {
    
    @SeayaMapping("/hello")
    public void sayHello(String s){
        System.out.println(s);
    }
}
```
3.创建好之后，假如现在客户端访问http://localhost:8080/say/hello, 当我们已经在服务端获取到这个请求时，就需要调用这个方法。

首先我们得拿到包下所有的类，主要是通过启动类获取包名，在通过包名获取所有类。[通过包名获取所有类](https://www.cnblogs.com/bihanghang/p/10208268.html).

接下来就是遍历每一个类，如果这个类有@SeayaMapping注解，那个再获取这个类的所有方法，然后遍历所有方法，如果方法有@SeayaMapping注解，利用invoke()方法来执行。

写出来就变成这样了，好可怕的代码。。。

```java
for ( Class<?> aClass:
        classes) {
    for ( Annotation classA:
            aClass.getAnnotations()) {
        if (classA instanceof SeayaMapping && ((SeayaMapping) classA).value().equals("/say")){
            for (Method m :
                    aClass.getMethods()) {
                for (Annotation a :
                        m.getAnnotations()) {
                    if (a instanceof SeayaMapping && ((SeayaMapping) a).value().equals("/hello"))
                        m.invoke(aClass.newInstance(),"Ok beng");
                }

            }
        }
    }
}
```


