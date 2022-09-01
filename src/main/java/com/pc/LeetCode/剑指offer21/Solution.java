package com.pc.LeetCode.剑指offer21;

import com.pc.LeetCode.common.ListNode;

/**
 * https://leetcode.cn/problems/SLwz0R/
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 倒数第k个节点的前驱节点
        ListNode pre = null;

        ListNode slow = head, fast = head;

        for (int i = 0; i < n; i++) {
            if (fast != null) {
                fast = fast.next;
            }
        }


        while (fast != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }

        // 需要分类讨论，如果删除的是链表中的第1个元素，由于没有前驱节点，因此需要特殊处理
        if (pre == null) {
            return head.next;
        }

        pre.next = slow.next;
        return head;
    }

}
