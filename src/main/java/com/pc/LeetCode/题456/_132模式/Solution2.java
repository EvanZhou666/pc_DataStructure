package com.pc.LeetCode.题456._132模式;

import com.pc.LeetCode.common.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 456. 132 模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *
 */
public class Solution2 {

    /**
     * 枚举k
     * 完全可以做到 O(n)，维护前缀最小值+单调栈即可。
     * 先从单调栈中找到左侧离 i 最近的比 nums[i] 大的元素，再查询这个值左侧的最小值，只要这个最小值小于 nums[i] 即可。
     * @param nums
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        int[] dp = new int[nums.length];
        list.addLast(Integer.MAX_VALUE);
        for (int i = 0; i < len; i++) {
            dp[i] = i == 0 ? nums[0] : Math.min(dp[i - 1], nums[i]);

            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            // num[i]是被看作ijk模式中的k被枚举的。ijk模式解释： i<j<k，但num[j] > num[k];且num[k] > num[i]
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                // 一直出栈比nums[i]还要小的元素
                int pop = stack.pop();
            }
            // 此时留在栈顶的就是比num[i]要大的数，此时将num[i]看作是ijk模式中的k，栈顶元素就是ijk模式中的j，此时找j左边的最小值，如果最小值比k还要小则存在ijk模式。
            if (!stack.isEmpty() && dp[stack.peek()] < nums[i]) {
                return true;
            }

            stack.push(i);

        }
        return false;
    }

    private static void printlr(int[] nums, int[][] leftRightMinValues) {
        for (int i = 0; i < leftRightMinValues.length; i++) {
            if (leftRightMinValues[i][0] == -1 ) {
                System.out.print("左不存在");
            } else {
                System.out.print(nums[leftRightMinValues[i][0]]);
            }

            if (leftRightMinValues[i][1] == -1 ) {
                System.out.print("右不存在");
            } else {
                System.out.print(" " + nums[leftRightMinValues[i][1]]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        boolean exists = false;
        exists = find132pattern(new int[]{1,0,1,-4,3});
        Assert.assertIsFalse(exists);

        exists = find132pattern(new int[]{1,3,4,2});
        Assert.assertIsTrue(exists);

        exists = find132pattern(new int[]{3,5,0,3,4});
        Assert.assertIsTrue(exists);

        exists = find132pattern(new int[]{-2,1,-2});
        Assert.assertIsFalse(exists);
    }

}
