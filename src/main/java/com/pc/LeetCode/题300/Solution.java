package com.pc.LeetCode.题300;

import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.GoodQuestion;

import java.util.*;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
@GoodQuestion(type = "动态规划系列")
public class Solution {

    /**
     * 动态规划 相当于在构造一张一维的表
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 定义dp[i]是以i为结尾的最长上升子序列
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            // 因为任何数字自己都可以构造1个长度为1的上升子序列
            dp[i] = 1;
            // 轮询一遍第i个数字前面的所有数字，如果当前数字nums[i]比前面的数字大，那么则有可能组成新的最长上升子序列。
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < dp.length; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {

        int ans;
        ans = lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        Assert.assertEquals(ans, 4);

        ans = lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
        ans = lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12});
        Assert.assertEquals(ans, 6);

    }

}
