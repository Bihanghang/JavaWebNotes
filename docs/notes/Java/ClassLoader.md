# 双亲委派模型
基本的类加载器有三个，AppClassLoader，ExtentionClassLoader，BootStrapClassLoader。当需要加载一个类时，先调用父级加载器，也就是说加载顺序是BootStrapClassLoader-->ExtentionClassLoader-->AppClassLoader.
