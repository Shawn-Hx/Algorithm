package cn.edu.nju.dp;


/**
 * LeetCode 32
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1
 * Input:   "(()"
 * Output:  2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2
 * Input:   ")()())"
 * Output:  4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * 解法：
 * dp[i] 表示以i位置字符结尾的最长有效子串的长度
 *
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // 有效括号子串一定以右括号结尾
            if (s.charAt(i) == ')') {
                // 跳过以前一个字符结尾的最长有效子串后，再前面一个字符是否是左括号（此左括号与i位置右括号对应）
                if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    // 注：还需要加上以再前面一个字符结尾的最长有效子串长度
                    if (i - dp[i - 1] - 2 >= 0) {
                        dp[i] += dp[i - dp[i - 1] - 2];
                    }
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
    }

}
