package com.pc.LeetCode.题191.位1的个数;

/**
 * @author evan
 */
public class Solution {

    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            System.out.println(n);

            if (( n & 0x00000001) == 1) {
                count++;
            }

            n = (n >>> 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int w = hammingWeight(11);
        System.out.println("w："+w);
    }

}
