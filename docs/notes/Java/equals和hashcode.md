## 重写equals和hashcode
```java
public class Hello {

    private String name;

    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != getClass()) return false;
        Hello hello = (Hello)o;
        return Objects.equals(hello.name,name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}