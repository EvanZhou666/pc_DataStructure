package com.pc.LeetCode.题42.接雨水;

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
public class Solution2 {

    /**
     * <p>方式2，动态编程</p>
     * 核心思路：
     * 1.遍历数组两次，第一次找到i左边的最大元素 记录到left_max数组里面；第2次找到i右边的最大元素 记录到right_max数组里面
     * 2.按照方式1的暴力解法，累加当前桶的位置能够承接的雨水值，更新最终的答案
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
//        left_max[i] 代表i左边的最大元素
        int[] left_max = new int[size];
        // right_max 代表i右边的最大元素
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            // 这里比较精髓，left_max[i]存储i左边的最大元素，会包含i。意思就是说如果没有i左边更大的元素了，height[i]就是最大的，那么left_max[i] = arr[i]
            // 这样并会不会影响计算i出的最大可装雨水值。 left_max[i] - height[i] = 0
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            // 精髓 i右边的最大元素，包含位置i
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        // 为什么是从1开始的？到size-1结束。
        // 因为第0根柱子找不到左柱子，所以在0的位置肯定是没有办法接到水的
        // 因为最后一根柱子不存在右柱子，所以在size-1的位置上肯定也是没有办法接到水的
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
            System.out.println("i="+i + " area=" + (Math.min(left_max[i], right_max[i]) - height[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int ans = 0;
//        ans = solution.trap(new int[]{4,2,0,3,2,5});
//        System.out.println(ans);
        ans = solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(ans);

    }
}
