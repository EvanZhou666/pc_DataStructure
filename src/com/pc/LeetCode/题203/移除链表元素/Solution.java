package com.pc.LeetCode.题203.移除链表元素;

/**
 *删除链表中等于给定值 val 的所有节点。
 *@author Evan
 *@since 2020/9/5 12:01
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {

	public static ListNode removeElements(ListNode head, int val) {
		// 定义dummyHead是虚拟头指针
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		// 定义cur为遍历指针,检查cur的下一个元素是不是等于val
		ListNode cur = dummyHead;

		ListNode temp;
		while (cur.next != null) {
			if (cur.next.val == val) {
				temp = cur.next;
				cur.next = cur.next.next;
				temp.next = null;
			} else {
				cur = cur.next;
			}
		}
		return dummyHead.next;

	}

	public static void main(String[] args) {
//		1->2->6->3->4->5->6
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(6);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(5);
		ListNode n7 = new ListNode(6);
		n1.next =n2;
		n2.next =n3;
		n3.next =n4;
		n3.next =n4;
		n4.next =n5;
		n5.next =n6;
		n6.next =n7;
		ListNode listNode = removeElements(n1, 6);
		ListNode h = listNode;
		while (h!=null) {
			System.out.print(h.val+"-->");
			h = h.next;
		}


	}
}
