package com.pc.LeetCode.题337;

import com.pc.LeetCode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 *
 */
public class Solution {

    // 状态转移方程：
    // 定义f（node）为在偷取node节点时候能够获取到的最大利润
    // 定义g（node）为在不偷取node节点时候能够获取的最大利润
    // 故，node节点最终能够偷取的最大利润为max(f(node),g(node))
    // f(root) = root.val + g(node.left) + g(node.right)
    // g(root) = max(f(root.left), g(root.left)) + max(f(root.right),g(root.right))
    //
    // 根据状态转移方程，可以推到出最优子结构就是叶子节点。假如leaf是某个叶子节点，则：
    // f(leaf) = leaf.val;
    // g(leaf) = 0;
    // max(f(leaf), g(leaf)) = max(leaf.val, 0) = leaf.val
    // 由于要先计算左右子孩子的利润，在计算父节点的利润，很容易想到这是一个dfs的后序遍历。
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> fmap = new HashMap<>(16);
        Map<TreeNode, Integer> gmap = new HashMap<>(16);
        dfs(root, fmap, gmap);
        return Math.max(fmap.get(root), gmap.get(root));
    }

    public void dfs(TreeNode node, Map<TreeNode, Integer> fmap, Map<TreeNode, Integer> gmap) {

        if (node == null) {
            return;
        }

        dfs(node.left, fmap, gmap);
        dfs(node.right, fmap, gmap);

        // 偷取node能够获取的最大利润
        int node_f_profile = node.val + gmap.getOrDefault(node.left, 0) + gmap.getOrDefault(node.right, 0);
        fmap.put(node, node_f_profile);
        int node_g_profile = Math.max(fmap.getOrDefault(node.left, 0), gmap.getOrDefault(node.left, 0)) +
                Math.max(fmap.getOrDefault(node.right, 0), gmap.getOrDefault(node.right, 0));
        gmap.put(node, node_g_profile);
    }
}
