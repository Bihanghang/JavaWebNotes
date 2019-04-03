内部维护了一个缓存池，它总是Integer缓存池中获取Integer对象，超出了其缓存池缓存池（-128到127），它才new新的Integer对象。只要在这个范围内，都是直接取出对象而不是创建，所以值总是相等的.

```java
Integer a = 23;
int b = 23;
System.out.println(a==b);//true
```

```java
Integer a = 823;
int b = 823;
System.out.println(a==b);//true
```
