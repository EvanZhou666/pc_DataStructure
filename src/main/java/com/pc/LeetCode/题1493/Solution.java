package com.pc.LeetCode.题1493;

import com.pc.LeetCode.common.Assert;

/**
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
 */
public class Solution {

    public static int longestSubarray(int[] nums) {

        int maxLen = 0;
        int l = 0, r = 0;
        int maxK = 1;
        while (r < nums.length) {
            if (nums[r] == 1) {
                r++;
            } else {
                if (maxK > 0) {
                    maxK -= 1;
                    r++;
                } else {
                    maxLen = Math.max(r - l - 1, maxLen);
                    if (nums[l] == 0) {
                        maxK += 1;
                    }
                    l++;
                }
            }

        }

        int len = 0;
        if (maxK == 0) {
            len = r - l - 1;
        } else {
            len = r - l;
        }
        len = Math.max(len, maxLen);
        return len == nums.length ? len - 1 : len;

    }

    public static void main(String[] args) {
        int ans;
//        ans = longestSubarray(new int[]{1,1,0,1});
//        Assert.assertEquals(ans, 3);

        ans = longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1});
        Assert.assertEquals(ans, 5);
    }

}

