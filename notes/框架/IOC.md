## IOC
IOC--Inversion of Control即控制反转，常常和DI--依赖注入一起被提到。

核心是为了解除程序之间的耦合度。

那么什么样的代码是耦合度高的呢？

假如有个人现在去买苹果
```java
interface Fruit{}
class Apple implements Fruit{}
class Person{
    private Apple apple;
    public Person(){
        apple = new Apple();
    }
}
```
然后家里有苹果了，又去买梨子，这时候就得改代码
```java
class Pear implements Fruit{}
class Person{
    private Pear pear;
    public Person(){
        pear = new Pear();
    }
}
```
再买别的就得一直改代码。
假如把代码解耦，只留下接口，写成这样
```java
class Person{
    public Fruit fruit;
    public Person(Fruit fruit){
        this.fruit = fruit;
    }
}
```
可以看到Person构造器没有自行创建要买的水果，而是将水果这个接口作为参数传递进来，我们称这种方式为<font color=red size=4>构造器注入</font>。

假如现在再买橘子，只需要创建橘子类和修改Spring的配置文件就可以，不用修改Person类的代码。
```
class Orange implements Fruit{}

<bean id="orange" class="cn.bh.springtest.Orange" />

<bean id="person" class="cn.bh.springtest.Person">
<constructor-arg ref="orange">
</bean>
```
在测试类里面测试
```java
ApplicationContext applicationContext =new ClassPathXmlApplicationContext("spring.xml");
Person person = (Person) applicationContext.getBean("person");
System.out.println(person.fruit.toString());//输出cn.bh.springtest.Orange@6895a785
```
可以看到没有改动代码的情况下买到了橘子。


