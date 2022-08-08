package com.pc.LeetCode.题128;

import com.pc.LeetCode.common.Assert;

import java.util.HashSet;
import java.util.Set;


/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
// 哈希表解法1
public class Solution01 {

    /**
     * 最容易想到的解法，但是提交会超时。
     * 哈希表解法。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLengeth = 0;
        for (int i = 0; i < nums.length; i++) {

            int currentLength = 0;
            // currentPlusOne 将num[i] 不断递增，看set中是否存在
            int currentPlusOne = nums[i];

            // 超时的瓶颈在这里 会存在重复计算。假如存在1,2,3,4的序列。
            // 第1次遍历计算以“1”开头的最大序列。
            // 第2次遍历计算以“2”开头的最大序列。存在重复查找。又会查找2，3，4是否在set集合中。
            // 此处需要优化，也就说当第2次遍历的时候，跳过查找2，3，4是否在set集合中。怎么跳过呢？
            // 假如以“2”开头的递增序列存在且满足题意是最长的，那么就不会存在以“2-1=1”为开头的最长递增序列，
            // 因此只要判断“前驱节点 2-1=1”是否在set中，如果存在那么最终最长递增序列肯定不是以2为开头的，因此本次查找可以跳过。
            while (set.contains(currentPlusOne)) {
                currentPlusOne += 1;
                currentLength += 1;
            }

            maxLengeth = Math.max(currentLength, maxLengeth);

        }
        return maxLengeth;
    }

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        int ans = solution01.longestConsecutive(new int[]{100,4,200,1,3,2});
        Assert.assertEquals(ans, 4);

    }
}
