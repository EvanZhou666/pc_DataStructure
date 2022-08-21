package com.pc.LeetCode.题974;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 同余定理：如果a除以k的余数和b除以k的余数相等，那么a-b一定能够被k整除
     * 前缀和 + 哈希表
     * [1, 2, 3, 4, 1] k =5
     * 如果preSum[0...2]的余数为1
     * 如果preSum[0...4]的余数也为1
     * 那么说明 (2,4]连续区间的元素和是可以被整除的
     * @param nums
     * @param k
     * @return
     */
    public static int subarraysDivByK(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);// 处理前缀和本身就等于k的情况

        int requiredPrefixSum;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            requiredPrefixSum = sum % k; // 余数
            // 余数出现负数的情况，要另外处理
            if (requiredPrefixSum < 0) {
                // 负数转换为正数
                requiredPrefixSum += k;
            }

            System.out.println("requiredPrefixSum:" + requiredPrefixSum + " " + map.toString());

            ans += map.getOrDefault(requiredPrefixSum, 0);

            map.put(requiredPrefixSum, map.getOrDefault(requiredPrefixSum, 0) + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int i = subarraysDivByK(new int[]{-1,2,9}, 2);
        System.out.println(i);
//        int i = subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5);
//        System.out.println(i);

        // 如果preSum[0...2]的余数为1
        // 如果preSum[0...4]的余数也为1
        // 那么说明 (2,4]连续区间的元素和是可以被整除的
//        int i = subarraysDivByK(new int[]{1, 2, 3, 4, 1}, 5);
//        System.out.println(i);
    }
}
