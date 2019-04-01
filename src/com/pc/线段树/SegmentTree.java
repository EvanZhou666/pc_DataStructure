package com.pc.线段树;

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
        return (treeindex<<1)+1;
    }

    private int leftTreeIndex(int treeindex) {
        return (treeindex<<1)+2;
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

    private E query(int treeIndex , int l ,int r){

        return null;
    }

//    返回区间[queryL,queryR]的值
    public E query(int queryL ,int queryR){
        if (queryL<0||queryL>=data.length||queryR<0||queryR>=data.length||queryL>queryR)
            throw new IllegalArgumentException("索引下标不合法");

        return null;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i]!=null) res.append(tree[i]+" ");
            else res.append("null ");
//            i不在最后一个位置
//            if (i!=tree.length-)
            
        }

        return res.toString();
    }
}
