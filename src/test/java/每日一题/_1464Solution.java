package 每日一题;

import java.util.PriorityQueue;

/**
 * 1464. 数组中两元素的最大乘积
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 *
 * 请你计算并返回该式的最大值。
 * <a href="https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/">1464. 数组中两元素的最大乘积</a>
 */
public class _1464Solution {

    public int maxProduct(int[] nums) {

        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
        }

        while (heap.isEmpty()) {
            System.out.println(heap.poll());

        }
        return heap.poll() - 1 * (heap.poll() -1);
    }
}
