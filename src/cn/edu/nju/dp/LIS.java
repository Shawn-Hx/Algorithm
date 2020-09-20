package cn.edu.nju.dp;

import java.util.Arrays;

/**
 * LeetCode 300. Longest Increasing Subsequence
 * 最长上升子序列
 */
public class LIS {

    // O(nlogn)
    public int advanceLengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int res = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(dp, num);
            if (index >= 0)
                continue;
            dp[-index-1] = num;
            res = Math.max(res, -index);
        }
        return res;
    }

    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
