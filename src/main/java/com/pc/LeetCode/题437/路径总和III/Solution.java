package com.pc.LeetCode.题437.路径总和III;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * 暂时没有想到其它解法，双递归，先这样吧，看答案有人说 前缀和解法的
 *@author Evan
 *@since 2021/3/7 9:42
 */
public class Solution {

	private int res = 0;

	private int sum;

	public int pathSum(TreeNode root, int sum) {

		this.sum = sum;

		if (root ==null) {
			return res;
		}
		dfs(root,0);

		if (root.left!=null){
			pathSum(root.left,sum);
		}

		if (root.right!=null) {
			pathSum(root.right,sum);
		}

		return res;
	}

	/**
	 * 深度遍历以root为根的树,统计从这颗树根节点出发，在到叶子节点的过程中currentSum等于全局sum出现了几次，出现的次数就是以root为根的树，路径和等于sum的次数
	 * @param root
	 * @param currentSum
	 */
	public void dfs(TreeNode root, int currentSum) {

		if (root == null) {
			return;
		}

		if (root.val + currentSum == sum) {
			res++;
		}
		if (root.left != null) {
			dfs(root.left, currentSum + root.val);
		}

		if (root.right != null) {
			dfs(root.right, currentSum + root.val);
		}

	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(TreeNode.listOfElecments(10));
		ll.add(TreeNode.listOfElecments(5, -3));
		ll.add(TreeNode.listOfElecments(3, 2, null, 11));
		ll.add(TreeNode.listOfElecments(3, -2, null, 1,null,null,null,null));
		TreeNode root = TreeNode.conver2Tree(ll);
		Solution solution = new Solution();
		int res = solution.pathSum(root, 8);
		System.out.println(res);
	}
}
