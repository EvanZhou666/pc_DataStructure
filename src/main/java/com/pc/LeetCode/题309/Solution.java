package com.pc.LeetCode.题309;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Solution {

    // 定义f(i)为第i天结束后持有股票能够获取的最大利润。
    // 定义g(i)为第i天结束后不持有股票并且不处于冷冻期，能够获得的最大利润。
    // 定义k(i)为第i天结束后不持有股票并且处于冷冻期，能够获得的最大利润。
    // 则：f(i) = max[ f(i-1)、g(i-1) - prices[i] ]
    //     g(i) = max[ g(i-1)、k(n-1) ]
    //     k(i) = f(i-1) + prices[i]
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[] dp_f = new int[prices.length];
        int[] dp_g = new int[prices.length];
        int[] dp_k = new int[prices.length];

        dp_f[0] = -prices[0];
        dp_g[0] = 0;
        dp_k[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            // f(i) = max(保存和前一天持有股票获得最大利润、昨天不持有股票今天买入股票获得最大利润)
            dp_f[i] =  Math.max(dp_f[i - 1],  dp_g[i - 1] - prices[i]);
            // 这里的冷冻期为什么是i-1 而不是dp_k[i] = dp_f[i - 2] + prices[i-2]呢？
            // 因为我们k(i)的定义是第i结束后的状态是冷冻期，比如我今天卖了股票，那么今天结束后就是处于冷冻期，明天就不能进行交易。
            // 所以如果我今天是冷冻期，那么就是我昨天还持有股票，今天卖了股票导致的，因此 k(i) = f(i-1) + prices[i]
            dp_k[i] = dp_f[i - 1] + prices[i];
            dp_g[i] = Math.max(dp_g[i - 1], dp_k[i - 1]);
        }
        return Math.max(dp_g[prices.length - 1], dp_k[prices.length - 1]);
    }

     private static void  printDpNums(int[] prices,int[] dp_f, int[] dp_g, Integer[] dp_k) {
         System.out.println("股票每日价格:" + Arrays.toString(prices));
         System.out.println("f(n)持有股票能够获得的最大利润:" + Arrays.toString(dp_f));
         System.out.println("g(n)不持有股票且不处于冷冻期能够获得的最大利润:" + Arrays.toString(dp_g));
         System.out.println("k(n)处于冷冻期且不持有股票能够获得最大利润" + Arrays.toString(dp_k));
         System.out.println();
     }
    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int ans ;

        ans = solution.maxProfit(new int[]{2,4,1,7});
        Assert.assertEquals(ans, 6);

        ans = solution.maxProfit(new int[]{1,2,3,0,2});
        Assert.assertEquals(ans, 3);

        ans = solution.maxProfit(new int[]{1,2,3});
        Assert.assertEquals(ans, 2);

        ans = solution.maxProfit(new int[]{3,2,1});
        Assert.assertEquals(ans, 0);

        ans = solution.maxProfit(new int[]{7,1,5,3,6,4});
        Assert.assertEquals(ans, 5);

        ans = solution.maxProfit(new int[]{1});
        Assert.assertEquals(ans, 0);
    }
}
