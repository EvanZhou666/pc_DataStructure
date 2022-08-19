package com.pc.LeetCode.题1004;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 */
public class Solution {

    /**
     * 滑动窗口系列，最大子序列问题。最大子序列一般就是窗口的最大值。
     * 常用套路，右指针不断循环扩大，保证窗口内的状态满足题意的，如果不满足题意，左指针被动循环右移动，直到l=r
     * 参考题解 https://leetcode.cn/problems/max-consecutive-ones-iii/solution/fen-xiang-hua-dong-chuang-kou-mo-ban-mia-f76z/
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        int count_0 = 0;
        int ans = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                count_0++;
            }

            while (l <= r && count_0 > k) {
                if (nums[l] == 0) {
                    count_0--;
                }
                l++;
            }

            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
