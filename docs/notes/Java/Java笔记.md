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

# Map
Map接口有两个实现，HashMap，TreeMap

## HashMap
HashMap是无序的，速度较快。常用的方法：
```java
for(Map.Entry<String,Enployee> entry:staff.entrySet()){
	String k = entry.getKey();
	Employee v = entry.getValue();
	// do something with k,v
}
//java8用法
counts.foreach((v,k)->{
	//do something with k,v
})
```
## TreeMap
TreeMap用键的整体顺序对元素进行排序，并将其组织成搜索树。

## BigDecimal
- Note: the results of this constructor can be somewhat unpredictable. One might assume that new BigDecimal(.1) is exactly equal to .1, but it is actually equal to .10000000000000000555111512312578 27021181583404541015625. This is so because .1 cannot be represented exactly as a double (or, for that matter, as a binary fraction of any finite length). Thus, the long value that is being passed in to the constructor is not exactly equal to .1, appearances notwithstanding.  The (String) constructor, on the other hand, is perfectly predictable: new BigDecimal(".1") is exactly equal to .1,as one would expect. Therefore, it is generally recommended that the (String) constructor be used in preference to this one  

所以想要精确计算，只能用String类型来构造BigDecimal。

精度测试:
```java
BigDecimal bigDecimal = new BigDecimal("0.2681097612");
BigDecimal bigDecimal1 = bigDecimal.setScale(9, RoundingMode.HALF_EVEN);
ROUND_HALF_EVEN    银行家舍入法
向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
如果舍弃部分左边的数字为奇数，则舍入行为与 ROUND_HALF_UP 相同;
如果为偶数，则舍入行为与 ROUND_HALF_DOWN 相同。
注意，在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。
此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。
如果前一位为奇数，则入位，否则舍去。
以下例子为保留小数点1位，那么这种舍入方式下的结果。
1.15>1.2 1.25>1.2
```

## 日期

```java
/**字符串转时间*/
String text = "2018-03-04";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//"yyyy-MM-dd"，很关键不能写成"yyyy-mm-dd" 类似还有"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss"
Date date = sdf.parse(text);
```
如果涉及到加减多少天，用LocatDate
```java
LocalDate.now();
LocalDate . of ( 1999 , 12 , 31 );
LocalDate aThousandDaysLater = newYearsEve . piusDays ( 1000 ) :
year = aThousandDaysLater . getYearO ； // 2002
month = aThousandDaysLater . getMonthValueO ; / / 09
day = aThousandDaysLater . getDayOfMonth () ; // 26
注意：plusDays 方法会生成一个新的 LocalDate 对象
```
## Optional
```java
public Date get_createTime() {
	return Optional.ofNullable(_createTime).orElse(new Date());
}
```

## List动态赋值
```java
ArrayList<String> lists2 = new ArrayList<String>(){{
	add("test1");
	add("test2");
}};
```
## 判断手机号码格式是否正确
```java
public static boolean IsMobilePhone(String input){
	return Pattern.matches("^1[34578]\\d{9}$", input);
}
```
## 判断字符串日期格式
```java
public static boolean isValidDate(String str) {
	boolean convertSuccess=true;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		format.setLenient(false);
		format.parse(str);
	} catch (ParseException e) {
		// e.printStackTrace();
	// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		convertSuccess=false;
	}
	return convertSuccess;
}
```

## 当前时间添加13分钟
```java
Date date = new Date(new Date().getTime() + Integer.valueOf(carPaySetting.getPayValidityDateTrainOrder()) * 60 * 1000);
```