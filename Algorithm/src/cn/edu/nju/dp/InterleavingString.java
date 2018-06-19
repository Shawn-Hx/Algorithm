package cn.edu.nju.dp;

/**
 * LeetCode 97
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 *
 * 解法：
 * dp[i][j] 表示 S1的前i个字符 和 S2的前j个字符 能否交错排列成 S3的前i + j个字符
 *
 * 当 i, j >= 1 时
 * dp[i][j] = dp[i - 1][j]  , s1[i - 1] == s3[i + j - 1]
 *          = dp[i][j - 1]  , s2[j - 1] == s3[i + j - 1]
 *          = false         , Others
 *
 */

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length() && s1.charAt(i - 1) == s3.charAt(i - 1); i++)
            dp[i][0] = true;

        for (int i = 1; i <= s2.length() && s2.charAt(i - 1) == s3.charAt(i - 1); i++)
            dp[0][i] = true;

        for (int i = 1; i <= s1.length(); i++)
            for (int j = 1; j <= s2.length(); j++)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                            || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new InterleavingString().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
