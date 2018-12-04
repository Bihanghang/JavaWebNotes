```java
/*
*当n的值很大时，需要首先借助B柱，将上面n-1个盘移到C柱上，再将第n个盘移到B柱，再借助A柱，将C柱上的n-1个盘移到B柱上，完成移动。
*/
class TowerOfHanoi {
    public static void move(int n,String A,String B,String C){
        if (n==1) {
            System.out.println(A+"-->"+B);
        } else {
            move(n-1,A,C,B);
            System.out.println(A+"-->"+B);
            move(n-1,C,B,A);
        }
    }

    public static void main(String[] args) {
        move(2, "A", "B", "C");
    }
}
```