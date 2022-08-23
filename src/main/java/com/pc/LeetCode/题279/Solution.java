package com.pc.LeetCode.题279;

/**
 * <a href="https://leetcode.cn/problems/perfect-squares/">279. 完全平方数</a>
 * <p>
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * <p>示例1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * </p>
 *
 * <p>示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * </p>
 * </p>
 */
public class Solution {

//    解答错误，并非贪心算法
//    public static int numSquares(int n) {
//        // 先开平方根
//        int m = (int) Math.pow(n, 0.5d);
//        int[] dp = new int[m+1];
//        for (int i = 1; i <= m; i++) {
//            dp[i] = i * i;
//        }
//
//        int sum = n;
//        int count = 0;
//        // 贪心
//        for (int i = dp.length - 1; i > 0;) {
//            if (sum == 0) {
//                break;
//            }
//            if (sum >= dp[i]) {
//                sum = sum - dp[i];
//                count ++;
//            } else {
//                i --;
//            }
//
//        }
//        return count;
//    }

    /**
     * 动态规划<br/>
     * <img src="./img.png">状态转移方程</img>
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int min;
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // 先将n开平方根，pow^2一定是小于等于n的
            min = Integer.MAX_VALUE;
            int pow = (int) Math.pow(i, 0.5d);
            if (pow * pow != i) {
                for (int j = pow; j > 0; j--) {
                    min = Math.min(min, dp[i - j * j] + 1);
                }
            } else {
                min = 1;
            }
            dp[i] = min;
        }
        return dp[n];
    }
    public static void main(String[] args) {
        for (int i = 1; i < 13; i++) {
            int ans = numSquares(i);
            System.out.println(i +"：" + ans);
        }
    }
}
