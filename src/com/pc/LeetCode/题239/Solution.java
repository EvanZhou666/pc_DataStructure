package com.pc.LeetCode.题239;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 暴力解法，先固定1个元素，然后再往此元素后面数k个位置，看是否满足nums[i] == nums[j]
     *
     * @param nums
     * @param k
     * @return
     */
//    public static boolean containsNearbyDuplicate(int[] nums, int k) {
//
//        int length = nums.length;
//        for (int i = 0; i < length; i++) {
////            j < length 防止数组下标越界
//            for (int j = i + 1; j <= i + k && j < length; j++) {
//                if (nums[i] == nums[j]) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 滑动窗口解法
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        // 滑动窗口 数组nums中的value做key，索引作为value
        Map<Integer, Integer> window = new HashMap<Integer, Integer>(k);
        // 初始化窗口
        for (int i = 0; i <= k && i< nums.length; i++) {
            if (window.containsKey(nums[i])) {
                return true;
            }
            window.put(nums[i], i);
        }

        // 初始化窗口的左右指针
        int l = 0;
        int r = k;
        //窗口向右滑动直到到右边界
        while (r < nums.length - 1) {
            window.remove(nums[l]);
            // 窗口向右滑动1格
            l++;
            if (window.containsKey(nums[++r])) {
                return true;
            }
            window.put(nums[r], r);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 1;
        Assert.assertIsTrue(containsNearbyDuplicate(nums, k));
    }

}
