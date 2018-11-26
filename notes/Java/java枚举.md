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
