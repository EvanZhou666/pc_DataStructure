package com.pc.LeetCode.题523;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 前缀和+哈希表
     * 同余定理
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);// 处理特殊情况

        int requiredPrefixSum;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            requiredPrefixSum = sum % k; // 余数
            // 余数出现负数的情况，要另外处理
            if (requiredPrefixSum < 0) {
                // 负数转换为正数
                requiredPrefixSum += k;
            }

            // System.out.println("requiredPrefixSum:" + requiredPrefixSum + " " + map.toString());

            if (map.containsKey(requiredPrefixSum)) {
                if (i - map.get(requiredPrefixSum) > 1) {
                    return true;
                }
            } else {
                map.put(requiredPrefixSum, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean ans = checkSubarraySum(new int[]{5,0,0,0}, 3);
        Assert.assertIsTrue(ans);
        System.out.println(ans);
    }
}
