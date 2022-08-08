package com.pc.堆;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @Description
 * @Author EvanZhou
 * @Date 2019/4/1 10:35
 **/
public class TestProrityQueue {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        Integer[] rands = BinaryHeap.testHeapfy(10);
        BinaryHeap<Integer> heap = new BinaryHeap<>(rands);

        BinaryHeap<Integer> heap2 = new BinaryHeap<>();
        for (Integer rand : rands) {
            heap2.add(rand);
            priorityQueue.add(rand);
        }
        System.out.println("堆化：");
        heap.levelOrder();
        System.out.println("add元素到自定义二叉堆：");
        heap.levelOrder();
        System.out.println("遍历java类库的最小堆:");
        Iterator iterator = priorityQueue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}
