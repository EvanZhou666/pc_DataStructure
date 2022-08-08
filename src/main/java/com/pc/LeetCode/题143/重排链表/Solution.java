package com.pc.LeetCode.题143.重排链表;

/**
 * 暴力解法1 把链表存储到线性表中，然后用双指针依次从头尾取元素即可。
 * 执行用时：4 ms, 在所有 Java 提交中击败了28.85%的用户
 * 内存消耗：42.5 MB, 在所有 Java 提交中击败了67.81%的用户
 *@author EvanZhou
 *@since 2020/9/2
 */
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class Solution {

	public static void reorderList(ListNode head) {
		ListNode p = head;
		ListNode[] listNodes = new ListNode[8];
		int i = 0;
		while (p != null) {
			listNodes[i] = p;
			p = p.next;
			listNodes[i].next = null;
			i++;
			if (i == listNodes.length * 0.75) {
				listNodes = reSize(listNodes);
			}
		}

		//m,n 数组首尾指针
		int m = 0, n = i - 1;
		while (n - m > 1) {
			listNodes[m].next = listNodes[n];
			listNodes[n].next = listNodes[m + 1];
			m++;
			n--;
		}
		// 循环终止后，如果m，n指针相差1，那么这是一个偶数链
		if (n - m == 1) {
			listNodes[m].next = listNodes[n];
		}
	}

	// 扩容
	private static ListNode[] reSize(ListNode[] listNodes) {
		ListNode[] newList = new ListNode[listNodes.length * 2];
		for (int j = 0; j < listNodes.length; j++) {
			newList[j] = listNodes[j];
			listNodes[j] = null;
		}
		return newList;
	}

	public static void main(String[] args) {
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4 = new ListNode(4);
		ListNode listNode5 = new ListNode(5);
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		listNode4.next = listNode5;
		reorderList(listNode1);
		print(listNode1);
	}

	private static void print(ListNode head) {
		ListNode p = head;
		while (p != null) {
			System.out.println("--->" + p.val);
			p = p.next;
		}
	}
}
