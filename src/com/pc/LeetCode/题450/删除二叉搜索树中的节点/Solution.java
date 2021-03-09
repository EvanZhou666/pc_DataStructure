package com.pc.LeetCode.题450.删除二叉搜索树中的节点;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 找到要删除的节点，和节点左子树中最大的节点记为preccessor，交换他们的val值，并且把preccessor从树中移除
 *
 * @author Evan
 * @since 2021/3/7 21:43
 */
public class Solution {

    private TreeNode keyNode;
    private TreeNode precessorParent;
    private boolean precessorInRightTree;

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        dfs(root, null, key);
        return root;
    }

    public void dfs(TreeNode root, TreeNode parent, int key) {

        if (root.val == key) {
            keyNode = root;
            // 找到前驱节点
            TreeNode precessor = removePrecessor(root);
            if (precessor != null) {
                if (precessorInRightTree) {
                    precessorParent.right = null;
                } else {
                    keyNode.left = null;
                }
                keyNode.val = precessor.val;
            } else {

            }
        } else if (key > root.val) {
            dfs(root.right, root, key);
        } else if (key < root.val) {
            dfs(root.left, root, key);
        }
    }


    public TreeNode removePrecessor(TreeNode root) {
        // 比root稍微小一点的数
        TreeNode precessor = null;
        if (root.left != null) {
            precessor = root.left;
            precessorParent = root;
            TreeNode node = precessor;
            while (node != null && node.right != null) {
                precessorParent = node;
                precessorInRightTree = true;
                node = node.right;
            }
            precessor = node;
        }
        return precessor;

    }


    public static void main(String[] args) {
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(listOfElecments(5));
        ll.add(listOfElecments(3, 6));
        ll.add(listOfElecments(2, 4, null, 7));
        TreeNode root = conver2Tree(ll);
//        TreeNode.levelOrder(root);
        Solution solution = new Solution();
        TreeNode node = solution.deleteNode(root, 5);
        TreeNode.levelOrder(node);
    }
}
