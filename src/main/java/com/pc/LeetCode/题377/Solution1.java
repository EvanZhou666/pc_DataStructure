package com.pc.LeetCode.题377;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 */
public class Solution1 {

    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        Arrays.sort(nums);
        // 定义dp[i]凑出target=i的组合个数, 则dp[i] = sum( dp[i-nums[0] 、dp[i-nums[1] .... + dp[i-nums[n]]]) 其中n<nums.length
        // 第一层循环，相当于在遍历背包容量
        for (int i = 1; i < dp.length; i++) {
            // 第2层循环，遍历物品。
            // 本题更像是爬楼梯问题，不太像背包问题
            for (int j = 0; j < nums.length && i - nums[j] >= 0; j++) {
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int ans;
        ans = combinationSum4(new int[]{1,2,3},2);
        Assert.assertEquals(ans, 2);
    }

}
