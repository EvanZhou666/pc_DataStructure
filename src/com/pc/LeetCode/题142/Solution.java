package com.pc.LeetCode.题142;

/**
 * 141.环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class Solution {

    public ListNode hasCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (slow.next != null) {
            // 如果链表没有环，快指针一定先到链尾
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            // 结论 从相遇点到入环点的距离加上 n-1圈的环长，恰好等于从链表头部到入环点的距离。
            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
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
        ListNode listNode = solution.hasCycle(listNode1);
        System.out.println(listNode.val);
    }
}


