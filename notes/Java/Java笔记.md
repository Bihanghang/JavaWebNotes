# Compare
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
必须知道的几个方法:
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
# 线程的生命周期六个状态
* New(新创建)
* Runnable(可运行)
* Blocked(被阻塞)
* Waiting(等待)
* Timed Waiting(计时等待)
* Terminated(被终止)
## Runnable(可运行)
记住，在任何给定时刻，二个可运行的线程可能正在运行也可能没有运行（这就是为什
么将这个状态称为可运行而不是运行 )
。
## Waiting(等待)
当线程等待另一个线程通知调度器一个条件时
， 它自己进入等待状态 。 在调用 Object . wait 方法或 Thread . join 方法
， 或者是等待 java ,
util . concurrent 库中的 Lock 或 Condition 时 ， 就会出现这种情况
。
## Terminated(被终止)
线程因如下两个原因之一而被终止 ：
  * 因为`run`方法正常退出而自然死亡 。
  * 因为一个没有捕获的异常终止了`run`方法而意外死亡 。

# 线程的3种创建方式
1.继承Thread类创建线程

2.实现Runnable接口创建线程

3.使用Callable和Future创建线程






