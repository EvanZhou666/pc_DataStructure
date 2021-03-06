package com.pc.平衡二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 平衡二叉树定义：对于任意节点，它的左右子树的高度差相差不过1；
 * 这个定义更加宽松些，更加严格是:对于任意叶子节点，它们之间的高度相差不过1，完全二叉树、线段树就是严格意义下的平衡二叉树
 * 平衡因子： 左右子树的高度差。
 */
public class AVL<K extends Comparable<K>,V> {
    private Node root;
    private int size;


    public AVL() {

        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 获得节点node的高度
    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add (K key ,V value) {
        Node node = new Node(key, value);
        root = add(root,node);
    }

    private Node add(Node root ,Node node) {
        if (root == null) {
            size++;
            return node;
        }

        Node retNode;
        if (node.key.compareTo(root.key)>0) {
            root.right = add(root.right, node);
            retNode = root;

        } else if (node.key.compareTo(root.key)<0){
            root.left = add(root.left,node);
            retNode = root;
        } else {
            root.value = node.value;
            retNode = root;
        }
        retNode.height =Math.max(getHeight(retNode.left),getHeight(retNode.right))+1;
//        注意判断失衡形态的条件，包括边界值，以及getBalanceFactor函数里的节点是谁。
//        LL：右旋
        if (getBalanceFactor(retNode)>1&&getBalanceFactor(retNode.left)>=0){
            retNode = rightRotate(retNode);
        } else if ((getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) <= 0)) {
            retNode = leftRotate(retNode);
        }
        /**
         *  n1 、n2 代表是一颗子树，当然一个节点也可以看做一颗子树，新插入的接点位于n2子树上
         *        r                             r
         *      /   \          左旋           /   \
         *     x     n1       ----->        y     n1
         *    /                           /  \
         *   y                           x    n2
         *    \
         *     n2
         *
         */
//        LR:先左旋转，再右旋转。
        else if ((getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) < 0)){
            retNode.left = leftRotate(retNode.left);//旋转之后一定要再次接住
            retNode = rightRotate(retNode);
        }

//        RL:先右旋转，再左旋转。
        else if ((getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) > 0)) {
            retNode.right = rightRotate(retNode.right); //旋转之后一定要再次接住
            retNode = leftRotate(retNode);
        } else{

        }

        return retNode;
    }

    public boolean isAVLTree() {
        List list = new ArrayList();
        return isAVLTree(root,list);
    }
    private boolean isAVLTree (Node root,List list) {
        if (root ==null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(root))>1) {
            return false;
        }
        if (root.left!=null && root.left.key.compareTo(root.key)>0) {
            return false;
        } else if (root.right!=null && root.right.key.compareTo(root.key)<0){
            return false;
        }

        boolean avlleft = isAVLTree(root.left, list);
        boolean avlright = isAVLTree(root.right, list);
        return avlleft && avlright;


    }


    /**
     * 左旋操作: 注意旋转的是哪一个节点
     *        r                         x
     *        \           r左旋        /  \
     *        x           ----->     r    y
     *       / \                     \     \
     *      n1  y                     n1    n2
     *          \
     *           n2
     * 旋转一次，不忘维护节点的高度
     */
    private Node leftRotate (Node root) {
        Node x = root.right;
        Node n1 = x.left;
        x.left = root;
        root.right = n1;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * //右旋操作: 新插入的节点位于y的左子树上
     *        r                           x
     *      /  \         r右旋           /  \
     *     x    n1        ----->        y   r
     *    / \                         /    / \
     *   y   n2                      n3   n2  n1
     *  /
     * n3
     *
     * 旋转一次，不忘维护节点的高度
     */
    private Node rightRotate (Node root) {
        Node x = root.left;
        Node n2 = x.right;
        x.right = root;
        root.left = n2;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //    删除元素
    public void remove(K key) {
        root = doRemove(key,root);

    }

    private Node doRemove(K e, Node root) {
        if (root == null) {
            return null;
        }
        Node retNode = null;
        if (e.compareTo(root.key) == 0) {
//            如果是叶子节点 直接删除
            if (root.left == null && root.right == null) {
                size--;
                return null;
            }
//           如果左孩子节点或者是右孩子节点不为空
            else if ((root.left == null) ^ (root.right == null)) {
                size--;
                retNode = root.left == null ? root.right : root.left;
                if (root.left == null) {
                    root.right = null;
                } else {
                    root.left = null;
                }
            }
//           如果左右子树均不为空，从该节点的右子树中找一个最小的元素(因为该元素大小最接近要被删除的元素，而且一定没有左孩子)，替换该节点。
            else if (root.left != null && root.right != null) {
                Node minInSubTree = dofindMin(root.right);
//                System.out.println("右子树中最小元素是："+minInSubTree.data);
//                root.value = minInSubTree.value;
                minInSubTree.right = doRemove(minInSubTree.key, root.right);
                minInSubTree.left = root.left;
                root.left = root.right = null;
                retNode = minInSubTree;
            }
        }

        else if (e.compareTo(root.key) > 0) {
            root.right = doRemove(e, root.right);
            retNode = root;
        }
        else if (e.compareTo(root.key) < 0) {
            root.left = doRemove(e, root.left);
            retNode = root;
        }
//        if (retNode == null) return null;
        retNode.height =Math.max(getHeight(retNode.left),getHeight(retNode.right))+1;
//        LL：右旋
        if (getBalanceFactor(retNode)>1&&getBalanceFactor(retNode.left)>=0){
            retNode = rightRotate(retNode);
        }
//        RR：左旋
        else if ((getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) <=0)) {
            retNode = leftRotate(retNode);
        }
//        LR：
        else if ((getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) < 0)){
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }

//        RL:先右旋转，再左旋转。
        else if ((getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) > 0)) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }

        return retNode;
    }

    private Node dofindMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return dofindMin(node.left);
    }

    //    层序遍历二分搜索树
    public void levelOrder(){
        LinkedList<Node> quque = new LinkedList();
        if (root!=null&&size>0){
            quque.offer(root);
            doLevelOrder(quque);
        }
    }

    /**
     * AVL树层序遍历
     * @param queue
     */
    public void doLevelOrder(LinkedList queue){
        while (!queue.isEmpty()){
            Node poll = (Node)queue.poll();
            System.out.println(poll.key+"height:"+poll.height);
            if (poll.left!=null){
                queue.offer(poll.left);
            }
            if (poll.right!=null){
                queue.offer(poll.right);
            }
        }
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

    private class Node {
        private Node left;
        private Node right;
        private K key;
        private V value;
        private int height;

        public Node(){ height = 1; }
        public Node(K key ,V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    public static void main(String[] args) {
        AVL<Integer, Object> avl = new AVL<>();
        avl.add(5,null);
        avl.add(7,null);
        avl.add(9,null);
        avl.add(10,null);
        avl.add(11,null);
        avl.remove(11);
        System.out.println("isAVLTree:"+avl.isAVLTree());
        avl.levelOrder();


    }
}
