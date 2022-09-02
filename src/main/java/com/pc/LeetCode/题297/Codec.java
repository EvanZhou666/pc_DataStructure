package com.pc.LeetCode.题297;

import com.pc.LeetCode.common.TreeNode;

import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 */
public class Codec {

    /**
     * bfs广度优先搜索，注意审题，不要求按照给出的输入格式进行序列话。（官方题解都没按照输入格式进行序列化的！！！！）
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<Object> queue = new ArrayDeque<>();

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Object pop = queue.pop();
                if (pop instanceof String) {
                    sb.append(",").append((String) pop);
                } else {
                    TreeNode node = (TreeNode) pop;
                    sb.append(",").append(node.val);
                    if (isLeaf(node)) {
                        queue.offer("null");
                        queue.offer("null");
                    } else {
                        if (node.left != null) {
                            queue.offer(node.left);
                        } else {
                            queue.offer("null");
                        }

                        if (node.right != null) {
                            queue.offer(node.right);
                        } else {
                            queue.offer("null");
                        }
                    }
                }
            }
        }

        String preHandleStr = sb.toString();
        if (!preHandleStr.isEmpty()) {
            preHandleStr = sb.substring(1);
        }
        preHandleStr = "[" + preHandleStr + "]";
        System.out.println(preHandleStr);
        return preHandleStr;
    }

    private static boolean isLeaf(TreeNode treeNode) {
        return treeNode != null && treeNode.left == null && treeNode.right == null;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {

        // 预先处理数据，转换成我想要的TreeNode list
        if ("".equals(data) || "[]".equals(data)) {
            return null;
        }
        data = data.substring(1, data.length() - 1);

        String[] split = data.split(",");
        List<Object> list = new ArrayList<>(split.length);
        for (String s : split) {
            if ("null".equals(s)) {
                list.add(null);
            } else {
                list.add(new TreeNode(Integer.parseInt(s)));
            }
        }
        Deque<Object> deque = new ArrayDeque<>();
        TreeNode root = (TreeNode) list.get(0);
        deque.offer(root);

        for (int i = 1; i < list.size(); i = i + 2) {
            Object parent = deque.pop();
            if (parent instanceof TreeNode) {
                Object left = list.get(i);
                Object right = list.get(i + 1);
                if (left instanceof TreeNode) {
                    ((TreeNode) parent).left = (TreeNode) left;
                    deque.offer(left);
                }
                if (right instanceof TreeNode) {
                    ((TreeNode) parent).right = (TreeNode) right;
                    deque.offer(right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//        root.left = treeNode2;
//        root.right = treeNode3;
//        treeNode3.left = treeNode4;
//        treeNode3.right = treeNode5;


        TreeNode root = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(-7);
        TreeNode treeNode3 = new TreeNode(-3);
        TreeNode treeNode4 = new TreeNode(-9);
        TreeNode treeNode5 = new TreeNode(-3);
        TreeNode treeNode6 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(-7);
        TreeNode treeNode8 = new TreeNode(-4);
        TreeNode treeNode9 = new TreeNode(6);
        TreeNode treeNode10 = new TreeNode(-6);
        TreeNode treeNode11 = new TreeNode(-6);
        TreeNode treeNode12 = new TreeNode(0);
        TreeNode treeNode13 = new TreeNode(6);
        TreeNode treeNode14 = new TreeNode(5);
        TreeNode treeNode15 = new TreeNode(9);
        TreeNode treeNode16 = new TreeNode(-1);
        TreeNode treeNode17 = new TreeNode(-4);
        TreeNode treeNode18 = new TreeNode(-2);

        root.left = treeNode2;
        root.right = treeNode3;

        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        treeNode4.left = treeNode6;
        treeNode4.right = treeNode7;

        treeNode5.left = treeNode8;

        treeNode6.left = treeNode9;
        treeNode7.left = treeNode10;
        treeNode7.right = treeNode11;

        treeNode9.left = treeNode12;
        treeNode9.right = treeNode13;

        treeNode10.left = treeNode14;
        treeNode11.left = treeNode15;
        treeNode12.right = treeNode16;
        treeNode13.left = treeNode17;
        treeNode15.left = treeNode18;


        String seial = serialize(root);
        TreeNode deserialize = deserialize(seial);
        serialize(deserialize);

//        [1,2,3,4,5,6,7,8,null,null,9,10,null,null,null,null,null,null,null,11]
    }
}
