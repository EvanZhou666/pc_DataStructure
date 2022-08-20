package com.pc.LeetCode.题718;

public class Solution2 {

    /**
     * 滑动窗口解法。
     * 固定一个数组，将另外一个数字进行偏移，对比A[i]和B[i]的值，更新最大重复数组。
     * <img src="./slidewindow.png"/>
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int maxLen = 0;
        for (int offset = 0; offset < nums2.length; offset ++) {
            maxLen = Math.max(maxLen, calMaxLength(nums1, nums2, offset));
        }

        for (int offset = 0; offset < nums1.length; offset ++) {
            maxLen = Math.max(maxLen, calMaxLength(nums2, nums1, offset));
        }
        return maxLen;
    }

    private static int calMaxLength(int[] nums1, int[] nums2, int offset) {
        int maxLength = 0;
        int length = 0;
        for (int i = 0; i < nums1.length && i + offset < nums2.length; i++) {
            if (nums1[i] == nums2[i + offset]) {
                length ++;
                maxLength = Math.max(length, maxLength);
            } else {
                length = 0;
            }
            
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int ans;
        ans = findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
        System.out.println(ans);
    }
}
