package com.pc.二分搜索树;

/**
 * 平衡二叉树实现
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private Node root = null;
    private int size;

    public BST() {
        root =null;
        size =0;
    }
    public BST(Node root){
        this.root =root;
        size = 1;
    }

    public int size(){
        return size;
    }

    /**
     * 递归插入节点
     * @param e
     */
    public void add(E e) {
        add(new Node(e),root);
    }

    private void add(Node insert ,Node nextroot){
        if (root==null){
            root = insert;
            size++;
            return;
        }
        if (insert.data.compareTo(root.data)>0) {
            addInRight(insert,root.right,root);
        }else {
            addInLeft(insert,root.left,root);
        }


    }

    private void addInRight(Node insert, Node right, Node root) {
        if (right==null){
            root.right = insert;
            size++;
            return;
        }
        if (insert.data.compareTo(right.data)>0) {
            addInRight(insert,right.right,right);
        } else {
            addInLeft(insert,right.left,right);
        }
    }

    private void addInLeft(Node insert, Node left, Node root) {
        if (left==null){
            root.left = insert;
            size++;
            return;
        }
        if (insert.data.compareTo(left.data)>0) {
            addInRight(insert,left.right,left);
        } else {
            addInLeft(insert,left.left,left);
        }
    }

    private class Node {
        private E data;
        Node left,right;
        public Node(){}
        public Node(E e){
            data = e;
        }
    }

    public static void main(String[] args) {
        BST<Integer> ebst = new BST<>();
        ebst.add(8);
        ebst.add(7);
        ebst.add(6);
//        ebst.add(10);
    }
}
