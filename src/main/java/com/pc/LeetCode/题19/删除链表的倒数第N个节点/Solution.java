package com.pc.LeetCode.题19.删除链表的倒数第N个节点;

/**
 *
 *@author Evan
 *@since 2020/9/5 18:17
 */

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;

		ListNode slow = dummyHead;
		ListNode fast = dummyHead;
		for (int i = 0; i < n + 1; i++) {
			fast = fast.next;
		}

		while (fast.next != null) {

			slow = slow.next;
			fast = fast.next;

		}

		slow.next = slow.next.next;
		return dummyHead.next;
	}
}
