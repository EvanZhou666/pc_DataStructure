package com.pc.LeetCode.题239;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {

    /**
     * 优先队列之最大堆解法 官方题解，抄的答案
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] ans = new int[nums.length - k + 1];
//        int i = 0;
        // 最大堆
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return -Integer.compare(o1[0], o2[0]);
            }
        });

        // init
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }
        ans[0] = priorityQueue.peek()[0];

        // add Queue
        for (int i = k; i < nums.length; i++) {
            priorityQueue.offer(new int[]{nums[i],i});
            // 精髓在这里，太巧妙了，延迟删除堆顶元素
            while (priorityQueue.peek()[1] < i - k + 1) {
                priorityQueue.poll();
            }
            ans[i-k+1] = priorityQueue.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] ints = null;
        ints = solution2.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ints));

        ints = solution2.maxSlidingWindow(new int[]{1,-1}, 1);
        System.out.println(Arrays.toString(ints));
    }

}
