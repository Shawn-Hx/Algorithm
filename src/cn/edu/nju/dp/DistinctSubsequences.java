package cn.edu.nju.dp;

import java.util.Arrays;

/**
 * LeetCode 115
 *
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * 解法：
 *
 * dp[i][j] 表示 S前j个字符的不同子序列中与T前i个字符相同的子序列个数 （i, j >= 1）
 *
 * dp[i][j] = dp[i][j - 1]                      S[j - 1] != T[i - 1]
 *            dp[i][j - 1] + dp[i - 1][j - 1]   S[j - 1] == T[i - 1]
 *
 */

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        if (s.length() < t.length())
            return 0;

        int[][] dp = new int[t.length() + 1][s.length() + 1];
        Arrays.fill(dp[0], 1);

        for (int i = 0; i < t.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                dp[i + 1][j + 1] = dp[i + 1][j];
                if (t.charAt(i) == s.charAt(j))
                    dp[i + 1][j + 1] += dp[i][j];
            }
        }

        return dp[t.length()][s.length()];
    }

    public static void main(String[] args) {
        int res = new DistinctSubsequences().numDistinct("babgbag", "bag");
        System.out.println(res);
    }

}
