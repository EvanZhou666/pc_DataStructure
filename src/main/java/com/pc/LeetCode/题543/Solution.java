package com.pc.LeetCode.题543;

import com.pc.LeetCode.common.TreeNode;

public class Solution {

    private int maxLen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxLen = 0;
        dfs(root);
        return maxLen;
    }

    // 【root节点和它的左子树】或者【root节点和它右子树】构成的最大直径
    private int dfs(TreeNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        if (root.left != null && root.right != null) {
            maxLen = Math.max(left + right + 2, maxLen);
        } else {
            maxLen = Math.max(Math.max(left, right) + 1, maxLen);
        }
        return Math.max(left, right) + 1;
    }
}
