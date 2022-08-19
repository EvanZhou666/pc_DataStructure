package com.pc.LeetCode.题525.连续数组;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */
public class Solution {

    /**
     * 暴力解法 O(N^2) 超时
     * 每1个数字都可以作为连续子序列的开头节点。
     * 因此可以轮询检查该位置后面的序列作为尾节点的时候是否满足题意。
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {
        int[] dp_1 = new int[nums.length];
        int[] dp_0 = new int[nums.length];
        if (nums[0] == 1) {
            dp_1[0] = 1;
        } else {
            dp_0[0] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                dp_1[i] = dp_1[i-1] + 1;
                dp_0[i] = dp_0[i-1];
            } else {
                dp_0[i] = dp_0[i-1] + 1;
                dp_1[i] = dp_1[i-1];
            }

        }

        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j = j + 2) {
                if (i == 0) {
                    if (dp_1[j] == dp_0[j]) {
                        maxLength = Math.max(maxLength, (j - i + 1));
                    }
                } else {
                    if (dp_1[j] - dp_1[i-1] == dp_0[j] - dp_0[i-1]) {
                        maxLength = Math.max(maxLength, (j - i + 1));
                    }
                }

            }

        }

        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }


    /**
     * 相比暴力解法1：少了第2层的遍历。前缀和的优化，利用规律：
     * 如果当前位置i的0,和1的数量差值和前面的某个位置x相同，代表这x和i中间的子序列0和1的数量相等，这个时候就可以更新结果。
     * @param nums
     * @return
     */
    public static int findMaxLength2(int[] nums) {
        int[] dp_1 = new int[nums.length + 1];
        int[] dp_0 = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // dp_1[i + 1] 前i个元素1的数目
                dp_1[i + 1] = dp_1[i] + 1;
                dp_0[i + 1] = dp_0[i];
            } else {
                dp_0[i + 1] = dp_0[i] + 1;
                dp_1[i + 1] = dp_1[i];
            }
        }

        int maxLength = Integer.MIN_VALUE;
        // 在遍历的过程中动态维护一个hashmap，记录0和1的数量与位置的关系
        // 如果当前位置i的0,和1的数量差值和前面的某个位置x相同，代表这x和i中间的子序列0和1的数量相等，这个时候就可以更新结果。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < dp_1.length; i++) {
            // 这里可以优化，尽量避免字符串拼接，把0看作-1，用一个dp数组就能搞定。
            Integer key = dp_1[i] - dp_0[i];
            if (map.containsKey(key)) {
                Integer x = map.get(key);
                maxLength = Math.max(maxLength, i - x);
            } else {
                map.put(key , i);
            }
        }

        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }

    public static void main(String[] args) {
        int maxLength;

        maxLength = findMaxLength2(new int[]{0,1,1,0,1,1,1,0});
        Assert.assertEquals(maxLength, 4);

        maxLength = findMaxLength2(new int[]{0,0,1,0,0,0,1,1});
        Assert.assertEquals(maxLength, 6);
        System.out.println(maxLength);

        maxLength = findMaxLength2(new int[]{0, 1, 0});
        System.out.println(maxLength);
        Assert.assertEquals(maxLength, 2);

        maxLength = findMaxLength2(new int[]{0,0,1,0,0,0,1,1});

        Assert.assertEquals(maxLength, 6);

        maxLength = findMaxLength2(new int[]{0, 1});
        System.out.println(maxLength);
        Assert.assertEquals(maxLength, 2);
    }
}
