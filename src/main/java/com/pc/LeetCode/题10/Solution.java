package com.pc.LeetCode.题10;

import com.pc.LeetCode.common.Assert;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class Solution {

    /**
     * 动态规划，就是边界条件太多
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        char[] str = new char[s.length() + 1];
        char[] patterns = new char[p.length() + 1];
        System.arraycopy(s.toCharArray(), 0, str, 1, str.length - 1);
        System.arraycopy(p.toCharArray(), 0, patterns, 1, patterns.length - 1);

        boolean[][] dp = new boolean[str.length][patterns.length];
        dp[0][0] = true;

        // i和j是针对dp数组的下标，对应到s,p的时候有1个偏移量
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {

                // 如果是字母或者"."
                if ((Character.isLetter(patterns[j])) || patterns[j] == '.') {
                    // "."可以和任何字符匹配；如果是字母则必须相等； i >= 1 防止越界
                    if ((patterns[j] == str[i] || patterns[j] == '.') && i >= 1) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    boolean temp1 = false;
                    boolean temp2 = false;

                    // 场景1：*前面的字符出现多次，patter[j-1...j]看作整体,多次匹配str，（条件是：str[i] 和 patterns[j-1]能够匹配）
                    if ((str[i] == patterns[j - 1] || (patterns[j - 1] == '.' && str[i] != '\u0000')) && i >= 1) {
                        temp1 = dp[i - 1][j];
                    }

                    // 场景2：patter[j-1...j]看作整体，不参与匹配。
                    if (j >= 2) {
                        temp2 = dp[i][j - 2];
                    }
                    // 场景1和场景2 有1个为真，则结果为真
                    dp[i][j] = temp1 || temp2;
                }

            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertIsFalse(solution.isMatch("aa", "a"));
        Assert.assertIsTrue(solution.isMatch("aa", "a*"));
        Assert.assertIsTrue(solution.isMatch("ab", ".*"));
        Assert.assertIsTrue(solution.isMatch("aab", "c*a*b"));
        Assert.assertIsFalse(solution.isMatch("bb", ".bab"));
    }
}
