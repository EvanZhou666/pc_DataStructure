package com.pc.LeetCode.题137.只出现一次的数字;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 0 0 0 0
 * 0 0 0 1
 * 0 0 1 0
 * 0 0 1 1
 * 0 1 0 0
 * 0 1 0 1
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * @author evanzhou
 */
public class Solution {

    public static int singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor = xor ^ num;
            System.out.println(xor);
        }
        return 0;
    }

    public static void main(String[] args) {
        singleNumber(new int[]{1,1,1,2,3,3,3});
    }

}
