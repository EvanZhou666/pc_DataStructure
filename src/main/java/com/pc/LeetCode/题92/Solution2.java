package com.pc.LeetCode.题92;

import com.pc.LeetCode.common.ListNode;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class Solution2 {

    /**
     * 使用迭代的方式，反转链表中间某段链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int index = 1;

        ListNode leftNode = null;
        ListNode needReverseLeftNode = null;
        ListNode needReverseRightNode = null;
        ListNode rightNode = null;

        ListNode pre = null;

        // 找到需要反转的那一段
        while (cur != null) {
            if (left == index) {
                leftNode = pre;
                needReverseLeftNode = cur;
            }
            if (right == index) {
                rightNode = cur.next;
                needReverseRightNode = cur;
                break;
            }
            pre = cur;
            cur = cur.next;
            index ++;
        }

        // reverse [needReverseLeftNode ... needReverseRightNode]
        ListNode[] headTail = reverse(needReverseLeftNode, needReverseRightNode);

        // 边界判断，可能从链表第0个元素就要反转。
        if (leftNode == null) {
            head = headTail[0];
        } else {
            leftNode.next = headTail[0];
        }
        headTail[1].next = rightNode;
        return head;
    }

//    [head, tail]
    private static ListNode[] reverse(ListNode leftNode, ListNode rightNode) {
        if (leftNode == rightNode) {
            return new ListNode[]{leftNode, leftNode};
        }

        ListNode finished = rightNode.next;

        ListNode pre = null;
        ListNode cur = leftNode;

        ListNode reversedHead = null;
        ListNode reversedTail = cur;

        while (true) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

            if (cur == finished) {
                reversedHead = pre;
                break;
            }
        }

        return new ListNode[]{reversedHead, reversedTail};
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        reverseBetween(node1, 2, 4);
    }
}
