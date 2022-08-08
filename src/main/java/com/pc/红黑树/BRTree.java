package com.pc.红黑树;

import com.pc.平衡二叉树.FileOperation;

import java.util.ArrayList;

/**
 * 《算法导论》红黑树：
 * 1、每个节点不是红色就是黑色的
 * 2、根节点是黑色的
 * 3、每一个叶子节点（最后的空节点）是黑色的
 * 4、如果一个节点是红色的，那么它的孩子节点都是黑色的
 * 5、从任意一个节点到叶子节点，经过的黑色节点是一样的。
 *
 * 红黑树与2-3树的等价性：
 * 什么是2-3树？
 * 1、满足二分搜索树的基本性质。
 * 2、节点可以存放一个元素或者两个元素。
 *
 *     二节点         三节点
 *      a               b
 *     / \            / | \
 * 2-3树是一种绝对平衡的树（对任意一个节点，左右子树的高度是相等的）
 *
 * 2-3树是怎样维护绝对平衡的？
 * 2-3树添加元素过程：
 * 如果插入2-节点，直接融合
 *   ___               _____
 *  |12|  ---->插入6   |6 |12|
 *
 * 在根节点情况下：
 * 如果插入的节点是3-节点：
 *   ______                            ________
 *  |6 |12|   ---->插入2,先融合成4-节点 |2|6 |12|  ----->再分裂    2
 *                                                             /  \
 *                                                            6   12
 * 一般插入的节点不是根节点：
 *如果插入的节点是3-节点,父亲节点为2-节点：                                                        ____
 *       6                       6                           6                                |4|6|
 *    /    \                   /  \                        /   \                             / |  \
 *  _____       ---->插入4   2|4|5 12  ---> 4-节点分裂      4    12 --->平衡性打破,4向上融合    2  5   12
 *  |2|5|  12                                            /  \
 *                                                      2    5
 *如果插入的节点是3-节点,父亲节点为3-节点：
 *       ____                           _____                                         ________
 *      |6|8|                           |6|8|                                          |4|6|8|
 *    /   |  \  ---->插入元素4         /   |  \  --->将4-节点拆开，并向父亲节点融合      /\    | \
 *  ____                           _____                                            2 5    7 12
 * |2|5|  7   12                  |2|4|5| 7   12
 *
 *----->再次将4-节点拆开           6
 *                             /   \   ---->将以6为根的子树继续向上融合，如果6的父亲节点是2-节点，很简单直接融合即可，如果父亲节点是三节点，那么重复这个步骤，融合至根节点为止。
 *                            4     8
 *                          /  \   / \
 *                         2   5  7   12
 *
 *
 * 红黑树和2-3树的等价性：
 *    2-节点              3-节点
 *     ___                 ____
 *     |a|                |b|c|
 *    /   \               / | \
 *等价成的红黑树节点：
 *      a(黑)              c(黑)
 *     /   \              /    \
 *                      b(红)
 *                      /  \
 * 红黑树是保持“黑平衡”的二叉树
 * 严格意义上，不是平衡二叉树，最大高度2log(n)  O(logn)
 *
 * 红黑树和AVL树的比较。
 * 1、AVL树的查询更加迅速一点。
 * 2、存储的元素经常发生添加元素和删除元素，使用红黑树效率更高。综合来看，红黑树统计性能更佳。
 *
 * 红黑树添加新元素：
 * 2-3树添加一个新元素：
 * 或者添加进2-节点，形成一个3-节点
 * 或者添加进3-节点，暂时形成一个4-节点
 * 永远添加红色节点
 *
 * 基于红黑树的Map和Set
 * java 中的TreeMap和TreeSet基于红黑树。
 */
public class BRTree <K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        /**
         * 新增加的节点为什么是红色呢？
         * 在2-3树里面，添加节点不像二分搜索树一样，添加到树中为空的位置，
         * 2-3树是“融合”到一个节点中，而“红色”就代表了这种“融合关系”，代表这个节点需要与其它节点进行融合。
         * @param key
         * @param value
         */
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public BRTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断节点node的颜色
    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){

        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){

        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node){

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 向红黑树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK; // 最终根节点为黑色节点
    }

    // 向以node为根的红黑树中插入元素(key, value)，递归算法
    // 返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value); // 默认插入红色节点
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BRTree<String, Integer> map = new BRTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
