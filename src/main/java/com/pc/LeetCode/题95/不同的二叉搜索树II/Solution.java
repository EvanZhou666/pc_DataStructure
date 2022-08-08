package com.pc.LeetCode.题95.不同的二叉搜索树II;

import java.util.List;

/**
 *给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/2/18 21:19
 */
public class Solution {

	private static int[] array = null;

	private int[] generateArrayN(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i + 1;
		}
		return array;
	}

	public List<TreeNode> generateTrees(int n) {
		array = generateArrayN(n);
		for (int i = 1; i < array.length; i++) {
			generateNode(i);
		}
		return null;
	}

	private TreeNode generateNode(int rootI) {
		if (rootI < 0) {
			return null;
		}

		//		TreeNode root = new TreeNode(array[rootI]);
		//		//		获取辅助节点左区间 [1,rootI-1]
		//
		//		if (rootI-1<1) {
		//			root.left = null;
		//		} else {
		//			// 生成左子树
		//			for(int i=1;i<rootI;i++) {
		//				TreeNode left = generateNode(rootI-1);
		//				root.left = left;
		//			}
		//		}
		//
		//		// 生成右子树
		//		for(int i=)

		//

		return null;

	}

	class TreeNode {
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



