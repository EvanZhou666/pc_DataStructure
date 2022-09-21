package com.pc.LeetCode.题155;

import com.alibaba.fastjson2.util.Differ;
import com.pc.LeetCode.common.GoodQuestion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * <p>
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@GoodQuestion(type = "设计系列")
//@GoodQuestion(type = "栈系列")
// 头条高频
public class MinStack {

 /*  解法一：栈+优先队列实现
    PriorityQueue<Integer> priorityQueue;
    Deque<Integer> stack;

    public MinStack() {
        priorityQueue = new PriorityQueue();
        stack = new ArrayDeque<>();
    }

    public void push(int val) {
        priorityQueue.offer(val);
        stack.push(val);
    }

    public void pop() {
        priorityQueue.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return priorityQueue.peek();
    }*/

    /*解法2；双栈实现，数据栈+辅助栈*/
/*
    Deque<Integer> data;
    Deque<Integer> helper;
    public MinStack() {
        data = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int val) {
        data.push(val);
        if (helper.isEmpty()) {
            helper.push(val);
        } else if (val <= helper.peek()) {
            helper.push(val);
        } else if (val > helper.peek()) {
            helper.push(helper.peek());
        }
    }

    public void pop() {
        data.pop();
        helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return helper.peek();
    }*/

    /*不适用额外数据结构 + 使用常数空间，面试问道过*/
    /*解法3，栈里面存存放和上一个最小值的差值*/
    Deque<Long> data;
    long min = Long.MAX_VALUE;
    public MinStack() {
        data = new ArrayDeque<>();
    }

    public void push(int val) {
        if (data.isEmpty()) {
            data.push(0l);
            min = val;
        } else {
            long diff = val - min;
            data.push(diff);
            if (diff < 0) {
                min = val;
            }
        }
    }

    public void pop() {
        Long diff = data.pop();
        // 更新到上一个最小值
        if (diff < 0) {
            min = min - diff;
        }
    }

    public int top() {
        // diff 差值小于0 ，当前min就是栈顶元素
        if (data.peek() < 0) {
            return (int) (min);
        }
        return (int) (data.peek() + min);
    }

    public int getMin() {
        return (int) min;
    }

}
