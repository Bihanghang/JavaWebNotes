```java
/*
	类加载顺序：
			1.static 变量
 			2.static 代码块
			3.成员变量
			4.匿名块
			5.构造器
			ps:先加载父类,再加载子类;
	先是父类的static 变量+static 代码块执行完，执行子类的static 变量+static 代码块，此时再去执行父类的成员变量+匿名块+构造器，注意，优先级是成员变量>匿名块>构造器。然后再执行子类的成员变量+匿名块+构造器。
 */
public class ExaminationDemo {
	public static void main(String[] args) {
		System.out.println("1运行 ExaminationDemo 中的 main 函数， 创建 D 类实例");
		new D();
	}
}

class E {
	E() {
		System.out.println("8执行 E 的构造函数");
	}

	public void funcOfE() {
		System.out.println("12执行 E 的函数");
	}
}

class F {
	F() {
		System.out.println("2执行 F 的构造函数");
	}

	public void funcOfF() {
		System.out.println("4执行 F 的函数");
	}
}

class B {
	E e = new E();
	static F f = new F();
	public String sb = getSb();

	static {
		System.out.println("3执行 B 类的 static 块(B 包含 E 类的成员 变量,包含静态 F 类成员变量)");
		f.funcOfF();
	}

	{
		System.out.println("10执行 B 实例的普通初始化块");
	}

	B() {
		System.out.println("11执行 B 类的构造函数(B 包含 E 类的成员变 量,包含静态 F 类成员变量)");
	}

	public String getSb() {
		System.out.println("9初始化 B 的实例成员变量 sb");
		return "sb";
	}
}

class C extends B {
	static {
		System.out.println("5执行 C 的 static 块(C 继承 B)");
	}

	{
		System.out.println("13执行 C 的普通初始化块");
	}

	C() {
		System.out.println("14执行 C 的构造函数(C 继承 B)");
	}
}

class D extends C {
	public String sd1 = getSd1();
	public static String sd = getSd();

	static {
		System.out.println("7执行 D 的 static 块(D 继承 C)");
	}

	{
		System.out.println("16执行 D 实例的普通初始化块");
	}

	D() {
		System.out.println("17执行 D 的构造函数(D 继承 C);父类 B 的实 例成员变量 sb 的值为：" + sb + ";本类 D 的 static 成员变量 sd 的值为：" + sd
				+ "; 本类 D 的实例成员变量 sd1 的值是：" + sd1);
	}

	static public String getSd() {
		System.out.println("6初始化 D 的 static 成员变量 sd");
		return "sd";
	}

	public String getSd1() {
		System.out.println("15初始化 D 的实例成员变量 sd1");
		return "sd1";
	}
}
```

