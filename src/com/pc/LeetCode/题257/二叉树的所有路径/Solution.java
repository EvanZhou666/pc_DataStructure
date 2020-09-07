package com.pc.LeetCode.题257.二叉树的所有路径;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *@author Evan
 *@since 2020/9/7 12:49
 */
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {

	static List<String> list = new ArrayList<>();

	public static List<String> binaryTreePaths(TreeNode root) {

		if (root != null) {
			binaryTreePaths(root, "");
		}

		return list;
	}

	private static void binaryTreePaths(TreeNode root, String path) {

		StringBuilder builder = new StringBuilder(path);
		builder.append(root.val);
//		path += root.val;
		if (root.left == null && root.right == null) {
			// 是叶子节点
			list.add(builder.toString());
		}

		builder.append("->");

		if (root.left != null) {
			binaryTreePaths(root.left, builder.toString());
		}

		if (root.right != null) {
			binaryTreePaths(root.right, builder.toString());
		}

	}

	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);
		TreeNode node4 = new TreeNode(5);
		root.left = node1;
		root.right = node2;
		node1.right = node4;
		binaryTreePaths(root);
		System.out.println(list);
	}
}
