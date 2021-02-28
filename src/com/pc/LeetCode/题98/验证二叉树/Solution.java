package com.pc.LeetCode.题98.验证二叉树;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *@author Evan
 *@since 2021/2/21 13:26
 */
public class Solution {

	private LinkedList<TreeNode> queue = null;

	private Long preVal = Long.MIN_VALUE;

	/**
	 *
	 * 解法2
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		boolean leftIsBst = isValidBST(root.left);
		if (root.val <= preVal) {
			return false;
		}
		preVal = Long.valueOf(root.val);
		boolean rightIsBst = isValidBST(root.right);
		return leftIsBst && rightIsBst;
	}

	/**
	 * 解法1
	 * @param root
	 * @return
	 */
	public boolean isValidBST2(TreeNode root) {

		if (queue == null) {
			queue = new LinkedList<>();
		}
		if (root == null) {
			return true;
		}

		boolean leftIsBst = isValidBST2(root.left);
		TreeNode pre = queue.pollFirst();
		queue.push(root);
		if (pre != null && root.val <= pre.val) {
			return false;
		}
		boolean rightIsBst = isValidBST2(root.right);
		return leftIsBst && rightIsBst;

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
