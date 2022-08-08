package com.pc.LeetCode.题226.翻转二叉树;

import com.pc.LeetCode.common.TreeNode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class Solution {

    /**
     * 要反转二叉树，只要把root的左右儿子视为新的“子树”,将所有"子树"的左右儿子顺序对调，则实现整颗二叉树的翻转。
     * "后序遍历"解法
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.right;
        root.left = root.right;
        root.right = temp;
        return root;
    }

}
