package com.pc.LeetCode.题144.二叉树的前序遍历;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树前序遍历，递归解法和非递归解法
 *@author Evan
 *@since 2021/3/7 21:03
 */
public class Solution {

	/**
	 * 递归解法
	 * @param root
	 * @return
	 */
//	public List<Integer> preorderTraversal(TreeNode root) {
//		List list = new ArrayList();
//		return pre(root,list);
//
//	}

	private List pre(TreeNode root,List list) {
		if (root == null) return list;
		list.add(new Integer(root.val));
		pre(root.left,list);
		pre(root.right,list);
		return list;
	}

	/**
	 * 非递归解法
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		LinkedList<TreeNode> stack =  new LinkedList<>();

		TreeNode cur;

		if (root!=null) {
			stack.push(root);
		}

		while (!stack.isEmpty()) {
			cur = stack.pop();
			result.add(cur.val);
			if (cur.right!=null) {
				stack.push(cur.right);
			}

			if (cur.left!=null) {
				stack.push(cur.left);
			}

		}
		return result;
	}
}
