```java
class FindPeakElement {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>nums[i+1]){
                return i;
            }
        }
        return nums.length-1;
    }
    /**
     * 只是判断条件变成了nums[i]与nums[i+1]的二分查找,竟然没想到，惆怅。
     */
    public int findPeakElement2(int[] nums) {
        int l = 0 , r = nums.length-1;
        while(l < r){
            int mid = l + (r-l)/2;
            if(nums[mid] < nums[mid+1]){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
```