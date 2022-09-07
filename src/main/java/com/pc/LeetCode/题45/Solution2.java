package com.pc.LeetCode.题45;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

/**
 * 动态规划解法
 */
public class Solution2 {

    public static int jump(int[] nums) {
        // 初始化dp数组。定义dp[i]是跳跃到以n为结尾的位置所需要的最小步数，则：
//        dp[i] = dp[i-1] + 1   if nums[n-1] >= 1
//        dp[i] = dp[i-2] + 1   if nums[n-1] >= 2
//        dp[i] = dp[i-3] + 1   if nums[n-1] >= 3
//        .....
//        dp[i] = dp[1] + 1   if nums[1] >= n-1
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= nums[j]) {
                    if (dp[i] == -1) {
                        dp[i] = dp[j] + 1;
                    } else {
                        dp[i] = Math.min(dp[j] + 1, dp[i]);
                    }
                }
            }
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int ans;
        ans = jump(new int[]{2,3,1,1,4});
        Assert.assertEquals(ans, 2);
        ans = jump(new int[]{2,3,0,1,4});
        Assert.assertEquals(ans, 2);
    }
}
