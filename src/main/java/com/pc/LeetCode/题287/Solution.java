package com.pc.LeetCode.题287;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 */
public class Solution {

    public int findDuplicate(int[] nums) {
        int sum = 0;
        int n = nums.length - 1;
        int shouldBe = (1 + n) * n / 2;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum - shouldBe;
    }
}
