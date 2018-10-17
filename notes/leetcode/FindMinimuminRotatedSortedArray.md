```java
/**
 * 这道题相当复杂啊，首先基本思想是将两边不需要的部分截掉，但是要把中间两个数先作比较。
 * 这样最后保留下来的两个数如果是[2,3]这种正排序，则会发生数组越界的情况，所以要在前面
 * 加个判断，从而让这种情况提前剔除，而在while中像[3,2]这种逆序情况还得放在[2,3]之前判断，
 * 不然又得数组越界。
 */
class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int l=0 , r=nums.length-1;
            if(nums.length == 1){
                return nums[r];
            }
            if(nums[r]>nums[l]){
                return nums[l];
            }
            while(true){
               int mid = l + (r-l)/2;
                if(nums[mid]>nums[mid+1])
                return nums[mid+1];
               if(nums[mid]<nums[mid-1])
                return nums[mid];
               
               if(nums[l]<nums[mid]){
                  l = mid+1;
               } else {
                   r = mid-1;
               }
            }
      }
}
```