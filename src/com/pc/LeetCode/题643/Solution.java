package com.pc.LeetCode.题643;

import com.pc.LeetCode.common.Assert;

/**
 * 643. 子数组最大平均数 I
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public static double findMaxAverage(int[] nums, int k) {

        int length = nums.length;
        int l = 0;
        int r = 0;

        int sum = 0;
        // 初始化滑动窗口
        for ( r = 0; r < k; r++) {
            sum += nums[r];

        }
        // 最大平均值
        double maxAvg = (double) sum / k;

        while (r < length) {

            // 更新滑动窗口的状态
            sum = sum - nums[l] + nums[r];
            maxAvg = Math.max((double) sum / k , maxAvg);

            // 套用模板，但此处不需要检查滑动窗口状态是否满足条件

            // 滑动窗口右移1格
            l ++;
            r ++;
        }

        return maxAvg;

    }

    public static void main(String[] args) {
        double maxAverage = findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);
        Assert.assertEquals(maxAverage, 12.75000);

    }

}
