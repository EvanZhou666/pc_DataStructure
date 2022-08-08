package com.pc.LeetCode.题450.删除二叉搜索树中的节点;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 找到要删除的节点，和节点左子树中最大的节点记为preccessor，交换他们的val值，并且把preccessor从树中移除
 *
 * @author Evan
 * @since 2021/3/7 21:43
 */
public class Solution {

	public TreeNode deleteNode(TreeNode root, int key) {
		return dfs(root, null, key);
	}

	public TreeNode dfs(TreeNode root, TreeNode parent, int key) {
		if (root == null) {
			return null;
		}

		if (root.val == key) {
			if (root.left == null && root.right == null) {
				return null;
			} else if (root.left != null && root.right == null) {
				return root.left;
			} else if (root.left == null && root.right != null) {
				return root.right;
			} else {
				TreeNode precessor = root.left;

				while (precessor.right != null) {
					precessor = precessor.right;
				}
				precessor.right = root.right;
				return root.left;
			}
		} else if (key < root.val) {
			root.left = dfs(root.left, root, key);
		} else {
			root.right = dfs(root.right, root, key);
		}
		return root;

	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(TreeNode.listOfElecments(5));
		ll.add(TreeNode.listOfElecments(3, 6));
		ll.add(TreeNode.listOfElecments(2, 4, null, 7));
		TreeNode root = TreeNode.conver2Tree(ll);
		//        TreeNode.levelOrder(root);
		Solution solution = new Solution();
		TreeNode node = solution.deleteNode(root, 3);
		TreeNode.levelOrder(node);
	}
}
