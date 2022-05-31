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
            // 相遇的时候，快指针比慢指针多走一圈，多走的步数就是环的长度
            // 设在第x个位置成环 链表的长度为n 则：(t-x) % n = (2t-x) %n
            // 2 * (x/1) = (x/1)
            // (2-1) *
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


