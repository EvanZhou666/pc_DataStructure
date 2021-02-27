package com.pc.LeetCode.题111.二叉树的最小深度;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 * @author Evan
 */
public class Solution {

    /**
     * 层序遍历，逐层遍历，第一次出现左右节点都为空的节点时，该节点的深度就是最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
       if (root == null) {
           return 0;
       }
       int deep = 1;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        TreeNode poll;
        List<TreeNode> nextLevelNode = new ArrayList<>();
        while (!queue.isEmpty()) {
            poll = queue.poll();
            if (poll.left == null && poll.right == null) {
                return deep;
            }

            if (poll.left!=null) {
                nextLevelNode.add(poll.left);
            }
            if (poll.right!=null) {
                nextLevelNode.add(poll.right);
            }

            // 如果出队完，说明该层元素已经遍历完，进入下一层
            if (queue.isEmpty()) {
                while (!nextLevelNode.isEmpty()) {
                    queue.offer(nextLevelNode.remove(0));
                }
                deep++;
            }
        }
        return deep;
    }
}
