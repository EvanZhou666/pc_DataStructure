package com.pc.LeetCode.题107.二叉树的层序遍历2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *@author Evan
 *@since 2021/2/19 21:11
 */
public class Solution {

	private List<List<Integer>> result;

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		result = new ArrayList<>();
		if (root != null) {
			List<TreeNode> first = new ArrayList<>();
			first.add(root);
			getNextNodes(first);
		}
		return result;

	}

	/**
	 * 获取下一层的节点
	 * @param currentNodes
	 */
	private void getNextNodes(List<TreeNode> currentNodes) {

		if (currentNodes == null || currentNodes.isEmpty()) {
			return;
		}

		List<TreeNode> nextNodes = new LinkedList<>();

		for (TreeNode current : currentNodes) {
			if (current.left!=null) {
				nextNodes.add(current.left);
			}

			if (current.right!=null) {
				nextNodes.add(current.right);
			}
		}
		getNextNodes(nextNodes);
		List<Integer> collect = currentNodes.stream().map(n -> n.val).collect(Collectors.toList());
		result.add(collect);
//		print(currentNodes);
	}

//	private void print(List<TreeNode> nodes){
//		for (TreeNode node : nodes) {
//			System.out.print(node.val+",");
//		}
//		System.out.println();
//
//	}

	public static void main(String[] args) {
		Solution solution = new Solution();
//		TreeNode root = null;
		TreeNode root = new TreeNode(3);
		TreeNode left1 = new TreeNode(9);
		TreeNode right1 = new TreeNode(20);
		root.left =left1;
		root.right= right1;
		TreeNode left2 = new TreeNode(15);
		TreeNode right2 = new TreeNode(7);
		right1.left = left2;
		right1.right = right2;
		solution.levelOrderBottom(root);

	}
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return "TreeNode{" +
					"val=" + val +
					'}';
		}
	}
}
