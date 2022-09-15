package com.pc.LeetCode.题630;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    /**
     * 贪心算法；copy from 官方
     */
    public static int scheduleCourse(int[][] courses) {

        // todo 代码review 按照课程结束时间升序排列 为什么要进行排序？
        //  因为我们想让结束时间早的课程先学习，结束时间晚的课程后学习。
        //  如果先学结束时间晚的课程，那么结束时间早的课程不一定还能学习。反之，先学习结束时间早的再学习结束时间晚的，那么能够学习结束时间晚的课程的纪律要大。
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // todo 代码review 需要注意的是这里是大顶堆
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        // 优先队列中所有课程的总时间
        int total = 0;

        // todo 代码review 遍历第i个课程
        for (int[] course : courses) {
            // todo 代码review ti = 学习课程i所需要的时间 di = 必须在di时间线之前学习完课程
            int ti = course[0], di = course[1];
            // todo 代码review 核心来了!
            //  贪心策略：1. 假如我们前面学习的k门课程的总时间 + 学习第i门课程的时间，没有超过第i门课程的结束时间，
            //  （不用管这个k值是多少，实际上就是当前队列的大小，前面学了哪些课程也都在队列里面了）
            //  那么毫无疑问，我们又能多学习1门课程，并将i所需要的时长假如到队列中。（为什么要把时长加入到队列里面呢？因为出队的时候要用到）
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                // todo 代码review 贪心策略：2. 假如我们无法在截至时间di前，完成第i门课程的学习，也就是说我们最多能学习的课程数保持不变。
                //  但是这还不够，我们需要维持1个最优解，在考虑前i门课程的情况下，只能选择学习k门课程，使得学习k门课程的时间越小于di越好。
                //  这样能为后面的[i...length）门课程流出更多的时间。
                //  因此我们需要找1个耗时时间最长的课程，然后用课程i替换它。
                total = total - q.poll() + ti; // todo 代码review 需要的注意的是 我们之所以能只出队1次，是因为我们用的是大顶堆。
                q.offer(ti);
            }
        }

        return q.size();
    }

}
