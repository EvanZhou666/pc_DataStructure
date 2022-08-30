package com.pc.LeetCode.题206;

import com.pc.LeetCode.common.ListNode;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 */
public class Solution {
//    public ListNode reverseList(ListNode head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        ListNode pre = head;
//
//        ListNode cur;
//
//        cur = pre.next;
//        while (cur != null) {
//            pre.next = null;
//            ListNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
//    }

    // 迭代写法优化
    public ListNode reverseList(ListNode head) {
        // 这里很细节，因为cur是从第1个元素开始的，如果是从第2个元素开始，
        // 针对第1个元素还有额外处理第一个元素的next等于null
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
