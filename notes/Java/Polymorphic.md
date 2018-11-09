```java
class A {
	public String show(D obj) {
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

class C extends B {
}

class D extends B {
}

public class Test {
	public static void main(String[] args) {
		A a1 = new A();  
	   
		A a2 = new B();  
	   
		B b = new B();  
	    C c = new C(); 
	    D d = new D();
	   
        a1.show(b);
        System.out.println(a1.show(b));
        a1.show(c);
        System.out.println(a1.show(c));
	    a1.show(d); 
        System.out.println(a1.show(d));
		a2.show(b);//由于右边是B(),而且B()重写了方法，所以用子类的
        System.out.println(a2.show(b));
        System.out.println(a2.getClass());
        a2.show(c); 
		System.out.println(a2.show(c));
		System.out.println(a2.show(d));
		System.out.println(b.show(b));
		System.out.println(b.show(c));
		System.out.println(b.show(d));
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
	
	public static void main(String[] args) {
		new Polymorphic2();//先默认隐式调用父类构造器，
	}
}
```