package com.pc.LeetCode.题148;

import com.pc.LeetCode.common.ListNode;

/**
 * 148. 排序链表
 * https://leetcode.cn/problems/sort-list/
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 要求 O(n log n) 时间复杂度和常数级空间复杂度下
 */
public class Solution {

    // todo EvanZhou 官方答案，待自己实现 头条考过
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // todo 代码review 建立虚拟节点
        ListNode dummyHead = new ListNode(0, head);
        // todo 代码review 分段值，从1开始每次倍增 1、2、4、8
        for (int subLength = 1; subLength < length; subLength <<= 1) {

            ListNode prev = dummyHead, curr = dummyHead.next;

            while (curr != null) {
                ListNode head1 = curr;

                // todo 代码review  获取当前分段的尾节点
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }

                // todo 代码review  下一个分段的头节点
                ListNode head2 = curr.next;
                // todo 代码review  断开上一个分段和下一个分段之间的联系
                curr.next = null;

                // todo 代码review  下一个分段的头节点
                curr = head2;

                // todo 代码review  又是一次轮询，找到下一个分段的尾节点
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // todo 代码review  临时遍历，为了存储segment3或者segment5.....
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    // todo 代码review 断开联系
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                // todo 代码review 又一次遍历，话说这样的时间复杂度 真的是N*LogN吗.....
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }


    /**
     * 自底向上的归并排序
     */
//    public ListNode sortList(ListNode head) {
//        // 链表的长度
//        int length = getNodeLength(head);
//        ListNode newHead = null;
//
//        // segment分段大小 ，从1开始，每次迭代翻倍
//        for (int segment = 1; segment <= length; segment = segment + segment) {
//
//            ListNode cur = head;
//            while ( cur != null) {
//                // 获取当前分段的尾节点
//                // [cur, tail]
//                ListNode tail = getNode(cur, segment - 1);
//                // 下一个分段的尾指针
//                ListNode nextSegmentTail = getNode(cur, 2 * segment - 1);
//
//                ListNode tail1= tail.next;
//                ListNode tail2= nextSegmentTail.next;
//
//                tail.next = null;
//                nextSegmentTail.next = null;
//
//                newHead = mergeTwoLists(cur, tail.next);
//                cur = getNode(cur, segment);
//            }
//        }
//        return newHead;
//    }

    // 获取数组长度
    private int getNodeLength(ListNode head) {
        ListNode h = head;
        int len = 0;
        while (h != null) {
            len++;
            h = h.next;
        }
        return len;
    }

    private ListNode getNode(ListNode head, int offset) {
        int count = 0;
        ListNode h = head;
        while (count < offset && h != null) {
            h = h.next;
            count++;
        }
        return h;
    }

    /**
     *
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode(0);
        ListNode head = merged;

        ListNode head1 = list1;
        ListNode head2 = list2;

        // 一直循环，比较链表头的元素，小的在前，直到有1条链表遍历结束
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                head.next = head1;
                head = head.next;
                head1 = head1.next;
            } else {
                head.next = head2;
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
