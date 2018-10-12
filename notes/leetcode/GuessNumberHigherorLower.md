```java
/**
 * 简单的二分，么啥好说的。
 */
public class GuessNumberHigherorLower {
    public int guessNumber(int n) {
        int low = 1, high = n;
        while(low<=high) {
            int mid = low +(high-low)/2;
            if(guess(mid) == 0){
                return mid;
            }else if(guess(mid) > 0){
                low = mid+1;
            }else if(guess(mid) < 0) {
                high = mid-1;
            }
        }
        return -1;
    }
}
```