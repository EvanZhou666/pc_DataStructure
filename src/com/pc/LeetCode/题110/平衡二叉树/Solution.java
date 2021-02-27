package com.pc.LeetCode.题110.平衡二叉树;

import com.pc.LeetCode.common.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * @author Evan
 * 树的高度 等于左右子树最大的高度加上1
 */
public class Solution {

    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }

        boolean leftIs = isBalanced(root.left);
        if (!leftIs) {
            return false;
        }

        boolean rightIs = isBalanced(root.right);
        if (!rightIs) {
            return false;
        }

        int heightL = getHeight(root.left);
        int heightR = getHeight(root.right);
        if (Math.abs(heightL-heightR)>1) {
            return false;
        }
        return true;
    }

    /**
     * 获取树的的高度
     * @param root
     * @return
     */
    private int getHeight (TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left,right)+1;
    }
}
