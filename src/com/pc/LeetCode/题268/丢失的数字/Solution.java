package com.pc.LeetCode.题268.丢失的数字;

import java.util.Arrays;

/**
 * @author evanzhou
 */
public class Solution {

    public static int missingNumber(int[] nums) {

        int len = nums.length;

        // 如果不缺少，所有元素的异或结果 应该是xorShouldBe
        int xorShouldBe = 0;
        for (int i =0;i<=len;i++) {
            xorShouldBe ^= i;
        }

        // 实际上的异或结果
        int xorReality = 0;
        for (int num : nums) {
            xorReality ^= num;
        }

//        System.out.println(Arrays.toString(nums)+"数组中缺少的元素是:"+(xorShouldBe^xorReality));
        return xorShouldBe^xorReality;
    }

    public static void main(String[] args) {
        missingNumber(new int[]{0});
        missingNumber(new int[]{0,1,2,4});
        missingNumber(new int[]{0,1,2,4,5});
    }
}
