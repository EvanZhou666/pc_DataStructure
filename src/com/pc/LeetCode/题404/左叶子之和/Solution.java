package com.pc.LeetCode.题404.左叶子之和;

import com.pc.LeetCode.common.TreeNode;

/**
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 *@author Evan
 *@since 2021/3/3 23:01
 *  左叶子，左右节点为空，并且是某个节点的左节点的节点是左叶子 根节点不能看作左叶子
 */
public class Solution {
	public int sumOfLeftLeaves(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int leftValue;
		if (root.left != null && root.left.left == null && root.left.right == null) {
			leftValue = root.left.val;
		} else {
			leftValue = sumOfLeftLeaves(root.left);
		}

		int rightValue = sumOfLeftLeaves(root.right);

		return leftValue + rightValue;
	}

}
