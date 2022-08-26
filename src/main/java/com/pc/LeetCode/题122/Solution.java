package com.pc.LeetCode.题122;

/**
 *
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a><br/>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 *
 *
 */
public class Solution {

    // 定义f(n)为第n天不持有股票能够获得的最大利润，
    // f(n) = max(g(n-1)+prices[n],f(n-1))
    // 定义g(n)为第n天持有股票能够获得的最大利润
    // g(n) = max(g(n-1)，f(n-1)-prices[n-1])
    public int maxProfit(int[] prices) {
        // dp_f[i] = f(i)
        int[] dp_f = new int[prices.length];
        int[] dp_g = new int[prices.length];

        dp_f[0] = 0;
        dp_g[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp_f[i] = Math.max(dp_g[i - 1] + prices[i], dp_f[i - 1]);
            dp_g[i] = Math.max(dp_g[i - 1], dp_f[i - 1] - prices[i]);
        }
        return dp_f[prices.length - 1];
    }

}
