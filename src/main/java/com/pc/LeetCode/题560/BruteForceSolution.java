package com.pc.LeetCode.题560;

import com.pc.LeetCode.common.Assert;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">560. 和为 K 的子数组</a>
 * <p>给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。</p>
 * <p>暴力解法1</p>
 */
public class BruteForceSolution {

    /**
     * 暴力解法 数组中的每个元素都可以作为子数组的头节点，检查每个元素作为头节点的时候是否满足sum=k，满足则结果+1
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans += 1;
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int ans = 0;
        ans = subarraySum(new int[]{1,1,1}, 2 );
        Assert.assertEquals(ans, 2);
    }

}
