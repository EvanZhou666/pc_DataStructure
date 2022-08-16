package com.pc.LeetCode.剑指offer38;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public static int[] dailyTemperatures(int[] temperatures) {

        int[] ans = new int[temperatures.length];
        // 最小栈
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < temperatures.length; i++) {

            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int pop = stack.pop();
                ans[pop] = i - pop;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            ans[pop] = 0;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] temperatures = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(temperatures));
    }
}
