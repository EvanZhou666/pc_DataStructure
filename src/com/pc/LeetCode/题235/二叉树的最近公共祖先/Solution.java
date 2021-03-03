package com.pc.LeetCode.题235.二叉树的最近公共祖先;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/3/2 22:56
 *  注意是二叉搜索树
 */
public class Solution {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		if (p.val < root.val && q.val < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (p.val > root.val && q.val > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return root;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(listOfElecments(6));
		ll.add(listOfElecments(2,8));
		ll.add(listOfElecments(0,4,7,9));
		ll.add(listOfElecments(null,null,3,5,null,null,null,null));
		TreeNode treeNode = conver2Tree(ll);
		TreeNode treeNode1 = solution.lowestCommonAncestor(treeNode, new TreeNode(2), new TreeNode(4));
		System.out.println(treeNode1.val);
	}
}
