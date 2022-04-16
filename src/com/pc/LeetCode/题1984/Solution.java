package com.pc.LeetCode.题1984;

import java.util.Arrays;

/**
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * <p>
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * <p>
 * 返回可能的 最小差值 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);

        int l = 0;
        // 初始化滑动窗口
        int r = k-1;

        // 初始化最小值
        int min = Integer.MAX_VALUE;

        while (r < nums.length) {
            min = Math.min(min,Math.abs(nums[r] - nums[l]));
            l++;
            r ++;
        }
        return min;
    }
}
