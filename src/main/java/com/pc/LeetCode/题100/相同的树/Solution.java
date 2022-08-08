package com.pc.LeetCode.题100.相同的树;

/**
 * @author evan
 */
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p!=null&&q!=null) {
            if (p.val !=q.val) {
                return false;
            }
            boolean leftSameTree = isSameTree(p.left, q.left);
            boolean rightSameTree = isSameTree(p.right, q.right);
            return leftSameTree && rightSameTree;

        } else {
            return false;
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
