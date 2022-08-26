package com.pc.LeetCode.题123;

import java.util.Arrays;

/**
 * <img src="./img.png"/>
 */
public class Solution {
    // 定义f(i,k)为第i天结束后持有股票,并且已经买入k次 能够获取的最大利润。
    // 定义g(i,k)为第i天结束后不持有股票，并且已经卖出过k次，能够获得的最大利润。
    // 则：f(i,k) = max[ f(i-1, k)、g(i-1,k-1) - prices[i] ]
    //     g(i,k) = max[ g(i-1, k)、f(i-1,k) + prices[i] ]
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[][] dp_f = new int[prices.length][3];
        int[][] dp_g = new int[prices.length][3];

        for (int i = 0; i < prices.length; i++) {
            for (int k = 0; k < 3; k++) {
                dp_f[i][k] = Integer.MIN_VALUE;
                dp_g[i][k] = Integer.MIN_VALUE;
            }
        }


        dp_f[0][0] = 0; // 第0天结束后持有0支股票，买入过0次，显然最大利润是0
        dp_f[0][1] = -prices[0]; // 第0天结束后持有1支股票，买入过1次，显然最大利润是0 // -prices[0]
        dp_g[0][0] = 0; // 第0天结束后不持有股票且卖出过0次，显然最大利润是0
        dp_g[0][1] = 0; // 第0天结束后不持有股票且卖出过1次，不存在该情况，初始化为0看看


        print(dp_f, dp_g, prices,0,2);
        for (int i = 1; i < prices.length; i++) {
            for (int k = 0; k < 3; k++) {
                if (k == 0) {
                    dp_f[i][k] = Math.max(dp_f[i - 1][k], dp_g[i - 1][0]);
                    dp_g[i][k] = dp_g[i - 1][k];
                } else {
                    dp_f[i][k] = Math.max(dp_f[i - 1][k],  dp_g[i - 1][k-1] - prices[i]);
                    dp_g[i][k] =  Math.max(dp_g[i - 1][k], dp_f[i - 1][k] + prices[i]);
                }

            }
            print(dp_f, dp_g, prices,i,2);

        }
        return max(dp_g[prices.length - 1][0], dp_g[prices.length - 1][1], dp_g[prices.length - 1][2]);
    }

    private static void print(int[][]f, int[][]g, int[] prices, int indexI, int indexK) {
        System.out.println("第"+indexI+"天结束后持有股票,并且已经买入过k次 能够获取的最大利润:");

        for (int[] _f : f) {
            System.out.println(Arrays.toString(_f));
        }

        System.out.println("第"+indexI+"天结束后不持有股票，并且已经卖出过k次，能够获得的最大利润:");
        for (int[] _g : g) {
            System.out.println(Arrays.toString(_g));
        }
        System.out.println();
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(new int[]{1,2,3,4,5}));
        int profile = solution.maxProfit(new int[]{1,2,3,4,5});
        System.out.println(profile);
    }

}
