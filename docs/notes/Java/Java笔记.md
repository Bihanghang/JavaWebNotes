# 重写Comparator排列数字

```java

/**
          //两个数比较时 返回-1时，第一个数放前面
 *       //return -1; //-1表示放在红黑树的左边,即逆序输出
        //return 1;  //1表示放在红黑树的右边，即顺序输出
        //return o;  //表示元素相同，仅存放第一个元素
 */
//将1-9按如下排列//8 6 4 2 1 3 5 7 9	
Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1%2 == 0 && o2%2 != 0) return -1;
        if(o1%2 == 0 && o2%2 == 0){
            if (o1 > o2) {
                return -1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return 1;
            }
        } 
        if(01%2 != 0 && o2%2 != 0){
            if (o1 > o2) {
                return 1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return -1;
            }
        } 
        if(01%2 != 0 && o2%2 == 0) return 1;
        throw new IllegalArgumentException("Error");
    }
});
```
# 常见的流的用法

[递归实现复制多级文件夹](#递归实现复制多级文件夹)

![](https://raw.githubusercontent.com/Bihanghang/JavaWebNotes/master/notes/img/IO流概念图.PNG)

## FileInputStream & FileOutputStream

```java
String content = null;//用来储存解码后的byte数组
int size=0;//用来存储每次从文件读取的字节数
byte[] buffer = new byte[1024];//用作读进程序与从程序写出的媒介
FileInputStream r = new FileInputStream("D:\\table.sql");//读取文件
FileOutputStream w = new FileOutputStream("kk.sql");
//每次从文件读取字节数为buffer的长度，读到尽头返回-1
while( (size=r.read(buffer)) !=-1){
    //把size容量的buffer转为字符串
    content=new String(buffer,0,size);
    System.out.println(content);
    //将buffer内容写入
    w.write(buffer);
}
r.close();
w.close();
```
## BufferReader & BufferWriter

据说缓冲流是利用将读到的数据先放在一个地方，然后一次性写入内存而不是读一个写一个。
但是这个地方是什么呢？如果是数组，那么和FileInputStream好像没什么区别
```java
BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\table.sql"));
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ke.sql"));
String string = "";
while((string=bufferedReader.readLine())!=null){
    System.out.println(string);
    bufferedWriter.write(string+"\n");
}
bufferedReader.close();
bufferedWriter.flush();
bufferedWriter.close();
```
## 递归实现复制多级文件夹

- 必须知道的几个方法:

  * isDirectory()判断文件是否是文件夹
  * mkdirs()在指定位置创建文件夹可以创建多级目录
  * mkdir()只能在本目录下创建文件夹
  * getAbsolutePath()返回绝对路径
  * getName()返回此文件的名字
  * listFiles()返回此文件下的所有文件

思想很简单，如果是文件则复制，如果是文件夹则先复制文件夹再递归一下源文件夹下的所有文件，关键是用好getAbsolutePath()，getName()这两个方法。

```java
public class NotAnonymous{
	public void copy(String src,String dest) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));
		String string = null;
		while((string = bufferedReader.readLine())!=null){
			bufferedWriter.write(string);
		}
		bufferedWriter.flush();
		bufferedReader.close();
		bufferedWriter.close();
	}
	public void copyFolder(String src,String dest) throws IOException{
		File startFoler = new File(src);
		if (startFoler.isDirectory()) {
			File kFile = new File(dest+"/"+startFoler.getName());
			kFile.mkdirs();
			File[] files = startFoler.listFiles();
			for (File file : files) {
				copyFolder(file.getAbsolutePath(), kFile.getAbsolutePath());
			}
		}else {
			copy(src, dest+"/"+startFoler.getName());
		}
	}
	public static void main(String[] args) throws IOException {
		NotAnonymous notAnonymous = new NotAnonymous();
		notAnonymous.copyFolder("E:/test", "F:/hello");
	}
}
```

# String,StringBuilder,StringBuffer

String为字符串常量，StringBuilder和StringBuffer为字符串变量

String对象一旦创建无法更改，StringBuilder和StringBuffer可以更改。

String在 str="abc"+"cd"这种情况逼StringBuilder和StringBuffer快，引文就相当于str="abccd"，字符串常量池只创建了一个str字符串

str = "abc";str+="cd"这种情况StringBuilder和StringBuffer快，因为相当于创建了"abc"和"abccd"两个字符串常量

线程情况:

String是不可变量，线程安全。

StringBuilder线程不安全，StringBuffer线程安全。

# ThreadLocal

概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式：访问串行化，对象共享化。而ThreadLocal采用了“以空间换时间”的方式：访问并行化，对象独享化。前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。

## ThreadLocal的用法

阿里巴巴 java 开发手册中推荐的 ThreadLocal 的用法：

```java
public class DateUtil {
    public static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
}
```
然后我们再要用到 DateFormat 对象的地方，这样调用：<br>
`DateUtils.df.get().format(new Date());`<br>
ThreadLocal 相当于每个线程A在创建的时候，已经为你创建好了一个 DateFormat，这个 DateFormat 在当前这个线程A中共享。其他线程B，再用到 DateFormat 的地方，也会创建一个 DateFormat 对象，这个对象会在线程 B 中共享，直到线程 B 结束。

也就是说 ThreadLocal 的用法和我们自己 new 对象一样，然后将这个 new 的对象传递到各个方法中。但是到处传递的话，太麻烦了。这个时候，就应该用 ThreadLocal。

因为数据源是公用的，所以将其设为ThreadLocal，其余线程就可以直接用了。

如果要使用 ThreadLocal，通常定义为 private static 类型，在我看来最好是定义为 private static final 类型。

# 回调

回调(callback)是一种常见的程序设计模式。在这种模式中可以指出某个特定事件发<br>
生时应该采取的动作.例如可以指出在按下鼠标或选择某个菜单项时应该采取什么行动.

# java枚举的下标

java中枚举下标值默认从0开始，可以用ordinal()这个方法获取下标值。

```java
public enum Sex {
	MALE(1,"男"),FEMALE(2,"女");
	private int id;
	private String name;
	private Sex(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static Sex getSex(int id){
		if (id==1) {
			return MALE;
		} else if (id==2) {
			return FEMALE;
		}
		return null;
	}
}
```
而MALE(1,"男")中的1是MALE内部的属性值。

枚举MALE就相当于一个对象，但注意Sex构造器是private，所以MALE只能通过set,get方法赋值取值。
