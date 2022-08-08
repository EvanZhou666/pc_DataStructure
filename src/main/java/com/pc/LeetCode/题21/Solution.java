package com.pc.LeetCode.题21;


/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 */
public class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode(0);
        ListNode head = merged;

        ListNode head1 = list1;
        ListNode head2 = list2;

        // 一直循环，比较链表头的元素，小的在前，直到有1条链表遍历结束
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                head.next = new ListNode(head1.val);
                head = head.next;
                head1 = head1.next;
            } else {
                head.next = new ListNode(head2.val);
                head = head.next;
                head2 = head2.next;
            }
        }

        // 链表1遍历完了，链表2还没有
        if (head1 == null && head2 != null) {
            head.next = head2;
        } else if (head1 != null && head2 == null) { // 链表2遍历完了，链表1还没有
            head.next = head1;
        }
        return merged.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
