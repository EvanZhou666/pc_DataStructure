package com.pc.LeetCode.题449.序列化和反序列化二叉搜索树;

import com.pc.LeetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 *449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 * 解题的关键：注意是二叉搜索树，记住二叉搜素树的特性。本题的进阶版本是不考虑二叉搜索数
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder nodeBuilder = new StringBuilder();
        if (root == null) {
            return nodeBuilder.toString();
        }
        serializeHelper(nodeBuilder,root);
        return nodeBuilder.substring(1);

    }

    public void serializeHelper(StringBuilder builder,TreeNode root) {
        if (root == null) {
            return;
        }
        builder.append(",").append(root.val);
        serializeHelper(builder,root.left);
        serializeHelper(builder,root.right);
    }

    public TreeNode deserializeHelper(Integer lowest,Integer upper,Deque<Integer> queue) {

        if (queue.isEmpty()) {
            return null;
        }

        Integer poll = queue.peek();
        if (poll < lowest || poll > upper) {
            return null;
        }
        queue.removeFirst();

        TreeNode root =  new TreeNode(poll);

        root.left = deserializeHelper(lowest,poll,queue);
        root.right = deserializeHelper(poll,upper,queue);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        Deque<Integer> queue = new LinkedList<>();
        for (String s : array) {
            if (!"".equals(s)) {
                queue.addLast(Integer.valueOf(s));
            }
        }
        return deserializeHelper(Integer.MIN_VALUE,Integer.MAX_VALUE,queue);
    }

    public static void main(String[] args) {
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(listOfElecments(8));
        ll.add(listOfElecments( 3,9));
        ll.add(listOfElecments(null,4,10, null));
        ll.add(listOfElecments(null,null,null,null,null,11,null,null));
        TreeNode root = conver2Tree(ll);
        //        TreeNode.levelOrder(root);
        Codec codec = new Codec();
        System.out.println(codec.serialize(root));
        TreeNode deserialize = codec.deserialize(codec.serialize(root));
        TreeNode.levelOrder(deserialize);

    }
}
