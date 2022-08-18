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
public class Solution {

    /**
     * 思路1 先用双指针找到一个可行解，再移动指针进行优化
     * 经提交测试能够通过
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：49 MB, 在所有 Java 提交中击败了17.17%的用户
     * 通过测试用例：20 / 20
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int windowSum = 0;
        while (r < nums.length) {
            windowSum += nums[r];

            if (windowSum >= target) {
                minLen = Math.min(minLen, r - l + 1);
            }

            while (l < r && windowSum >= target) {
                windowSum -= nums[l];
                l ++;
                if (windowSum >= target) {
                    minLen = Math.min(minLen, r - l + 1);
                }
            }
            r++;
        }

        return minLen == Integer.MAX_VALUE ? 0: minLen;

    }

    public static void main(String[] args) {
        int len;
        len = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(len);

        len = minSubArrayLen(4, new int[]{1,4,4});
        System.out.println(len);

        len = minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1});
        System.out.println(len);
    }
}
