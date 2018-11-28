## 切面
横切关注点可以被模块化为特殊的类，这些类被称为切面(aspect)。
## 通知
切面也有目标 —— 它必须要完成的工作。在 AOP 术语中，切面的工作被称为通知。

Spring 只支持方法连接点，
如果你的 AOP 需求超过了简单的方法调用（如构造器或属性拦截），那么你需要考虑使用 AspectJ 来实现切面。
## proxy-target-class 
proxy-target-class="true"和proxy-target-class="false"的区别，为true则是基于类的代理将起作用（需要cglib库），为false或者省略这个属性，则标准的JDK 基于接口的代理将起作用。
