package com.pc.LeetCode.题547.省份数量;

import com.pc.LeetCode.common.Uitls;
import com.pc.并查集.MyUnionFind;

public class Solution {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        MyUnionFind unionFind = new MyUnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getMaxConnectSize();
    }

    /**
     * 并查集
     */
    class UnionFind {

        private int[] parent;
        // 权重 实际上就是每个根节点下元素个数的大小
        private int[] weight;


        /**
         *
         * @param n 并查集容量大小
         */
        public UnionFind(int n) {
            parent = new int[n];
            weight = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                // 初始化权重值为1
                weight[i] = 1;
            }
        }

        /**
         * 查找x的"根节点"
         * @param x
         * @return
         */
        public int find (int x) {
            if (x == parent[x]) {
                return x;
            } else {
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }

        public void union(int p, int q) {
            int parentP = find(p);
            int parentQ = find(q);

            if (parentP == parentQ) {
                return;
            }

            if (weight[parentP] >= weight[parentQ]) {
                parent[parentQ] = parentP;
                weight[parentP] = weight[parentP] + weight[parentQ];
            } else {
                parent[parentP] = parentQ;
                weight[parentQ] = weight[parentQ] + weight[parentP];
            }

        }

        public int getConnectionCount() {
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == i) {
                    count ++;
                }
            }
            return count;
        }

    }

    public static void main(String[] args) {
//        String arrayJsonStr = "[[1,1,0,0,0,0,0,1,0,0,0,0,0,0,0],[1,1,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,1,0,1,1,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,0,0,1,1,0,0,0,0],[0,0,0,1,0,1,0,0,0,0,1,0,0,0,0],[0,0,0,1,0,0,1,0,1,0,0,0,0,1,0],[1,0,0,0,0,0,0,1,1,0,0,0,0,0,0],[0,0,0,0,0,0,1,1,1,0,0,0,0,1,0],[0,0,0,0,1,0,0,0,0,1,0,1,0,0,1],[0,0,0,0,1,1,0,0,0,0,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,1,0,1,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,1,0,0,0,0,1]]";
        String arrayJsonStr = "[[1,0,0,0,1,1,0,1,0,0,0,1,0,0,0],[0,1,0,0,1,0,1,0,0,0,0,0,0,0,0],[0,0,1,0,1,0,0,0,0,0,0,0,1,1,0],[0,0,0,1,0,0,0,0,0,0,0,1,1,1,0],[1,1,1,0,1,0,0,0,0,0,1,0,0,0,0],[1,0,0,0,0,1,0,0,0,1,0,0,0,0,0],[0,1,0,0,0,0,1,0,1,0,0,0,0,0,0],[1,0,0,0,0,0,0,1,0,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,1,0,0,0,0,0,1],[0,0,0,0,0,1,0,0,0,1,0,0,0,0,0],[0,0,0,0,1,0,0,0,0,0,1,0,0,1,0],[1,0,0,1,0,0,0,0,0,0,0,1,1,0,0],[0,0,1,1,0,0,0,0,0,0,0,1,1,0,0],[0,0,1,1,0,0,0,0,0,0,1,0,0,1,0],[0,0,0,0,0,0,0,0,1,0,0,0,0,0,1]]";
        int[][] board = Uitls.convertToInts(arrayJsonStr);
        Solution solution = new Solution();
        int nums = solution.findCircleNum(board);
        System.out.println(nums);
    }
}
