package com.pc.LeetCode.题1413.逐步求和得到正数的最小值;

import com.pc.LeetCode.common.Assert;

/**
 * 1413. 逐步求和得到正数的最小值
 * 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
 *
 * 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
 *
 * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
 */
public class Solution {

    /**
     * 考察贪心算法？？？
     * @param nums
     * @return
     */
    public int minStartValue(int[] nums) {
        int startValue = 1;
        int sum = startValue;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= 1) {
                startValue = startValue + (1 - sum - nums[i]);
                sum = 1;
            } else {
                sum = sum + nums[i];
            }

        }
        return startValue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int startValue = solution.minStartValue(new int[]{-3, 2, -3, 4, 2});
        Assert.assertEquals(startValue, 5);
        Assert.assertEquals(solution.minStartValue(new int[]{1, 2}), 1);
        System.out.println(startValue);
    }
}
