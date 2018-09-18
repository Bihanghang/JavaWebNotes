package Two;

import java.util.Stack;

class LongestValidParentheses {
    /*
    1.动态规划：
    用数组来保存状态，在后面调用这个状态,推进到结束。
     本题就是利用数组将当前位置最长有效子字符串的长度保存下来。
    'dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2'中的'dp[i-dp[i-1]-2]'就是之前所保存的状态。
         dp[i-1]表示的是之前所包含的最大值
    */
    /* public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if( s.charAt(i) == ')' ) {
                if (s.charAt(i-1) == '(') {
                    dp[i] = (i-2 >= 0 ? dp[i-2]:0) +2;
                } else if (i-dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
                    dp[i] = dp[i-1] + 2 + ((i-dp[i-1]-2) >=0 ? dp[i-dp[i-1]-2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    } */
    /* 2.Stack 
       如果栈空则填充，
       否则进行运算。 */
    /* public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i-stack.peek());
                }
            }
        }  
        return max;         
    }
 */
/* 3.no extra space */
public int longestValidParentheses(String s) {
    int left = 0 , right = 0 , max = 0;
    for (int i = 0; i < s.length(); i++) {
        if(s.charAt(i) == '(') {
          left++;  
        } else {
            right++;
        }
        if(right > left) {
            left = right = 0; 
        }
        if(right == left) {
            max = Math.max(max, left*2);
        }
    }  
    left = right = 0;
    for (int i = s.length()-1; i >= 0; i--) {
       if(s.charAt(i) == ')') {
           right++;
       } else {
           left++;
       }
       if(left > right) {
           left = right = 0;
       }
       if(left == right) {
           max = Math.max(max,right*2);
       }
    }     
}
}