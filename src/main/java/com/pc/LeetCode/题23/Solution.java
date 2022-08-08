package com.pc.LeetCode.题23;

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null && lists.length == 0) {
            return new ListNode();
        }

        if (lists.length == 1) {
            return lists[0];
        }

        ListNode merged = null;
        for (int i = 0; i < lists.length; i++) {
            merged = mergeTwoLists(merged, lists[i]);
        }
        return merged;
    }

    /**
     * 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
