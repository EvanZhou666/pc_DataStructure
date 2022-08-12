package com.pc.LeetCode.题42.接雨水;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

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
public class Solution {

    /**
     * <p>方式1，暴力解法</p>
     * <p>执行结果：通过</p>
     * <p>执行用时：1685 ms, 在所有 Java 提交中击败了5.00%的用户</p>
     * <p>内存消耗：42.1 MB, 在所有 Java 提交中击败了44.52%的用户</p>
     * <p>通过测试用例：322 / 322</p>
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int sumArea = 0;
        // 将数组里面的点，看作是一个个“桶”，”桶”能不能装水取决于在桶的左右有没有比当前“桶”还要高的柱子
        for (int i = 0; i < height.length; i++) {

            int leftTallest = serchLeft(height, i);
            int rightTallest = serchRight(height, i);

            if (leftTallest != -1 && rightTallest != -1) {
                int area = area(leftTallest, rightTallest, i, height);
                sumArea += area;

            }
        }
        return sumArea;

    }

    /**
     * 寻找j左边最高的柱子
     * @param height
     * @param i
     * @return
     */
    private int serchLeft(int[] height, int i) {
        int maxK = -1;
        for (int k = i-1; k >=0 ; k --) {
            if (height[k] > height[i]) {
                if (maxK != -1) {
                    maxK = height[maxK] > height[k] ? maxK : k;
                } else {
                    maxK = k;
                }
            }
        }
        return maxK;
    }

    /**
     * 寻找i右边最高的柱子
     * @param height
     * @param i
     * @return
     */
    private int serchRight(int[] height, int i) {
        int maxK = -1;
        for (int k = i + 1; k < height.length; k++) {
            if (height[k] > height[i]) {
                if (maxK != -1) {
                    maxK = height[maxK] > height[k] ? maxK : k;
                } else {
                    maxK = k;
                }
            }
        }
        return maxK;
    }

    /**
     * 计算以i为左柱子，j为右柱子，桶cur能够装多大的雨水
     * @param i
     * @param j
     * @param cur
     * @param height
     * @return
     */
    private int area(int i, int j, int cur, int[] height) {
        int min = Math.min(height[i], height[j]);
        return min - height[cur];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;
        ans = solution.trap(new int[]{4,2,0,3,2,5});
        System.out.println(ans);
        ans = solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(ans);
//        System.out.println(solution.area(1,3,new int[]{4,2,0,3,2,5}));
        
    }
}
