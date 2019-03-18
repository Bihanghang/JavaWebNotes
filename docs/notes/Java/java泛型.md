## 泛型方法
泛型方法要可以放在普通类和泛型类之中：
```java
<T> T getGeneric(T... a){
    return a[a.length/2];
}

String gets(String... aStrings){
    return aStrings[0];
}<T> T getGeneric(T... a){
    return a[a.length/2];
}

String gets(String... aStrings){
    return aStrings[0];
}
```
但是使用需要在尖括号先定义。