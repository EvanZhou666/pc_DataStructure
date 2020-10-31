package com.pc.LeetCode.题136.不重复的元素;

/**
 * 位运算
 * @author
 */
public class Solution {

    public static int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i =1;i<nums.length;i++) {
            result = result ^ nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1,2,2,4,4}));
    }
}
