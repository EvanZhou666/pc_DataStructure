package com.pc.LeetCode.题86;

import com.pc.LeetCode.common.ListNode;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。<br/>
 * 输入：head = [1,4,3,2,5,2], x = 3 <br/>
 * 输出：[1,2,2,4,3,5] <br/>
 * <img src="https://assets.leetcode.com/uploads/2021/01/04/partition.jpg"/>
 *
 */
public class Solution {


    public ListNode partition(ListNode head, int x) {
        ListNode dummyLeft = new ListNode();
        ListNode dummyRight = new ListNode();
        ListNode leftCur = dummyLeft;
        ListNode rightCur = dummyRight;

        while (head != null) {
            if (head.val < x) {
                leftCur.next = head;
                leftCur = leftCur.next;
            } else {
                rightCur.next = head;
                rightCur = rightCur.next;
            }
            head = head.next;
        }
        leftCur.next = dummyRight.next;
        return dummyLeft.next;
    }

}
