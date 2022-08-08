package com.pc.LeetCode.题103.二叉树的锯齿形层序遍历;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Evan
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> currentLevel = new ArrayList<>();
        if (root!=null) {
            currentLevel.add(root);
        }
        // 是否按从左到右升序输入
        boolean asc = true;
        while (!currentLevel.isEmpty()) {
            queue.addAll(currentLevel);
            if (asc) {
                result.add(currentLevel);
            } else {
                List<TreeNode> reverse = new ArrayList<>(currentLevel.size());
                for (int i=currentLevel.size()-1;i>=0;i--) {
                    reverse.add(currentLevel.get(i));
                }
                result.add(reverse);
            }
            asc =!asc;
            currentLevel = new ArrayList<>();
            TreeNode poll = null;
            while ((poll = queue.poll())!=null) {
                if (poll.right !=null) {
                    currentLevel.add(poll.right);
                }
                if (poll.left !=null) {
                    currentLevel.add(poll.left);
                }
            }
        }

        List<List<Integer>> result2 = new ArrayList<>();
        for (List<TreeNode> ll : result) {
            List<Integer> l = new ArrayList<>();
            for (TreeNode node : ll) {
                l.add(node.val);
            }
            result2.add(l);
        }

        return result2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(TreeNode.listOfElecments(1));
        ll.add(TreeNode.listOfElecments(2,3));
        ll.add(TreeNode.listOfElecments(4,null,null,5));
        TreeNode treeNode = TreeNode.conver2Tree(ll);
        solution.zigzagLevelOrder(treeNode);
    }
}
