# 10.内部类
## 迭代器模式的一个例子

假如我们想调用一个对象的的迭代器，那么这个迭代器是什么呢？如果容器都可以使用这个迭代器，那么在容器中肯定是返回一个共有接口的方法，并且返回的属于自己的的迭代器。

共有的迭代器应该长这样：
```java
interface Selector{
	boolean end();
	Object current();
	void next();
}
```
自身的迭代器是实现了公共接口的普通类，如果想要调用容器的成员变量，那么肯定只能作为内部类。
```java
public class Sequence {
	private Object[] items;
	private int next;
	public Sequence(){items = new Object[10];}
	public Sequence(int size){ items = new Object[size];}
	public void add(Object o){
		if (next < items.length) {
			items[next++] = o;
		}
	}
	private class SequenceSelector implements Selector{
		int i;
		public boolean end(){ return i==items.length;}
		public Object current(){return items[i];}
		public void next(){if (i<items.length) i++;} 
	}
	public Selector selector(){return new SequenceSelector();}
	public static void main(String[] args) {
		Sequence sequence = new Sequence();
	    for(int i=0;i<10;i++){
	    	sequence.add(i);
	    }
	    Selector selector = sequence.selector();
	    while (!selector.end()) {
	    	System.out.println(selector.current());
			selector.next();
		}
	}
}
```
众所周知，一个没有初始化的变量是没法用的，但是内部类SequenceSelector里面的i是没有初始化，但却可以用，这应该是JVM在遇到new关键字就会将变量全都设为默认值。
## 匿名内部类
匿名内部类就是说将某个继承其父类或者实现接口的子类名称藏匿起来，因为可以直接可以调用其父类接口来获取其内容，所以名称没有存在的必要，使代码结构更加紧凑。

最关键的是匿名内部类最外层只是一个方法，关键是如何实现里面的类，里面的类构造器如何传参，在没有名字的情况下如何做一些构造器的行为。
### 第一类:实现接口
```java
interface Contents{
	int value();
}
//匿名 使用了MyContents的默认构造器
public class Anonymous{
	public Contents contents(){
		return new Contents(){
			private int i=3;
			public int value(){return i;}
		};
	}
}
//非匿名
public class NotAnonymous{
	public Contents contents(){return new MyContents();}
	class MyContents implements Contents{
		private int i=3;
		public int value(){return i;}
	}
}
```
第二类:继承类
```java
class Wrapping {
	private int i;
	public Wrapping(int x){i=x;}
	public int value(){return i;}
}
//匿名 使用Wrapping的有参构造器
public class Anonymous{
	public Wrapping wrapping(int x){
		return new Wrapping(x){
			public int value(){
				return super.value()*47;
			}
		};
	} 
}
//非匿名
public class NotAnonymous{
	class MyWrapping extends Wrapping{
		public MyWrapping(int x){}
		public int value(){return super.value()*47;}
	}
	pubic Wrapping wrapping(int x){
		return new MyWrapping(x);
	}
}
```
使用外部定义的对象应该是匿名内部类特有的属性，非匿名用不了
```java
class Wrapping {
	private int i;
	public Wrapping(){}
	public Wrapping(int x){i=x;}
}

public class NotAnonymous{
	class MyWrapping extends Wrapping{
		String lbale = string;//这里报错
		public MyWrapping(){}
		public MyWrapping(int x){System.out.println("nih");}
	}
	public Wrapping wrapping(final String string){
		return new Wrapping(){
			String label=string;//不报错
		};
	}
}
```
还可以为匿名内部类做类似构造器的行为
```java
public class Anonymous{
	public Destination destination(final String dest,final float price){
		return new Destination(){
			private int cost;
			{
				cost = Math.round(price);
				if(cost>100){System.out.print("Over Budgt!")}
			}
			private String label=dest;
			public String readLabel(){return label;}
		}
	}
}
```



