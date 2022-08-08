package com.pc.LeetCode.题99.恢复二叉搜索树;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.levelOrder;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 *
 *@author Evan
 *@since 2021/2/21 21:59
 */
public class Solution {

	private Long preVal = Long.MIN_VALUE;
	private TreeNode preNode = null;

	private void swapNodeVal(TreeNode node1,TreeNode node2) {
		int temp = node1.val;
		node1.val = node2.val;
		node2.val = temp;
	}

	public void recoverTree(TreeNode root) {
		if (root == null) {
			return;
		}
//		if (root.left !=null && root.left.val>root.val) {
//			swapNodeVal(root.left,root);
//		}

//		if (root.right !=null && root.right.val<root.val) {
//			swapNodeVal(root.right,root);
//		}

		recoverTree(root.left);
		if (root.val <= preVal) {
			int num = root.val;
			root.val = preVal.intValue();
			preNode.val = num;
		}
		preVal = Long.valueOf(root.val);
		preNode = root;
		recoverTree(root.right);
	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
//		ll.add(listOfElecments(1));
//		ll.add(listOfElecments(3,null));
//		ll.add(listOfElecments(null,2,null,null));
		ll.add(listOfElecments(3));
		ll.add(listOfElecments(1,4));
		ll.add(listOfElecments(null,null,2,null));
		TreeNode treeNode = conver2Tree(ll);
		levelOrder(treeNode);

		System.out.println("===========恢复之后==========");
		Solution solution = new Solution();
	    solution.recoverTree(treeNode);
		levelOrder(treeNode);

	}
}
