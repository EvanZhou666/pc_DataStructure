package com.pc.LeetCode.题160;

import com.pc.LeetCode.common.GoodQuestion;
import com.pc.LeetCode.common.ListNode;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
@GoodQuestion(type="链表系列")
@GoodQuestion(type="双指针系列")
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            if (p != null) {
                p = p.next;
            } else {
                p = headB;
            }

            if (q != null) {
                q = q.next;
            } else {
                q = headA;
            }
        }
        return p;
    }

}
