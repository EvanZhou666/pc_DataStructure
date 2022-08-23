package com.pc.LeetCode.题198;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 */
public class Solution {

    /**
     * 动态规划
     * f(0) = max(v(0) + f(2)、 v(1)+f(3)、 v(2)+f(4)、... v(n-3)+f(n-1)、v(n-2)、v(n-1))
     * 定义f(0)为在[0,n]的区间抢劫能够获得的最大利润
     * 定义v(0)为在抢劫第0家房屋时候物品的价值
     * 比如 n=2
     * f(0)= max(v(0)、v(1))
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        int maxValue = 0;
        for (int i = dp.length - 2; i >= 0; i--) {
            maxValue = Math.max(Math.max(nums[n-2], nums[n-1]), maxValue);
            for (int j = i; j < n - 2; j++) {
                maxValue = Math.max(nums[j] + dp[j + 2], maxValue);
            }
            dp[i] = maxValue;
        }
        return dp[0];
    }
}
