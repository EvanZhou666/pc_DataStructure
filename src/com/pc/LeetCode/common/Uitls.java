package com.pc.LeetCode.common;

import java.util.Arrays;

public class Uitls {

    /**
     * 打印数组以及双指针，方便调试用
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void printArray(char[] nums, int l, int r) {
        if (r >= nums.length) {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=nil");
        } else {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=" + r);
        }
    }

}
