package com.pc.LeetCode.题42.接雨水;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * <p>给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>
 *
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png"/>
 *
 * <p>输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]</p>
 * <p>输出：6</p>
 * <p>解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 </p>
 *
 */
public class Solution3 {

    /**
     * <p>方式3，单调栈</p>
     * <img src="./img_1.png"/>
     * <ul>核心思路：如上图考虑这样一个柱子
     *  <li>最终能接的雨水总量等于上图s1+s2+s3+s4的面积;因此只要想办法求出每个s的面积是多少即可</li>
     *  <li>s1的面积等于离arr[3]最近的且比arr[3]大的左右柱子围起来的高度乘以1</li>
     *  <li>s2的面积等于离arr[4]最近的且比arr[4]大的左右柱子围起来的高度乘以1</li>
     *  <li>s3的面积等于离arr[5]最近的且比arr[5]大的左右柱子围起来的高度乘以1</li>
     *  <li>s4的面积等于离arr[9]最近的且比arr[9]大的左右柱子围起来的高度乘以1</li>
     *  <li>... 求arr[i]最近的且比arr[i]大的左右元素位置。这就是一个单调栈的应用场景</li>
     * </ul>
     * @param height
     * @return
     */
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();

        int ans = 0;

        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            }

            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer pop = stack.pop();
                int l = stack.isEmpty()? -1: stack.peek();
                int r = i;
                if (l != -1) {
                    ans = ans + (Math.min(height[l], height[r]) - height[pop]) * (r - l - 1);
                }
            }

            stack.push(i);

        }
        return ans;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int ans = 0;
//        ans = solution.trap(new int[]{4,2,0,3,2,5});
//        System.out.println(ans);
        ans = solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(ans);

    }
}
