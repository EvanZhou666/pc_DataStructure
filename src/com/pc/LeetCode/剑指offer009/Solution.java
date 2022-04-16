package com.pc.LeetCode.剑指offer009;


import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.Uitls;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组
 *
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 */
public class Solution {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int length = nums.length;
        int l = 0;
        int r = 0;

        int total = 0;
        int mutil = 1;

        while (r < length) {
            mutil *= nums[r];
            // 套用模板，但此处不需要检查滑动窗口状态是否满足条件
            while (mutil >= k && l < r ) {
                mutil /= nums[l];
                l ++;
            }

            // 更新答案
            if (mutil < k ) {
                // 难点在这里，怎么更新答案 (r - l + 1) 表示新增的以r结尾的合法数组有r-l+1个
                total = total + (r - l + 1);
                Uitls.printArray(nums, l ,r);
            }
            // 滑动窗口右移1格
            r ++;
        }

        return total;
    }

    public static void main(String[] args) {
        int res =numSubarrayProductLessThanK(new int[]{2,3,42,135,53,2},10);
        Assert.assertEquals(res,4);
    }

}
