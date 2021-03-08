package com.pc.LeetCode.题450.删除二叉搜索树中的节点;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 *@author Evan
 *@since 2021/3/7 21:43
 */
public class Solution {

	private TreeNode parentOfKey = null;

	public TreeNode deleteNode(TreeNode root, int key) {

		// 1 先深度遍历找到该节点
		TreeNode keyNode = dfs(root, key);
		if (keyNode  == null) {
			return root;
		}

		if (parentOfKey != null) {
			// 左子树中最大的节点
			TreeNode node = keyNode;
			if (node.left != null) {
				// 前驱节点父节点
				TreeNode precursorParent = node;
				node = node.left;
				// 轮询 直至找到findkeynode的前驱节点
				while (node.right!=null && node.left!=null) {
					precursorParent = node;
					node = node.right;
				}
				parentOfKey.left = keyNode .left;
				keyNode.left.right =keyNode.right;
				keyNode.right =null;
				keyNode.left =null;
			} else {
				parentOfKey.left = node.right;
				node.right = null;
			}
			return root;
		} else {


			return root;
		}

	}

	public TreeNode dfs(TreeNode root, int key) {

		if (root.val == key) {
			return root;
		} else if (key > root.val) {
			parentOfKey = root;
			return dfs(root.right, key);
		} else if (key < root.val) {
			parentOfKey = root;
			return dfs(root.left, key);
		}

		return null;
	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(listOfElecments(5));
		ll.add(listOfElecments(3, 6));
		ll.add(listOfElecments(2, 4, null, 7));
		TreeNode root = conver2Tree(ll);
		Solution solution = new Solution();
		TreeNode node = solution.deleteNode(root, 5);
		TreeNode.levelOrder(node);
	}
}
