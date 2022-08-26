package com.pc.背包问题;

/**
 * 0-1背包问题
 * 有一个背包，它的容量为C。现在有n种不同的物品，编号为0...n-1，
 * 其中每意见物品的重量为w(i),价值为v(i)。问可以向这个背包中盛放哪些物品，使得在不超过背包容量的基础上，
 * 物品的总价值最大。
 */
public class PackageZeroOneSolution {

    public static int knapsack01(int[] weight, int[] v, int C) {
        return calMaxValue(weight, v, v.length - 1, C);
    }

    /**
     * 递归求解怎么放置n个物品使得背包内物品总价值最大
     * @param weight 物品重量
     * @param v 价值
     * @param n 物品总数
     * @param c 背包容量，能够承受的物品总重量
     */
    private static int calMaxValue(int[] weight, int[] v, int n, int c) {
        // 终止条件：如果背包容量耗尽或者物品放置完成，
        if (c < 0 || n < 0) {
            return 0;
        }

        // 不将第n个物品放入背包的最大价值
        int res = calMaxValue(weight, v, n-1, c);

        // 将第n个物品放入背包的最大价值
        if (c - weight[n] < 0) { // 背包容量不够放入第n个物品，return
            return res;
        }
        res = Math.max(res, calMaxValue(weight, v, n-1, c - weight[n]) + v[n]);

        return res;
    }

    public static void main(String[] args) {
        int ans = knapsack01(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5);
        System.out.println(ans);
    }
}
