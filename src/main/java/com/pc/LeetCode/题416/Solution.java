package com.pc.LeetCode.题416;

import com.pc.LeetCode.common.Assert;

public class Solution {

    public boolean canPartition(int[] nums) {

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
        Boolean[][] memo = new Boolean[n][C+1];
        return dfs(n-1, C / 2, nums, memo);
    }

    /**
     * @param i    第i个元素
     * @param c
     * @param nums
     * @return
     */
    private boolean dfs(int i, int c, int[] nums, Boolean[][] memo) {
        if (i < 0 || c < 0) {
            return false;
        }

        if (c == 0) {
            return true;
        }

        if (memo[i][c] != null) {
            return memo[i][c];
        }
        boolean res = dfs(i - 1, c, nums, memo) || dfs(i - 1, c - nums[i], nums, memo);
        memo[i][c] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean ans;
//        solution.canPartition(new int[]{1,5,11,5});
        ans = solution.canPartition(new int[]{1,1});
        Assert.assertIsTrue(ans);

    }

}
