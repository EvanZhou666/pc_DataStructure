package com.pc.LeetCode.题622.设计循环队列;

import java.util.Arrays;

/**
 * 622. 设计循环队列
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1); // 返回 true
 * circularQueue.enQueue(2); // 返回 true
 * circularQueue.enQueue(3); // 返回 true
 * circularQueue.enQueue(4); // 返回 false，队列已满
 * circularQueue.Rear(); // 返回 3
 * circularQueue.isFull(); // 返回 true
 * circularQueue.deQueue(); // 返回 true
 * circularQueue.enQueue(4); // 返回 true
 * circularQueue.Rear(); // 返回 4
 *
 */
public class MyCircularQueue {


    /**
     * 存储元素的数组 <b>大小 = k + 1</b>
     */
    private int[] elements;

    private int capacity;
    /**
     * <b>队列首元素</b>对应的数组的索引
     */
    private int head;
    /**
     * 队列尾元素对应的索引的<b>下一个索引</b>。
     */
    private int tail;

    public MyCircularQueue(int k) {
        capacity = k + 1;
        elements = new int[capacity];
        head = tail = 0;
    }

    /**
     * 向循环队列插入一个元素。如果成功插入则返回真。
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        elements[tail] = value;
        tail = (tail + 1) % capacity;
        System.out.println("tail:" + tail + " capacity:" + capacity);
        return true;
    }

    /**
     *  从循环队列中删除一个元素。如果成功删除则返回真。
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回 -1 。
     * @return
     */
    public int Front() {
        return isEmpty() ? -1 : elements[head];
    }

    public int Rear() {
        return isEmpty() ? -1: elements[(tail - 1 + capacity) % capacity]; //取tail前一个元素这里比较容易写错的，如果tail是elements[0],那么它的前一个元素实际上是elements[capacity-1],也即是数组最后一个元素。所以这里要加上capasity然后取模
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
//        System.out.println(queue.isEmpty());
//        System.out.println(queue.isFull());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        int a = queue.Rear(); // 返回 3
        boolean b = queue.isFull(); // 返回 true
        boolean c = queue.deQueue(); // 返回 true
        boolean d = queue.enQueue(4); // 返回 true
        int e = queue.Rear(); // 返回 4

        System.out.println(Arrays.toString(queue.elements));
    }


}
