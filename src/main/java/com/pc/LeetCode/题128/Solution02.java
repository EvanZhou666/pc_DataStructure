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
// 哈希表解法2 -优化
public class Solution02 {

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

            while (set.contains(currentPlusOne) && !set.contains(nums[i] - 1)) {
                currentPlusOne += 1;
                currentLength += 1;
            }

            maxLengeth = Math.max(currentLength, maxLengeth);

        }
        return maxLengeth;
    }

    public static void main(String[] args) {
        Solution02 solution01 = new Solution02();
        int ans = solution01.longestConsecutive(new int[]{100,4,200,1,3,2});
        Assert.assertEquals(ans, 4);

    }
}
