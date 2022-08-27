package com.pc.背包问题;

import com.pc.LeetCode.common.Assert;

/**
 * 0-1背包问题
 * 有一个背包，它的容量为C。现在有n种不同的物品，编号为0...n-1，
 * 其中每意见物品的重量为w(i),价值为v(i)。问可以向这个背包中盛放哪些物品，使得在不超过背包容量的基础上，
 * 物品的总价值最大。
 */
public class PackageZeroOneSolution {

    /**
     * 计算0-1背包问题，求解在容量为C的情况下，背包所放物品的最大价值。
     * 定义f(n,C)为在容量为C的情况下，考虑将n个物品放入背包，产生的最大价值。则：
     * 对于第n个物品有两种策略：
     * 1.不考虑把第n个物品放入到背包中，直接考虑把前n-1个物品放入背包的产生的价值 = f(n-1,C)
     * 2.考虑把第n个物品放入到背包中，再考虑把前n-1个物品放入背包的产生的价值，因此这种策略的价值= 第n个物品的价值 + 前n-1个物品的价值 = v[n] + f(n-1, C- weight[n])
     * 因此。考虑将n个物品放入背包，产生的最大价值 ：f(n,C) = max [ f(n-1,C)、 weight[n] ]
     * @param weight
     * @param v
     * @param C
     * @return
     */
    public static int knapsack01(int[] weight, int[] v, int C) {
//        return calMaxValueRecursive(weight, v, v.length - 1, C);
        return calMaxValueDp(weight, v, C);
    }

    /**
     * 递归求解怎么放置n个物品使得背包内物品总价值最大
     * @param weight 物品重量
     * @param v 价值
     * @param n 物品总数
     * @param c 背包容量，能够承受的物品总重量
     */
    private static int calMaxValueRecursive(int[] weight, int[] v, int n, int c) {
        // 终止条件：如果背包容量耗尽或者物品放置完成，
        if (c < 0 || n < 0) {
            return 0;
        }

        // 不将第n个物品放入背包的最大价值
        int res = calMaxValueRecursive(weight, v, n-1, c);

        // 将第n个物品放入背包的最大价值
        if (c - weight[n] < 0) { // 背包容量不够放入第n个物品，return
            return res;
        }
        res = Math.max(res, calMaxValueRecursive(weight, v, n-1, c - weight[n]) + v[n]);

        return res;
    }

    /**
     * 使用动态规划思想求解<br/>
     * <img src="0-1背包问题.png"/> <br/>
     * @param weight
     * @param v
     * @param C
     * @return
     */
    private static int calMaxValueDp(int[] weight, int[] v, int C) {
        int n = weight.length; // 物品数目
        int[][] dp = new int[n][C+1];

        // 初始化第0行数据
        for (int j = 0; j < dp[0].length; j++) {
            if (j >= weight[0]) {
                dp[0][j] = v[0];
            }
        }

        // 遍历物品
        for (int i = 1; i < n; i++) {
            // 访问先前背包容量
            for (int j = 0; j < C + 1; j++) {
                // 不放入第i个物品时的时候最大价值
                dp[i][j] = dp[i - 1][j];
                // 减去第i个物品后的背包容量
                if (j - weight[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + v[i]);
                }
            }
        }

        return dp[n - 1][C];
    }

    public static void main(String[] args) {
        int ans = knapsack01(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5);
        System.out.println(ans);
        Assert.assertEquals(ans, 22);
    }
}
