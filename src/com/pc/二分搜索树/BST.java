package com.pc.二分搜索树;

import com.pc.栈.Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

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
            size++;
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

//    查找二分搜索树中最大的元素
    public E findMax() {
        if (root==null){throw new UnsupportedOperationException("空链表不支持查找最大元素");}
        return doFindMax(root).data;
    }

//    二分搜索树前序遍历
    public void preOrder() {
        doPreOrder(root);

    }

    private void doPreOrder(Node node){
        if (node ==null){
            return;
        }
        System.out.println(node.data);
        doPreOrder(node.left);
        doPreOrder(node.right);
    }

//    二分搜索树中序遍历
    public void midOrder(){
        doMidOrder(root);
    }

    private void doMidOrder(Node root){
//        循环终止的条件：某一子节点为空时返回
        if (root==null){
            return;
        }
        doMidOrder(root.left);
        System.out.println(root.data);
        doMidOrder(root.right);
    }

    private Node doFindMax(Node root) {
        if (root.right ==null){
            return root;
        }
        return doFindMax(root.right);
    }

    public void postOrder(){
        doPostOrder(root);
    }

    private void doPostOrder(Node root){
        if (root==null){
            return;
        }
        doPostOrder(root.left);
        doPostOrder(root.right);
        System.out.println(root.data);
    }

//    删除元素
    public void remove(E e) {
        doRemove(e,root);

    }

    private Node doRemove(E e ,Node root){
        if (root==null){
            return null;
        }
        if (e.compareTo(root.data)==0){
//            如果是叶子节点 直接删除
            if (root.left==null&&root.right==null){
                size--;
                return null;
            }
//            如果左右子树均不为空，从该节点的右子树中找一个最小的元素(因为该元素大小最接近要被删除的元素，而且一定没有左孩子)，替换该节点。
            if(root.left!=null&&root.right!=null){
                Node mixInSubTree = dofindMix(root.right);
//                System.out.println("右子树中最小元素是："+mixInSubTree.data);
                root.data = mixInSubTree.data;
                root.right = doRemove(mixInSubTree.data,root.right);
                return root;
            } else {
//                如果左孩子节点或者是右孩子节点不为空
                size--;
                return root.left==null?root.right:root.left;
            }
        }

        if (e.compareTo(root.data)>0){
            root.right = doRemove(e,root.right);
        }else if (e.compareTo(root.data)<0){
            root.left = doRemove(e,root.left);
        }
        return root;
    }

//  非递归的方式进行前序遍历
    public void preOrderNonRecursion(){
        LinkedList<Node> stack = new LinkedList<>();
        if (size()>0){
            stack.push(root);
            while (!stack.isEmpty()){
                Node pop = stack.pop();
                System.out.println(pop.data);
                if (pop.right!=null){
                    stack.push(pop.right);
                }
                if (pop.left!=null){
                    stack.push(pop.left);
                }
            }
        }

    }

//    层序遍历二分搜索树
    public void levelOrder(){
        LinkedList<Node> quque = new LinkedList();
        if (root!=null&&size>0){
            quque.offer(root);
            doLevelOrder(quque);
        }
    }

//    层序遍历：每次出队一个元素，便将该节点的左右孩子节点分别入队。
    public void doLevelOrder(LinkedList queue){
        while (!queue.isEmpty()){
            Node poll = (Node)queue.poll();
            System.out.println(poll.data);
            if (poll.left!=null){
                queue.offer(poll.left);
            }
            if (poll.right!=null){
                queue.offer(poll.right);
            }
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
      /*  ebst.add(8);
        ebst.add(7);
        ebst.add(6);
        ebst.add(100);
        ebst.add(88);*/
       /* ebst.add2(8);
        ebst.add2(7);
        ebst.add2(6);
        ebst.add2(50);
        ebst.add2(100);
        ebst.add2(88);
        System.out.println(ebst.contains(100));
        System.out.println(ebst.contains(101));
        System.out.println(ebst.contains(88));
        System.out.println(String.format("最小的节点是%s",ebst.findMix()));
        System.out.println(String.format("最大的节点是%s",ebst.findMax()));
        ebst.preOrder();
        System.out.println();
        ebst.midOrder();
        System.out.println();
        ebst.postOrder();
        System.out.println();
        ebst.preOrderNonRecursion();
        System.out.println("层序遍历：");
        ebst.levelOrder();
        ebst.remove(8);
        System.out.println("删除元素层序遍历结果：");
        ebst.levelOrder();*/
       int[] nums= new int[10];
       for (int i =0;i<10;i++){
           int rand = new Random().nextInt(1000);
           nums[i]=rand;
           ebst.add(rand);
       }
        System.out.println(ebst.size());
        System.out.println("nums:"+Arrays.toString(nums));
        ebst.midOrder();
        System.out.println("删除奇数项");
        for (int i=0;i<10;i++){
           if (i%2==1){
               ebst.remove(nums[i]);
           }
        }
        ebst.midOrder();
        System.out.println("size:"+ebst.size);


    }
}
