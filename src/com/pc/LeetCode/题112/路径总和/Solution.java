package com.pc.LeetCode.题112.路径总和;

import com.pc.LeetCode.common.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/2/27 21:56
 *  注意 是二叉树，不是二叉搜索树
 */
public class Solution {

	private int targetSum;

	/**
	 * 深度遍历root
	 * @param root
	 * @param sumNow
	 * @return
	 */
	public boolean dfs(TreeNode root, int sumNow, boolean isLeaf) {

		if (root == null) {
			return sumNow == targetSum && isLeaf;
		}

		if (sumNow + root.val > targetSum) {
			return false;
		}

		sumNow += root.val;

		boolean isL = dfs(root.left, sumNow, root.left == null && root.right == null);

		boolean isR = dfs(root.right, sumNow, root.left == null && root.right == null);

		return isL || isR;
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {

		if (root == null) {
			return false;
		}
		this.targetSum = targetSum;
		return dfs(root, 0,root.left == null && root.right == null);

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}
