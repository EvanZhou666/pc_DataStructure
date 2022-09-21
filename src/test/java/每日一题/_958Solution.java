package 每日一题;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 958. 二叉树的完全性检验
 * 给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。
 *
 * 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含 1 到 2h 节点之间的最后一级 h 。
 */
public class _958Solution {

    static final TreeNode NULL = new TreeNode(-1);

    /**
     * 在一个完全二叉树中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含1到2h节点之间的最后一级 h 。
     * 判断是否是满二叉树套路。广度优先搜索
     *
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();

        queue.offer(root);

        List<TreeNode> list = new ArrayList<>();

        // 不应该有下一层的的标识
        boolean noNextLevel = false;

        while (!queue.isEmpty()) {

            // 当前层是否应该终止
            boolean shouldEnd = false;

            while (!queue.isEmpty()) {

                TreeNode node = queue.poll();

                if (shouldEnd && node != NULL) {
                    return false;
                }

                // 如果在当层遇到了我们虚拟的空节点，它后面就不应该有有效节点了
                if (node == NULL) {
                    shouldEnd = true;
                    noNextLevel = true;
                }

                // 只有有效节点才加入list 进行下一轮循环，否则会死循环
                if (node != NULL) {
                    list.add(node);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                TreeNode node = list.get(i);
                queue.offer(node.left == null ? NULL : node.left);
                queue.offer(node.right == null ? NULL : node.right);
            }

            // 如果标记为没有下一层了，但是队列里面还有真实节点，那么返回false。
            if (noNextLevel) {
                for (TreeNode treeNode : queue) {
                    if (treeNode != NULL) {
                        return false;
                    }
                }
            }

            list.clear();
        }
        return true;
    }

    public static void main(String[] args) {
        boolean b;
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root7 = new TreeNode(7);

        root.left = root2;
        root.right = root3;
        root2.left = root4;
        root2.right = root4;
        root2.right = root5;
        root3.right = root7;
        b = isCompleteTree(root);
        System.out.println(b);

//
//        TreeNode root = new TreeNode(1);
//        TreeNode root2 = new TreeNode(2);
//        TreeNode root3 = new TreeNode(3);
//        TreeNode root4 = new TreeNode(4);
//        TreeNode root5 = new TreeNode(5);
//        TreeNode root6 = new TreeNode(6);
//
//        root.left = root2;
//        root.right = root3;
//        root2.left = root4;
//        root2.right = root5;
//        root3.left = root6;
//        b = isCompleteTree(root);
//        System.out.println(b);
    }

}
