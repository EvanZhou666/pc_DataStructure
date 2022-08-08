package com.pc.LeetCode.题141;

/**
 * 141.环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class Solution {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head, slow = head;
        while (slow.next != null) {
            // 如果链表没有环，快指针一定先到链尾
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode1;
        Solution solution = new Solution();
        boolean b = solution.hasCycle(listNode1);
        System.out.println(b);
    }
}


