package com.pc.LeetCode.题121;

/**
 * 暴力解法
 */
public class BurteSolution {

    // 暴力解法 O(N^2) 遍历两次。
    // 第一次遍历，轮询nums中的每个元素，假设在该天买入股票，记作第i天
    // 第二次遍历，轮询[i+1,n)之后的每个元素，尝试在[i+1,n)区间的每天卖出股票，计算最大利润更新结果。
    public int maxProfit(int[] prices) {
        int profile = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    profile = Math.max(profile, prices[j] - prices[i]);
                }
            }
        }
        return profile;
    }

    // 解法2。
    // 暴力解法第2次遍历实际上是在找一个区间最大值，求区间最大值，可以使用线段树或者是动态编程思想来解决。
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int profile = 0;
        int maxPrice = prices[n-1];
        for (int i = n-1; i >= 0; i--) {
            maxPrice = Math.max(prices[i], maxPrice);
            profile = Math.max(profile, maxPrice - prices[i]);
        }
        return profile;
    }

}
