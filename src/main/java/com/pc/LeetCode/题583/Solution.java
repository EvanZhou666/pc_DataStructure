package com.pc.LeetCode.题583;

/**
 * 583.两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *
 * 每步 可以删除任意一个字符串中的一个字符。
 */
public class Solution {

    // 复用经典例题代码，1143题 求公共最长子序列
    public int minDistance(String word1, String word2) {
        int longestCommon = longestCommonSubsequence(word1, word2);
        return word1.length() + word2.length() - longestCommon * 2;
    }

    public int longestCommonSubsequence(String text1, String text2) {

        if (text1.length() <= text2.length()) {

            return doLongestCommonSubsequence(text1, text2);
        } else {
            return doLongestCommonSubsequence(text2, text1);
        }

    }

    // 【不要管什么动态规划】就想象一张二维结果表
    public int doLongestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[n][m];

        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 1;
        }

        for (int j = 1; j < m; j++) {
            if (dp[0][j-1] == 0 && text1.charAt(j) == text2.charAt(0)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }

        for (int i = 1; i < n; i++ ) {
            if (dp[i-1][0] == 0 && text1.charAt(0) == text2.charAt(i)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }

        for (int i = 1; i < n; i ++) {
            for (int j = 1; j < m; j ++) {
                if (text1.charAt(j) == text2.charAt(i)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n-1][m-1];
    }
}
