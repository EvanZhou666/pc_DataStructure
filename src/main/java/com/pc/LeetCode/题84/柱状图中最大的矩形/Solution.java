package com.pc.LeetCode.题84.柱状图中最大的矩形;

import com.pc.LeetCode.common.Assert;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>
 * 剑指 Offer II 039. 直方图最大矩形面积
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * </p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg"/></p>
 */
public class Solution {

    /**
     * 暴力解法1 超时！！！.
     * 双层for循环，第一层for循环固定左边的柱子；第2层for循环枚举右边的柱子，计算矩形的面积。
     * @param heights
     * @return
     */
/*    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            int area = 0;
            for (int j = i; j < heights.length; j++) {
                if (heights[j] == 0) {
                    break;
                }

                minHeight = Math.min(minHeight, heights[j]);
                area = minHeight * (j - i + 1);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }*/

    /**
     * 暴力解法2，矩形的面积等于底*高，可以考虑固定高度，底座的长度往两边扩散。还是超时
     * @param heights
     * @return
     */
/*    public static int largestRectangleArea(int[] heights) {

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            // 当前高度固定为 height[i]
            int r = i + 1;
            while (r < heights.length && heights[r] >= heights[i]) {
                r ++;
            }

            int l = i - 1;
            while (l >= 0 && heights[l] >= heights[i]) {
                l --;
            }
            // 左闭右开
            l ++;
            // System.out.println("l:" + l + " r:" + r);
            maxArea = Math.max(maxArea, heights[i] * (r - l));

        }
        return maxArea;

    }*/

    /**
     * <p><img src="./单调栈解法.png"/></p>
     *
     * <p>解法3 单调栈 暴力解法2的优化，暴力解法2的扩散过程存在重复计算，假如5和6是相邻的两根柱子，在以高度为5进行的一次扩散的过程中，
     * 可能会遇到比5，6都矮的柱子。这中情况下就没有必要分为两次进行扩散了
     *
     * 固定高度；想象有一个栈存放着还不能确定在特定高度保持不变的情况下能围成的最大的柱子面积([h1,h2,h3,h4])，
     * h1<h2<h3<h4。h1、h2、h3、h4都不能结算，因为还可能有比他们更高的柱子进来。假如这个时候新来一个h5，h5<h3<h4，那么柱子会
     * 触发h4，h3的结算。</p>
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {

        int maxArea = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < heights.length; i++) {

            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            int area = 0;
            // 稍矮的柱子来了，触发结算
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int pop = stack.pop();

                if (!stack.isEmpty()) {
                    // 这里容易出错，可以想一想为什么要和新的stack.peek()比较？因为(新的stack.peek,i)这个区间上的值都是大于pop的高度值的
                    // 说的有点绕了，其实就是确定pop左边的比height[pop]小的最近的位置，记作m,那么从[m+1 位置开始都是都是height[pop]的，
                    // 这也是为什么stack.peek参与计算的原因，因为新的stack.peek就是pop左边距离最近的比height[pop]小的位置。
                    area = heights[pop] * (i - 1 - stack.peek());
                } else {
                    area = heights[pop] * i;
                }

                maxArea = Math.max(area, maxArea);
            }

            stack.push(i);

        }

        // 不会有新的柱子到来了，清算栈里面剩余的柱子能够围成的最大值
        int area = 0;
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            // 清算栈底剩余柱子能够围成的面积容易出错。能留在栈里的柱子，都是因为右边没有更矮的柱子了，所以右边能够一直扩散到最后一根柱子（含）
            // 左边能够扩散的最远距离，为它下面压着的那个元素（不含）。如果它下面没有压着元素，那能够一直扩散到第0个元素(含)
            if (!stack.isEmpty()) {
                area = heights[pop] * (heights.length - 1 - stack.peek());
            } else {
                area = heights[pop] * (heights.length);
            }
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int ans = 0;
        ans = largestRectangleArea(new int[]{5,4,1,2});
        Assert.assertEquals(ans , 8);


        ans = largestRectangleArea(new int[]{2,2,2});
        Assert.assertEquals(ans , 6);

        ans = largestRectangleArea(new int[]{2,1,2});
        Assert.assertEquals(ans , 3);

        ans = largestRectangleArea(new int[]{2,1});
        Assert.assertEquals(ans , 2);

        ans = largestRectangleArea(new int[]{2,1,5,6,2,3});
        Assert.assertEquals(ans , 10);

        ans = largestRectangleArea(new int[]{2,4});
        Assert.assertEquals(ans , 4);
    }
}
