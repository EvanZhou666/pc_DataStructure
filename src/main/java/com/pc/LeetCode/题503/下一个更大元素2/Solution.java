package com.pc.LeetCode.题503.下一个更大元素2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>503. 下一个更大元素 II</p>
 * <p>给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。</p>
 *
 * <p>数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。</p>
 * <p></p>
 *
 * <p>示例 1:</p>
 *
 * <p>输入: nums = [1,2,1]</p>
 * <p>输出: [2,-1,2]</p>
 * <p>解释: 第一个 1 的下一个更大的数是 2；</p>
 * <p>数字 2 找不到下一个更大的数； </p>
 * <p>第二个 1 的下一个最大的数需要循环搜索，结果也是 2。</p>
 *
 * <p></p>
 * <p>示例 2:</p>
 * <p>输入: nums = [1,2,3,4,3]</p>
 * <p>输出: [2,3,4,-1,4]</p>
 *
 */
public class Solution {

    /**
     * 题目要求求循环数组x后面的最大值。
     * 思路1：单调栈的思路，将原num数组 拼接上num[0]~num[len-2]形成新的数组nums2
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {

        int[] ans = new int[nums.length];

        int[] nums2 = new int[2 * nums.length - 1];
        System.arraycopy(nums, 0, nums2, 0, nums.length);
        for (int i = 0 , j = nums.length; i <= nums.length -2 ; i++,j++) {
            nums2[j] = nums[i];
        }

        int[] leftRightBiggerElementValue = leftRightBiggerElementValue(nums2);
        for (int i = 0; i < ans.length; i++) {
            ans[i] = leftRightBiggerElementValue[i];
        }

        return ans;
    }

    private static int[] leftRightBiggerElementValue(int[] nums) {
        int[] lrMin = new int[nums.length];
        // 维护一个单调栈，栈底到栈顶，元素依次减小
        // 由于nums存在重复元素，所以需要LinkedList存放重复的元素
        Deque<LinkedList<Integer>> stack = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                LinkedList<Integer> item = new LinkedList<>();
                item.add(i);
                stack.push(item);
                continue;
            }

            // num[i]比栈顶元素还要大，说明栈顶元素的最近的且比栈顶大的元素来了，即num[i]
            while (!stack.isEmpty() && nums[i] > nums[stack.peek().get(0)]) {
                LinkedList<Integer> pop = stack.pop();
                for (int j = 0; j < pop.size(); j++) {
                    lrMin[pop.get(j)] = nums[i];
                }
            }

            if (!stack.isEmpty() && nums[stack.peek().getLast()] == nums[i]) {
                stack.peek().addLast(i);
            } else {
                LinkedList<Integer> item = new LinkedList<>();
                item.add(i);
                stack.push(item);
            }
        }

        // 如果最后的栈非空，还要处理下剩余栈
        // 最后留在栈里的都是在右边找不到离自己最近的比自己大的元素
        while (!stack.isEmpty()) {
            LinkedList<Integer> pop = stack.pop();
            // 右边找不到比自己小的
            for (int j = 0; j < pop.size(); j++) {
              // 右边不存在比自己大的元素了,置为Integer.MIN_VALUE
                lrMin[pop.get(j)] = -1;
            }
        }

        return lrMin;
    }


    public static void main(String[] args) {
        int[] ans = nextGreaterElements(new int[]{1,2,3,4,3}); // [2,3,4,-1,4]
//        int[] ans = nextGreaterElements(new int[]{5,4,3,2,1});
        System.out.println(Arrays.toString(ans));
    }
}
