package com.pc.LeetCode.题343;

/**
 * <a href="https://leetcode.cn/problems/decode-ways/">343. 整数拆分</a>
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 *
 *
 */
public class Solution {

    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int j = 1;
            while (j < i) {
                // 计算继续分割i和不继续分割i哪一个大，将最大值*j，然后和历史值进行比较后更新
                dp[i] = Math.max(Math.max((i - j) , dp[i-j]) * j, dp[i]);
                j ++;
            }
        }
        return dp[n];
    }
}
