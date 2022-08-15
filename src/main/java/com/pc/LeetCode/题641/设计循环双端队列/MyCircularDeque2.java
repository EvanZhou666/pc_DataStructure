package com.pc.LeetCode.题641.设计循环双端队列;

/**
 * 基于循环数组实现，这样就省去了入队时候移动元素导致的性能低下问题
 */
public class MyCircularDeque2 {

    private int[] node;
    private int head = 0, tail = 0;
    private int capacity;

    public MyCircularDeque2(int k) {
        node = new int[k + 1];
        capacity = k + 1;
    }

    /**
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
     * @param value
     * @return
     */
    public boolean insertFront(int value) {

        // 队满了，不能插入任何元素了
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            node[head] = value;
            // 容易错的地方，由于数组是循环数组，所以tail不能只是tail+1。
            // 设想容量为4的数组，如果tail和head都等于3，这个时候首尾指针相遇，队列为空，但是下一个tail指向的位置是0，所以不能单纯的+1必须取模。
            tail = (tail + 1) % capacity;
            return true;
        }
        // 容易错的地方，head不能只是head-1.
        // 设想当前head位置为0，那当前要插入的元素如果单纯只是head = head -1 = -1,那就数组下标越界了。
        // 由于数组是循环利用的，当head = 0 的时候，插入的元素实际上要被放到数组末尾的最后一个元素上，所以head 需要 + capacity - 1 然后取模
        head = (head - 1 + capacity) % capacity;
        node[head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        node[tail] = value;

        // 容易出错的点
        // 可以记住一个规律，循环数组下标要加1的时候，先将下标值 + 容量值得到一个值，再将这个值对容量值取模。
        // 同理，循环数组下表要减1的时候，先将下表值-1 + 容量值得到一个值，再将这个值对容量值取模。
        tail = (tail + 1) % capacity;

        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        // 容易错的点
        head = (head + 1) % capacity;

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        // 下表减1，容易错的点，忘记假设capacity
        tail = (tail - 1 + capacity) % capacity;

        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : node[head];

    }

    public int getRear() {
        return isEmpty() ? -1 : node[(tail - 1 + capacity) % capacity]; // 下标值容易错的点
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

}
