package com.pc.LeetCode.题101.对称二叉树;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author Evan
 */
public class Solution {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {return true;}
        return checkSymmetric(root.left,root.right);
    }

    /**
     * 检查两个节点是否关于中轴对称
     * @param left
     * @param right
     * @return
     */
    private boolean checkSymmetric(TreeNode left,TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null){
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            boolean a = checkSymmetric(left.left, right.right);
            boolean b = checkSymmetric(left.right, right.left);
            return a&&b;
        }
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
