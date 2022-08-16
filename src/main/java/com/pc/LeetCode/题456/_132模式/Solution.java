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
public class Solution {

    /**
     * 超时了！！！
     * 1. 先用单调栈找到nums[i]的左右临近最小值
     * 2. 遍历nums[i]，如果nums[i]存在邻近最小值，代表可能存在132模式
     * 3.  nums[i] 往左遍历，找到比nums[i]小的最小值 记作： leftLessMin
     * 4.  nums[i] 往右遍历，找到比nums[i]小的最大值 记作： rightLessMax
     * 5. 比较leftLessMin和rightLessMax的大小 if leftLessMin < leftLessMax ==> return true
     * @param nums
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();

        int[][] leftRightMinValues = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {

            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                Integer pop = stack.pop();
                leftRightMinValues[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                leftRightMinValues[pop][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            leftRightMinValues[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            leftRightMinValues[pop][1] = -1;
        }


        for (int i = 0; i < leftRightMinValues.length; i++) {
            if (leftRightMinValues[i][0] != -1 && leftRightMinValues[i][1] != -1) {
                // 找左边的比nums[i]小的最小值
                int left = leftRightMinValues[i][0];
                int leftLessMin = Integer.MAX_VALUE;
                while (left >= 0) {
                    leftLessMin = Math.min(leftLessMin, nums[left]);
                    left = leftRightMinValues[left][0];
                }

                // 找右边的比nums[i]小的最大值
                int right = leftRightMinValues[i][1];
                int rightLessMax = Integer.MIN_VALUE;
                if (right != -1) {
                    for (int j = right; j < nums.length; j++) {
                        if (nums[j] < nums[i]) {
                            rightLessMax = Math.max(rightLessMax, nums[j]);
                        }
                    }
                }

                if (leftLessMin < rightLessMax) {
                    return true;
                }
            }

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
