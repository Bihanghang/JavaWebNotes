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

思想很简单，如果是文件则复制，如果是文件夹则先复制文件夹再递归一下文件夹下的所有文件，关键是用好getAbsolutePath()，getName()这两个方法。
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






