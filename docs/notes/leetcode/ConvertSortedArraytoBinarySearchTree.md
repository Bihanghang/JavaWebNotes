```java
//相当精妙，和汉诺塔有点类似
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0){
            return null;
        }
        TreeNode t = dd(nums, 0, nums.length-1);
        return t;
    }
    public TreeNode dd(int[] nums,int first,int end){
        if(first > end)
            return null;
        int mid = (first+end)/2;
        TreeNode t = new TreeNode(nums[mid]);
        t.left = dd(nums, first, mid-1);
        t.right = dd(nums, mid+1, end);
        return t;
    }
}
```