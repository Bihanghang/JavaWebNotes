# 10.内部类
迭代器模式的一个例子

假如我们想调用一个对象的的迭代器，那么这个迭代器是什么呢？如果容器都可以使用这个迭代器，那么在容器中肯定是返回一个共有接口的方法，并且返回的属于自己的的迭代器。

共有的迭代器应该长这样：
```java
interface Selector{
	boolean end();
	Object current();
	void next();
}
```
自身的迭代器是实现了公共接口的普通类，如果想要调用容器的成员变量，那么肯定作为内部类，最为方便。
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
