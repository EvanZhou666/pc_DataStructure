package com.pc.LeetCode.题236.二叉树的最近公共祖先;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *@author Evan
 *@since 2021/3/3 22:26
 */
public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if (root == null) {
			return null;
		}

		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		boolean isPInLeft = dfs(root.left, p);
		boolean isQInLeft = dfs(root.left,q);

		if (isPInLeft && isQInLeft) {
			return lowestCommonAncestor(root.left,p,q);
		} else if (!isPInLeft && !isQInLeft){
			return lowestCommonAncestor(root.right,p,q);
		} else {
			return root;
		}
	}

	/**
	 * 深度遍历，判断node是否存在于以root为根的树上
	 * @param root
	 * @param node
	 * @return
	 */
	private boolean dfs(TreeNode root,TreeNode node) {
		if (root == null) {
			return false;
		}

		if (root.val == node.val) {
			return true;
		}

		boolean dfs = dfs(root.left, node);
		boolean dfs1 = dfs(root.right, node);
		return dfs || dfs1;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(TreeNode.listOfElecments(3));
		ll.add(TreeNode.listOfElecments(5,1));
		ll.add(TreeNode.listOfElecments(6,2,0,8));
		ll.add(TreeNode.listOfElecments(null,null,7,4,null,null,null,null));
		TreeNode treeNode = TreeNode.conver2Tree(ll);
		TreeNode treeNode1 = solution.lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(1));
		TreeNode treeNode2 = solution.lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(4));
		System.out.println(treeNode1.val);
		System.out.println(treeNode2.val);
	}

}
