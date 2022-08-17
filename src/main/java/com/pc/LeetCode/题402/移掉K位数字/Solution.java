package com.pc.LeetCode.题402.移掉K位数字;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 402. 移掉 K 位数字
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，
 * 使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 */
public class Solution {

    /**
     * 贪心算法 + 单调栈
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        char[] nums = num.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(Integer.valueOf(nums[i]+""));
                continue;
            }

            while (!stack.isEmpty() && Integer.valueOf(nums[i]+"") < stack.peek() && count < k) {
                stack.pop();
                count ++;
            }
            stack.push(Integer.valueOf(nums[i]+""));
        }

        while (count < k) {
            stack.pop();
            count ++;
        }

        StringBuilder builder = new StringBuilder();

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            builder.insert(0, pop);
        }

        while (builder.indexOf("0") == 0) {
            builder = builder.replace(0,1,"");
        }

        return "".equals(builder.toString()) ? "0" : builder.toString();
    }

    public static void main(String[] args) {
        String ans = removeKdigits("10200", 1);
        System.out.println(ans);
    }


}
