package com.pc.线段树;

import java.util.LinkedList;

/**
 * 线段树：
 * 为什么要使用线段树？
 * 对于某个给定的区间：更新区间中的一个元素或者是一个区间的值
 * 查询一个区间【i，j】的最大值、最小值，或者区间数字和。
 * 线段树可能不是满二叉树，也可能不是完全二叉树
 * 线段树是平衡二叉树（最大深度和最小深度相差不超过1）
 *
 * 如果区间有n个元素，数组表示需要要4n个空间，我们的线段树不考虑添加元素。为什么?
 * 因为对于满二叉树，最后一层节点的数目等于前k层节点数加1。
 * 如果区间有n个元素 ：
 *         n=2的k次方，则需要分配2*n个空间，富余一个空间
 *         n=2的k次方加1，则需要分配4n个空间
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    private  static final  String newLine = "\r\n";
    public SegmentTree(E[] arr,Merger merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }
// 在treeindex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeindex,int l ,int r){
        if (l==r){
            tree[treeindex] = data[l];
            return;
        }
        int leftTreeIndex = leftTreeIndex(treeindex);
        int rightTreeIndex = rightTreeIndex(treeindex);
//        防止整型溢出，不直接int mid = (l+r)/2;
        int mid = l+(r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
//        综合 ，跟业务有关
        tree[treeindex] =merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
//        l,mid
//        mid+1,r;
    }

    private int rightTreeIndex(int treeindex) {
        return (treeindex<<1)+2;
    }

    private int leftTreeIndex(int treeindex) {
        return (treeindex<<1)+1;
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if (index<0 || index>=data.length){
            throw new IllegalArgumentException("索引下表越界");
        }
        return data[index];
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex , int l ,int r,int queryL,int queryR){
        if (l== queryL && r==queryR){
            return tree[treeIndex];
        }
        int mid = l+(r-l)/2;
        int leftTreeIndex = leftTreeIndex(treeIndex);
        int rightTreeIndex = rightTreeIndex(treeIndex);
        if (queryR<=mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        } else if (queryL>mid){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        } else {
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E ringhtResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
            return merger.merge(leftResult, ringhtResult);
        }
    }

//    返回区间[queryL,queryR]的值
    public E query(int queryL ,int queryR){
        System.out.println(String.format("查询线段数区间为[%s,%s]的值：",queryL,queryR));
        if (queryL<0||queryL>=data.length||queryR<0||queryR>=data.length||queryL>queryR)
            throw new IllegalArgumentException("索引下标不合法");
        return query(0,0,data.length-1,queryL,queryR);
    }

    public void set(int index , E e) {
        tree[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set (int treeIndex , int l ,int r, int index , E e) {
        if (l==r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l +(r-l)/2;
        int leftTreeIndex = leftTreeIndex(treeIndex);
        int rightTreeIndex = rightTreeIndex(treeIndex);
        if (index >= mid+1){
            set(rightTreeIndex,mid+1,r,index,e);
        } else {
            set(leftTreeIndex,l,mid,index,e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    private int parentIndex (int childIndex) {
        return (childIndex-1)/2;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i]!=null) res.append(tree[i]+" ");
            else res.append("null ");
//            i不在最后一个位置
            if (i!=tree.length-1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
    //    层序遍历线段树
    public void levelOrder(){
        LinkedList<Object> quque = new LinkedList();
        if (tree.length>0){
            quque.offer(tree[0]);
            quque.offer(newLine);
            doLevelOrder(quque,0);
        }
    }

    //    层序遍历：每次出队一个元素，便将该节点的左右孩子节点分别入队。
    public void doLevelOrder(LinkedList queue,int index){
        while (!queue.isEmpty()){
            E poll = (E)queue.poll();
            if (newLine.equals(poll)){
                System.out.print(poll);
                continue;
            }
            System.out.print(poll+" ");
            if (leftTreeIndex(index)<tree.length){
                queue.offer(tree[leftTreeIndex(index)]);
            }
            if (rightTreeIndex(index)<tree.length){
                queue.offer(tree[rightTreeIndex(index)]);
            }

//          如果是每一层的最后一个元素,入队换行符 每一层最后一个元素的索引满足:index = 2^n-2;
            int intValue = index==0?0:(int)(Math.log(index) / Math.log(2));
//            System.out.println("indexValue："+intValue);
            int verify = (2 << intValue);
            if (verify-2 == index){
                queue.offer(newLine);
            }
            index++;
        }
    }
}
