package com.pc.LeetCode.题199.二叉树的右视图;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 层序遍历，每一层的最后一个元素就是站在树的右侧，看到的节点值
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root!=null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            List<TreeNode> currentLevel = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll.left!=null) {
                    currentLevel.add(poll.left);
                }
                if (poll.right !=null) {
                    currentLevel.add(poll.right);
                }
                // 判断是否到了本层的最后一个节点
                if (queue.isEmpty()) {
                    result.add(poll.val);
                    for (TreeNode treeNode : currentLevel) {
                        queue.offer(treeNode);
                    }
                    currentLevel.clear();
                }
            }
        }
        return result;
    }

}
