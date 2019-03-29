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
     * 递归插入节点 实现方式一 比较挫...
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

//    二分搜索树中插入节点 实现方式2
    public void add2(E e){
        root = add2(new Node(e),root);
    }

//    在以root为根的子树上插入节点，并且返回新的子树（一个Node本身就可以看做是一棵树）
    private Node add2(Node insert ,Node root){
        if (root==null){
            return insert;
        }
        if (insert.data.compareTo(root.data)>0){
            //挂接新的子树到右节点上
            root.right = add2(insert,root.right);
        } else {
            //挂接新的子树到左节点上
            root.left = add2(insert,root.left);
        }
        return root;
    }

//    查找元素
    public boolean contains(E e) {
       return findE(e,root);
    }

    public boolean findE(E e,Node root) {
        if (root ==null) {
            return false;
        }
        if (e.compareTo(root.data)>0) {
            return findE(e,root.right);
        } else if(e.compareTo(root.data)<0) {
            return findE(e,root.left);
        } else {
            return true;
        }
    }

//    查找最小的元素 因为最最小的元素一定在左子树的叶子节点上，因此递归的终止条件是root.left==null
    public E findMix (){
        if (root ==null) { throw new UnsupportedOperationException("不支持改操作"); }
        return dofindMix(root).data;
    }

    private Node dofindMix(Node root) {
        if (root.left==null){
            return root;
        }
        return dofindMix(root.left);
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
      /*  ebst.add(8);
        ebst.add(7);
        ebst.add(6);
        ebst.add(100);
        ebst.add(88);*/
        ebst.add2(8);
        ebst.add2(7);
        ebst.add2(6);
        ebst.add2(100);
        ebst.add2(88);
        System.out.println(ebst.contains(100));
        System.out.println(ebst.contains(101));
        System.out.println(ebst.contains(88));
        System.out.println(String.format("最小的节点是%s",ebst.findMix()));

    }
}
