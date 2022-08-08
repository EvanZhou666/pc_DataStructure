package com.pc.LeetCode.题429.N叉树的层序遍历;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Node> queue = new LinkedList<>();
        queue.addLast(root);
        List<Node> t = new ArrayList<>();

        List<Integer> rootI = new ArrayList<>(1);
        rootI.add(root.val);
        result.add(rootI);

        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            if (node.children != null && !node.children.isEmpty()) {
                t.addAll(node.children);
            }
            if (queue.isEmpty()) {
                if (!t.isEmpty()) {
                    result.add(t.stream().map(n -> n.val).collect(Collectors.toList()));
                    queue.addAll(t);
                    t.clear();
                }
            }
        }
        return result;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
