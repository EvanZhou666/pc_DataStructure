package com.pc.LeetCode.题416;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 */
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
//        官方dp[0][0]初始化为true就很难受，我偏要初始化为false
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
