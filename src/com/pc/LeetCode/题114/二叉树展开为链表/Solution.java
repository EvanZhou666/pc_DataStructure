package com.pc.LeetCode.题114.二叉树展开为链表;

import com.pc.LeetCode.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *@author Evan
 *@since 2021/2/28 16:04
 */
public class Solution {

	Deque<TreeNode> deque = new LinkedList();

	/**
	 * 两次遍历 解决问题
	 * @param root
	 */
	public void flatten(TreeNode root) {
		dfs(root);
		TreeNode pre = null;
		TreeNode poll;
		while (!deque.isEmpty()) {
			poll = deque.pollFirst();
			if (poll!=null) {
				poll.left = poll.right =null;
			}
			if (pre !=null) {
				pre.left = null;
				pre.right = poll;
			}

			pre = poll;
		}

	}

	public void dfs(TreeNode root) {

		if (root == null) {
			return;
		}
		deque.addLast(root);
		dfs(root.left);
		dfs(root.right);
	}


	public static void main(String[] args) {

	}
}
