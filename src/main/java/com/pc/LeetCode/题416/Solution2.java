package com.pc.LeetCode.题416;

public class Solution2 {

    public static boolean canPartition(int[] nums) {
        int C = 0;
        for (int num : nums) {
            C += num;
        }

        if (C % 2 != 0) {
            return false;
        }
        // 原问题转换为在nums数组中（物品）考虑找n个数(n个物品)，使得这些物品的重量之和等于背包容量。
        // f(n,c) =  f(n-1, c) ||  f(n-1, c-weight[i]))
        int n = nums.length;
        // 使用动态规划求解
        return canPartitionDp(n, C/2, nums);
    }

    private static boolean canPartitionDp(int n, int C, int[] nums) {
        // 为什么C+1的空间呢
        // 因为考虑了物品能不能放入到空间为0的背包中
        boolean[][] dp = new boolean[n][C+1];

        // 初始化 dp[0][j] 代表[0,0]物品的和是否等于j
        for (int j = 0; j <= C; j++) {
            if (nums[0] == j) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                // 考虑[0,i]的物品之和是否等于j
                dp[i][j] = dp[i-1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[n-1][C];
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,1}));
    }

}
