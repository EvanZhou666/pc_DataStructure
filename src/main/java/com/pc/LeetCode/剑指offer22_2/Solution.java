package com.pc.LeetCode.剑指offer22_2;

import com.pc.LeetCode.common.ListNode;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        // 链表为null，或者只有一个节点的链表，一定不存在环
        if (head == null || head.next == null) {
            return null;
        }
        if (head == head.next) {
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast !=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                //System.out.println(slow.val);
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
