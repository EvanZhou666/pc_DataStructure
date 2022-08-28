package com.pc.LeetCode.题322;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/coin-change/
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 */
public class Solution {

    /**
     * 套用完全背包解题模板，执行效率比较低。这题不一定需要这么做，就是爬楼梯问题，
     * 通过 1200 ms	48.8 MB
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        // 同一面值的硬币可以无限使用，相当于考虑从n个物品中选取某些物品，每种物品都可以无限使用，使得物品的价值刚好等于背包的容量（amount）。
        // 典型的完全背包问题，由于每种背包容量固定，所以每种硬币实际上存在最大值，因此完全背包问题可以转换为0-1背包问题。
        // 转换过程：
        // [w0,w1,w2,w3... wn] 序列是物品的价值，为了更高效的利用空间，使用二进制思想，对于重量为w[i]的物品需要找到一个k值使得：
        // w[i] * 2^k <= amount，amount相当于是背包容量。根据k值，将重量为w[i]的无限物品转换为有限个数的物品序列[1,w[i]*2, w[i]*4,...， w[i]*k]
        // 将该序列添加到新的物品清单，同理处理其它的无限个数的物品w[n]，假如到新的序列中

        if (amount == 0) {
            return 0;
        }
        List<Integer> newWeight = new LinkedList<>();
        List<Integer> newValue = new LinkedList<>();
//        List<Integer> newValue = Arrays.asList(1,2,4,1,2);
        for (int coin : coins) {
            // 二进制思想：把第i种物品拆成重量为w[i]* 2^k、价值为2^k的若干件物品，其中k取遍满足w[i] *2^k ≤ amount 的非负整数。
            for (int ex = coin; ex <= amount; ex = 2 * ex) {
                newWeight.add(ex);
                newValue.add(ex / coin);
            }
        }

        System.out.println(newWeight);
        System.out.println(newValue);

        // 下面就是0-1背包的固定套路了
        // f(n,c) = 考虑选择n个物品使得，使得物品的总重量等于c，且物品的总价值最少。
        // 对于每个物品，都存在两种策略，选取和不选取
        // f(n,c) = min( f(n-1,c) 、 f(n-1,c-w[n]) + v[n])
        return oneZeroPackageDp(newWeight, newValue, amount);
    }

    private static int oneZeroPackageDp(List<Integer> weight, List<Integer> value, int C) {

        if (weight.isEmpty() || value.isEmpty()) {
            return -1;
        }
        int n = weight.size();
        // C+1是考虑容量为0的背包了
        int[][] dp = new int[n][C+1];

        // 初始化第0行，也就是物品0
        // dp[i][j] = 选取考虑[0,i]的物品使得物品的总重量等于j的时候，物品的最少总价值。
        for (int j = 0; j < dp[0].length; j++) {
            if (j == weight.get(0)) {
                dp[0][j] = value.get(0);
            } else {
                dp[0][j] = -1;
            }
        }

        dp[0][0] = 0;

        // 物品
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
//                f(n,c) = min( f(n-1,c) 、 f(n-1,c-w[n]) + v[n])
                if (j >= weight.get(i) && dp[i-1][j - weight.get(i)] != -1) {
                    if (dp[i - 1][j] != -1) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i-1][j - weight.get(i)] + value.get(i));
                    } else {
                        dp[i][j] = dp[i-1][j - weight.get(i)] + value.get(i);
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n-1][C];
    }

    public static void main(String[] args) {
        int ans ;
        ans = coinChange(new int[]{1}, 0);
        System.out.println(ans);
    }
}
