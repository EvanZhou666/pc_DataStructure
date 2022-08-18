package com.pc.LeetCode.题238.除自身以外数组的乘积;

/**
 * <p>
 *     <a href="https://leetcode.cn/problems/product-of-array-except-self/">238. 除自身以外数组的乘积</a>
 * </p>
 * <p>给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。</p>
 *
 * <p>题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。</p>
 *
 * <p><b>请不要使用除法</b>，且在 O(n) 时间复杂度内完成此题。</p>
 *
 *
 */
public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];

        // 由于不包含i，取值的时候方便。
        int[] dp_left = new int[nums.length + 1];

        int[] dp_right = new int[nums.length + 1];
        dp_left[0] = 1;
        dp_right[0] = 1;

        // dp_left[i]等于正数前i个数之和
        // dp_right[i]等于倒数前i个数之和
        for (int i = 0; i < nums.length; i++) {
            dp_left[i + 1] = dp_left[i] * nums[i];
            dp_right[i + 1] = dp_right[i] * nums[nums.length - i - 1];
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] = dp_left[i] * dp_right[nums.length - i -1];
        }
        return ans;

    }

    public int[] productExceptSelf2(int[] nums) {
        int[] ans = new int[nums.length];

        // 由于不包含i，取值的时候方便。
        int[] dp_left = new int[nums.length + 1];

        int[] dp_right = new int[nums.length + 1];
        dp_left[0] = 1;
        dp_right[0] = 1;

        // dp_left[i]等于正数前i个数之和
        // dp_right[i]等于倒数前i个数之和
        for (int i = 0; i < nums.length; i++) {
            dp_left[i + 1] = dp_left[i] * nums[i];
            dp_right[i + 1] = dp_right[i] * nums[nums.length - i - 1];
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] = dp_left[i] * dp_right[nums.length - i -1];
        }
        return ans;

    }

}
