package com.pc.LeetCode.题209.长度最小的子数组;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class Solution2 {

    /*ps: 该解法方向不正确，官方题解的前缀和实际上暴力解法的优化，由于暴力枚举的时间复杂度达到了O（N^2）,所以前缀和是为了来进行二分查找将时间复杂度优化到O(N*LogN)*/
    /*暴力枚举的思路是，对于数组里面的每个数，进行遍历，第一层遍历的含义是以每个数作为子数组的开头，第二层遍历是看能否找到一个子序列使sum和大于target*/
   /* *//**
     * 思路2 使用前缀和
     * 怎么感觉还是双指针的味道。。。。
     * @param target
     * @param nums
     * @return
     *//*
    public static int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int dp[] = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }

        int l = 0, r = 0;
        while (r < dp.length) {
            if (dp[r] - dp[l] >= target) {
                minLen = Math.min(minLen, r - l + 1);
            }

            while (l < r && dp[r] - dp[l] >= target) {
                l ++;
                if (dp[r] - dp[l] >= target) {
                    minLen = Math.min(minLen, r - l + 1);
                }
            }
            r++;
        }

        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }*/

}
