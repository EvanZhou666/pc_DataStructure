package com.pc.LeetCode.题709;


import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * 
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest类：
 *
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargest {

    PriorityQueue<Integer> priorityQueue;
    int maxSize;

    public KthLargest(int k, int[] nums) {

        priorityQueue = new PriorityQueue<>(k);
        maxSize = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < maxSize) {
            priorityQueue.offer(val);
        } else {
//            Integer poll = priorityQueue.poll();
//            根本不用关心堆顶元素和当前入队元素的大小,只需要把当前元素入队之后（当前堆大小=k+1），再出队栈顶元素就行，堆已经帮我们维护了大小关系，
            // 留在堆顶的就是第k大元素
//            priorityQueue.offer(Math.max(poll, val));
            priorityQueue.offer(val);
            priorityQueue.poll();
        }

        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4,5,8,2});
        int add = kthLargest.add(3);
        System.out.println(add);
    }
}
