```java
/**
 * 1.关键是优先对排好序的部分进行二分查找
 * 2.二分查找的结局是high与mid的重合或者low与mid的重合
 * 3.关键是nums[lo] <= nums[mid],如果是num[lo] < nums[mid],则会导致
 *   像[3,1]这样的集合进行二分查找，从而错误。
 * 4.while里面的<=可以将单独的元素输出
 */
public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        int lo = 0,hi = nums.length-1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(nums[mid] == target) {
                return mid;
            } 
            if(nums[lo] <= nums[mid]) {
                if(nums[lo] <= target && target < nums[mid]) {
                    hi = mid-1;
                } else {
                    lo = mid+1;
                }
            } else {
                if(nums[mid] < target && target <= nums[hi]) {
                    lo = mid+1;
                } else {
                    hi = mid-1;
                }
            }
        }
        return -1;
    }
}
```