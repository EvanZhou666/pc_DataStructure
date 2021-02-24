package com.pc.LeetCode.题99.恢复二叉搜索树;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *   2
 * /  \
 * 1    4
 *     /
 *    3
 *     \
 *      5
 * 中序遍历的结果是 1 2 3 5 4
 */
public class Solution {

    static TreeNode lastNode;

    public static void recoverTree(TreeNode root) {
        lastNode = new TreeNode(Integer.MIN_VALUE,null,null);
        inOrdertraversal(root);
    }

    // 中序遍历
    public static void inOrdertraversal(TreeNode root) {

        if (root == null) {
            return;
        }
        inOrdertraversal(root.left);
        System.out.println(root.val);
        if (lastNode !=null && lastNode.val > root.val) {
            // 交换该节点和上个节点的值
            int temp = lastNode.val;
            lastNode.val = root.val;
            root.val = temp;
        }
        lastNode = root;
        inOrdertraversal(root.right);
    }

    public static void main(String[] args) {

    }
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
