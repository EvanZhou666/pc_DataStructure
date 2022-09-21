package com.pc.LeetCode.题712;

/**
 * 712. 两个字符串的最小ASCII删除和
 */
public class Solution {

    /**
     * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        // 要让删除的ASCII总和最小，也就是留下来的ASCII码总和要越大越好。不过前提是要相等。
        // 如果不相等，肯定都要删除。
        int ans = longestCommonSubsequence(s1, s2);
        System.out.println(ans);
        return ans;
    }

    // copy from  1143
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

        // 初始化第0行第0列
        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = text1.charAt(0) + text2.charAt(0);
        }

        // ----------看起来很复杂，实际上都是在处理边界条件--------------
        // 初始化第0行m列
        for (int j = 1; j < m; j++) {
            if (text1.charAt(j) != text2.charAt(0)) {
                dp[0][j] = dp[0][j - 1] + text1.charAt(j);
            } else {

                int sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += text1.charAt(k);
                }
                dp[0][j]= sum;
            }
        }

        // 初始化第i行第0列
        for (int i = 1; i < n; i++) {
            if (text1.charAt(0) != text2.charAt(i)) {
                dp[i][0] = dp[i - 1][0] + text2.charAt(i);
            } else {
                int sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += text2.charAt(k);
                }
                dp[i][0] = sum;
            }
        }

        // 动态转移方程
        // 定义dp[i][j] 为text1[0...j]和text2的子字符串[0...i]如果要变得相等，要移除的最小ASCRII码之和。
        // 如果text1[j] == text2[i] => 则text1[j] 和 text2[i]对结果不影响，只需要考虑 子问题“text1[0...j-1]和text2的子字符串[0...i-1]如果要变得相等，要移除的最小ASCRII码之和”，即dp[i-1][j-1]
        // 如果text1[j] != text2[i] 则考察两种情况：
        //  -  “text1[0...j-1]和text2的子字符串[0...i]如果要变得相等，要移除的最小ASCRII码之和”
        //  - “text1[0...j]和text2的子字符串[0...i-1]如果要变得相等，要移除的最小ASCRII码之和”，
        // 为什么不需要考察第3种情况，将text1[j] 和 text2[i]都删除掉，前两种情况，只需要删除1个字符，而这个需要删除2个，肯定得不到最优解。
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (text1.charAt(j) == text2.charAt(i)) {
                    // i, j都保留
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 删除ascrii码小的那个
                    int a = dp[i][j - 1] + text1.charAt(j);
                    int b = dp[i - 1][j] + text2.charAt(i);
                    dp[i][j] = Math.min(a, b);
                }
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.minimumDeleteSum("eat", "sea");
        solution.minimumDeleteSum("sea", "eat");
    }

}
