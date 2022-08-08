package com.pc.LeetCode.题35;

import com.pc.LeetCode.common.Assert;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length -1 ;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }

        return target > nums[r] ? r + 1: l;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int i1 = solution.searchInsert(new int[]{1, 3, 5, 6}, 5);
        Assert.assertEquals(i1, 2);

        int i2 = solution.searchInsert(new int[]{1, 3, 5, 6}, 2);
        Assert.assertEquals(i2, 1);

        int i3 = solution.searchInsert(new int[]{1, 3, 5, 6}, 7);
        Assert.assertEquals(i3, 4);
    }
}
