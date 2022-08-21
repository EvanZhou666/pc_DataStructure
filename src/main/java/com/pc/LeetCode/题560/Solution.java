package com.pc.LeetCode.题560;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">560. 和为 K 的子数组</a>
 * <p>给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。</p>
 * <p>前缀和思想</p>
 */
public class Solution {

    /**
     * 前缀和技巧
     * presum（新）-presum（旧）= target
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] + nums[i];
        }

        int count = 0;
        map.put(0, 1); // dp[0] 有可能就等于K的
        for (int i = 0; i < dp.length; i++) {
            int oldPrefixSum = dp[i] - k;
            if (map.containsKey(oldPrefixSum)) {
                count += map.get(oldPrefixSum);
            }
            if (map.containsKey(dp[i])) {
                map.put(dp[i], map.get(dp[i]) +1);
            } else {
                map.put(dp[i], 1);
            }
        }

        return count;
    }


    /**
     * 前缀和思想，空间优化，省去一个dp数组
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int sum = 0;
        int preSum;
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        // 这一步很关键，有可能直接前缀和就等于k，比如，num[0]=k；或者是nums[0]+nums[1] = k
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum = sum - k;

            if (map.containsKey(preSum)) {
                count += map.get(preSum);
            }

            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) +1);
            } else {
                map.put(sum, 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int ans = 0;
        ans = subarraySum2(new int[]{1,2,3}, 3 );
        Assert.assertEquals(ans, 2);
    }

}
