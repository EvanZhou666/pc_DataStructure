package com.pc.并查集;

import java.util.Arrays;

/**
 * <p>我的并查集实现</p>
 * <img src="https://pic.leetcode-cn.com/1651390631-bkjtnm-123.png">如何合并两个节点</img>
 * <a href="https://blog.csdn.net/weixin_38279101/article/details/112546053">什么是并查集</>
 * 并查集解决的是元素的分类问题，诸如查找元素属于哪一个分类中，最大的分类数目是
 */
public class MyUnionFind {

    // 记录每个节点的根节点,nodes[i] 存储的是"祖先"（有可能是父亲节点、也有可能是爷爷、外祖）节点的索引位置，表示索引i的祖先是nodes[i]
    private int[] nodes;
    // 记录每棵树的权重。weight[i] 表示以i为根节点的数一共有weight[i]个节点。
    private int[] weight;

    public MyUnionFind(int n) {
        nodes = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            // 初始化每个节点的父亲节点为自己。
            nodes[i] = i;
            weight[i] = 1;
        }
    }

    /**
     * Find -- 查找所属的连通分量
     * @param x
     * @return
     */
    private int find(int x) {
        // 如果自己就是就是的祖先，也就说自己就是"根节点",直接返回
        if (x == nodes[x]) {
            return x;
        } else {
            // 递归查找x的爸爸的“根节点”
//            return find(nodes[x]);
            // 路径压缩,使用递归的写法直接把“链表拉平”
             nodes[x] = find(nodes[x]);
             return nodes[x];
        }
    }

    /**
     * Union -- 连接两个节点
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pFind = find(p);
        int qFind = find(q);
        // 如果p和q的“祖先”节点相同，那么没有必要再合并了
        if (pFind ==  qFind) {
            return;
        }

        // 平衡优化并查集，主要思想节点多的根节点兼并节点少的根节点，防止树的深度不断扩张，那么find的时候会增加递归的复杂度
        if (weight[pFind] >= weight[qFind]) {
            nodes[qFind] = pFind;
            weight[pFind] = weight[pFind] + weight[qFind];
        } else {
            nodes[pFind] = qFind;
            weight[qFind] = weight[qFind] + weight[pFind];
        }

    }

    private void printUnionFind(){
        System.out.println("nodes:" + Arrays.toString(nodes));
        System.out.println("weight:" + Arrays.toString(weight));

    }

    // 获取最大连通分量
    public int getMaxConnectSize() {
        int maxConnectSize = 0;
        for (int i = 0; i < weight.length; i++) {
            if (i == nodes[i]) {
                maxConnectSize = Math.max(maxConnectSize, weight[i]);
            }
        }
        return maxConnectSize;
    }

    /**
     * <img src="./img.png">myUnionFind.find(1)之前</img>
     * 如果开启了路径压缩，那么执行myUnionFind.find(1)之后。非根节点的权重就是错误的了。
     * <img src="./img_1.png">myUnionFind.find(1)之后</img>
     * 执行myUnionFind.find(1)之后，原来的0->1链表直接被“拉平”了，这个时候0的权重值实际上是不正确的了。
     *
     * @param args
     */
    public static void main(String[] args) {
        MyUnionFind myUnionFind = new MyUnionFind(5);
        myUnionFind.union(3,4);
        myUnionFind.union(2,3);
        myUnionFind.union(0,1);
        myUnionFind.union(0,3);
        myUnionFind.printUnionFind();
        int ans = myUnionFind.find(1);
//        ans = myUnionFind.find(3);
        System.out.println(ans);
        myUnionFind.printUnionFind();
    }


}
