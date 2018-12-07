```java
/**
 * 二分查找关键是让while()里面的条件结束，
 * 如果写成这样"else if(target > nums[mid] && target <= nums[right])"
 * 条件就无法结束了。
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0 , right = nums.length-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(target < nums[mid] && target >= nums[left]) {
                right = mid-1;
            }else {
                left = mid+1;
            } 
        }
        return -1;
    }
}
```