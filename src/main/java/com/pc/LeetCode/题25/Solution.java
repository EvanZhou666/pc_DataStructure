package com.pc.LeetCode.题25;

import com.pc.LeetCode.common.ListNode;

import java.util.List;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。<br/>
 *
 * <img src="./img.png"/><br/>
 * <img src="./img_1.png"/><br/>
 */
public class Solution {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode kHead = doReverseGroup(head, k);
        return kHead;
    }

    private static ListNode reverseListBetweenAB(ListNode a, ListNode b) {
        if (a == null || a.next == null || a.next == b) {
            return a;
        }
        ListNode head = reverseListBetweenAB(a.next, b);
        a.next.next = a;
        a.next = null;
        return head;
    }


    private static ListNode doReverseGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode start = head, end = head;

        int i = 0;

        while (end != null) {
            if (i == k) {
                break;
            }
            end = end.next;
            i++;
        }


        if (i == k) {
            // 反转长度为k的链表
            ListNode leftHead = reverseListBetweenAB(start, end);
            // 递归反转右边的链表。
            ListNode rightHead = doReverseGroup(end, k);
            start.next = rightHead;
            return leftHead;
        } else {
            // 剩下的子链表长度不足k
            return start;
        }
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
        ListNode node = reverseKGroup(node1, 2);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
