```java
/**
 * 二分查找的简单变化，把mid值保留了。
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left<right){
            int mid =  left + (right-left)/2;
            if(isBadVersion(mid) == true){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
}
```