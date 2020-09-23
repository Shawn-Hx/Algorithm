package cn.edu.nju.advance;

import java.util.Arrays;

/**
 * 求数组中逆序对个数
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        // 离散化
        Arrays.sort(tmp);
        for (int i = 0; i < n; i++)
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        // 统计逆序对
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res += bit.query(nums[i] - 1);
            bit.update(nums[i], 1);
        }
        return res;
    }

}
