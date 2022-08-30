package com.pc.LeetCode.题206;

import com.pc.LeetCode.common.ListNode;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Solution2 {

//    // 反转链表后 新的头节点
//    private ListNode newHead;
//
//    // 反转链表，递归写法1
//    public ListNode reverseList(ListNode head) {
//        if (head == null) {
//            return head;
//        }
//        newHead = null;
//        ListNode tail = dfs(head);
//        // 新的尾节点是反转之前的头节点，next节点要赋值为null，否则会导致链表成环
//        tail.next = null;
//        return newHead;
//    }
//
//    private ListNode dfs(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//
//        ListNode reversed = dfs(head.next);
//
//        if (reversed == null) {
//            // 当前节点就是反转后的头节点
//            newHead = head;
//        } else {
//            reversed.next = head;
//        }
//        return head;
//    }

    // 反转链表，递归写法优化
    // 上面的写法是始终返回反转后的子链表的尾节点，因此需要一个成员变量记录整个完整链表的头节点。
    // 那能不能让递归dfs函数直接返回头节点呢？答案是可以的
    public ListNode reverseList(ListNode head) {
        ListNode newHead = dfs(head);
        return newHead;
    }

    private ListNode dfs(ListNode head) {

        if (head == null || head.next == null) {
            // 在这里返回新链表的头节点
            return head;
        }
        ListNode node = dfs(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
