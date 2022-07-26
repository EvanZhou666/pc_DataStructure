package com.pc.LeetCode.剑指offer59;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class MaxQueue {

    Queue<Integer> queue;
    // 单调递增队列，队尾元素最大
    Deque<Integer> monotonousQueue;

    int[] maxValue = new int[]{-1,1};

    public MaxQueue() {
        queue = new LinkedList<>();
        monotonousQueue = new ArrayDeque<>();
    }

    public int max_value() {
        return monotonousQueue.isEmpty() ? -1: monotonousQueue.peekLast();
    }

    public void push_back(int value) {
        queue.offer(value);

        // 维护"单调递增队列"。并不是所有的元素都在"单调递增队列"里面的，当当前元素比队列里面"某些元素"要大的时候，"某些元素"会被移除掉（得看具体场景，通过这些元素都是没有价值了，对最终的结果不构成影响了）
        while (!monotonousQueue.isEmpty() && value > monotonousQueue.peekFirst()) {
            monotonousQueue.pollFirst();
        }
        monotonousQueue.offerFirst(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }

        int poll = queue.poll();

        // 如果当前出队元素是最大元素，那么要从单调队列里面删除
        if (poll == monotonousQueue.peekLast()) {
            monotonousQueue.pollLast();
        }

        return poll;
    }

}
