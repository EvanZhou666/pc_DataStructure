package com.pc.LeetCode.题641.设计循环双端队列;

/**
 * 基于数组实现，用链表实现太简单了。
 * 存在的问题：1.每次在队首插入数据，都可能导致数据拷贝，性能低下。
 */
public class MyCircularDeque {

    private int capasity;
    private int size;
    int[] node;
    int head, tail;

    public MyCircularDeque(int k) {
        capasity = k;
        // 待优化，最好是可以动态扩容
        node = new int[k];
        head = tail = 0;
    }

    /**
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false
     *
     * @param value
     * @return
     */
    public boolean insertFront(int value) {
        if (size == capasity) {
            return false;
        }

        if (isEmpty()) {
            node[head] = value;
            tail = head + 1;
            size ++;
            return true;
        }

        head = head -1;
        // right conmpact
        if (head < 0) {
            for (int i = tail - 1; i > head ; i--) {
                node[i+1] = node[i];
            }
            tail = tail + 1;
            head = head + 1;
        }

        node[head] = value;
        size ++;
        return true;
    }

    /**
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false
     *
     * @param value
     * @return
     */
    public boolean insertLast(int value) {

        if (capasity == size) {
            return false;
        }

        // left compact
        if (tail == capasity) {
            for (int i = head; i < tail; i++) {
                node[i-1] = node[i];
            }
            tail = tail -1;
            head = head - 1;
        }

        node[tail] = value;
        tail = tail + 1;
        size ++;
        return true;
    }

    /**
     * 从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false
     * @return
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        node[head] = -1;
        head = head + 1;
        size -- ;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        node[--tail] = -1;
        size --;
        return true;
    }

    /**
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
     * @return
     */
    public int getFront() {
        if (head == tail) {
            return -1;
        }
        return node[head];
    }

    /**
     * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1
     *
     * @return
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return node[tail - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capasity;
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
}
