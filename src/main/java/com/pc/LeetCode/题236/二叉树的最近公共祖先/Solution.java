package com.pc.LeetCode.题236.二叉树的最近公共祖先;

import com.pc.LeetCode.common.GoodQuestion;
import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/">236. 二叉树的最近公共祖先</a>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * @author Evan
 * @since 2021/3/3 22:26
 */
@GoodQuestion(type = "二叉树系列")
public class Solution {

	/**
	 * 后序遍历
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        // p和 q是否至少有1个节点位于root节点的左子树上，返回p或者q
        TreeNode pOrQInLeft = lowestCommonAncestor(root.left, p, q);
		// p和 q是否至少有1个节点位于root节点的右子树上，返回p或者q
		TreeNode pOrQInRight = lowestCommonAncestor(root.right, p, q);
		// 如果p或者q有1个位于root的左子树，另外1个位于root的右子树上，说明root就是p，q的最近公共祖先。
        if (pOrQInLeft != null && pOrQInRight != null) {
            return root;
        } else if (pOrQInLeft != null) {// 说明p和q两个都在root的左子树上，且root.left就是p点或者q点、
        	// 毫无疑问，那么要么p是q的最近公共祖先，要么q是p的最近公共祖先。
            return pOrQInLeft;
        } else if (pOrQInRight != null) // 同理 说明p和q两个都在root的右子树上，且root.right就是p点或者q点、
            return pOrQInRight;
        return null;
    }

    /**
     * 深度遍历，判断node是否存在于以root为根的树上
     *
     * @param root
     * @param node
     * @return
     */
    private boolean dfs(TreeNode root, TreeNode node) {
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
        ll.add(TreeNode.listOfElecments(5, 1));
        ll.add(TreeNode.listOfElecments(6, 2, 0, 8));
        ll.add(TreeNode.listOfElecments(null, null, 7, 4, null, null, null, null));
        TreeNode treeNode = TreeNode.conver2Tree(ll);
        TreeNode treeNode1 = solution.lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(1));
        TreeNode treeNode2 = solution.lowestCommonAncestor(treeNode, new TreeNode(5), new TreeNode(4));
        System.out.println(treeNode1.val);
        System.out.println(treeNode2.val);
    }

}
