```java
class A {
	public String show(B obj){
		return ("A and D");
	}
	public String show(A obj) {
		return ("A and A");
	}
}
class B extends A {
	public String show(B obj) {
		return ("B and B");
	}
	public String show(A obj) {
		return ("B and A");
	}
}
public class Test2 {
	public static void main(String[] args) {
		A a2 = new B();  
		System.out.println(a2.show(a2));//输出B and A
		System.out.println(a2.getClass().getTypeName());//输出cn.bh.tt.B
		/*总结:1.当A new出子类a2，a2的类型是B,所以调用show()方法时，会先在子类B里面找，
		 * 如果找不到再去父类A里面找。
		 * 2.最有意思的是a2调用了show(A obj)而不是show(B obj),应该是因为a2是由A new出来的所以在充当
		 * 参数的时候会向上转型为A。
		 * */
	}
}
```

```java
class A{
    public int i=7;//父类属性 i
    public A(){    //父类无参构造器
		print();
	}			   //父类方法print()
	public void print(){ 
        System.out.print(i);
        System.out.println("父类方法");
	}
}
public class Polymorphic2 extends A{
	public int j=4;       //子类属性 j
	public Polymorphic2(){ //子类无参构造器
        print();
	}
	public void print(){  //子类方法print
        System.out.println(j);
        System.out.println("子类方法");
	}
	public static void main(String[] args){
		new Polymorphic2();
		/*输出为0<br>4:子类先默认隐式调用父类构造器，由于print被子类重写，所以调用子类方法，但此时j是还没有初始化的，所以输出为0
		*/
	}
}
```