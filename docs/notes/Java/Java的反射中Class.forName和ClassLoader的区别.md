## ClassLoader
`ClassLoader`就是遵循双亲委派模型最终调用启动类加载器的类加载器，实现的功能是“通过一个类的全限定名来获取描述此类的二进制字节流”，获取到二进制流后放到JVM中。
例如`ClassLoader.getSystemClassLoader().loadClass("com.test.mytest.ClassForName");`是不会初始化类的。

## Class.forName()
`Class.forName()`方法实际上也是调用的CLassLoader来实现的。

`Class.forName(String name, boolean initialize,ClassLoader loader)`方法来手动选择在加载类的时候是否要对类进行初始化。