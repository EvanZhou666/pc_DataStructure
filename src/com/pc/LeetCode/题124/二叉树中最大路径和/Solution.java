package com.pc.LeetCode.题124.二叉树中最大路径和;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题关键：
 * 从任意节点出发，达到任意另一个节点，每个节点不能经过第二次
 * 考虑每个节点的最大贡献值，经过每个节点的最大贡献值等于该节点的值或者(该节点的值+max(左节点的最大贡献值，右节点的最大贡献值)
 * "转折节点"：假如该路径是最大的，那么左右子节点都在路径中的节点称之为"转折节点"，很明显，转折节点至多存在一个
 *                      5
 *                   /    \
 *                  4      8
 *                /  \    /  \
 *               7    2  13   4
 *                            \
 *                             1
 *
 * 假如路径和最大的路径是  7-->4-->2 那么"转折节点"就是 4
 * 假如路径和最大的路径是  7-->4-->5-->8 那么"转折节点"就是 5
 * 假如路径和最大的路径是  7-->4-->5 那么"转折节点"视作不存在
 * 假如路径和最大的路径是  7-->4-->5-->8-->13 那么"转折节点"是5
 * 假如路径和最大的路径是  13-->8-->4-->1 那么"转折节点"是8
 * 不要求计算最大路径是怎么走的，因此我们只需要使用一个全局变量，保存最大值
 * 每经过一个节点，比较全局的最大值和该节点的最大贡献值，和假设该节点是"转折节点"对应的路径和
 */
public class Solution {
    static ThreadLocal<Integer> cache = new ThreadLocal<>();
    public static int maxPathSum(TreeNode root) {
        try {
            cache.set(Integer.MIN_VALUE);
            maxContribution(root);
            return cache.get();
        } finally {
            cache.remove();
        }
    }

    /**
     * 计算节点的最大“贡献值”
     * @param root
     * @return
     */
    public static int maxContribution (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxContribution(root.left);
        int right = maxContribution(root.right);
        int maxContribution;
        if (left<0 && right<0) {
            maxContribution = root.val;
        } else {
            maxContribution = Math.max(left,right)+root.val;
        }
        // 最大贡献值，是否大于最大值
        if (maxContribution>cache.get()) {
            cache.set(maxContribution);
        }

        // 假设该节点是“转折节点”，也就是说路径经过了改节点的左右子树
        if (left+ right+maxContribution > cache.get()) {
            cache.set(left+ right+maxContribution );
        }

        return maxContribution;
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
