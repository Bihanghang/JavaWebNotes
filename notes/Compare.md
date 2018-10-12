```java
//8 6 4 2 1 3 5 7 9	
/**
 *       //return -1; //-1表示放在红黑树的左边,即逆序输出
        //return 1;  //1表示放在红黑树的右边，即顺序输出
        //return o;  //表示元素相同，仅存放第一个元素
 */
Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1%2 == 0 && o2%2 != 0) {
            return -1;
        } 
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
        if(01%2 != 0 && o2%2 == 0) {
            return 1;
        }
        throw new IllegalArgumentException("Error");
    }
});
```