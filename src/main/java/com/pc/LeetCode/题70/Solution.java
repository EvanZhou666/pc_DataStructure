package com.pc.LeetCode.题70;


/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Solution {

    /**
     * 动态规划解法
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] dp =  new int[n];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n-1];
    }


//    /**
//     * 两年前的自己的解法，未接触动态规划前，使用递归解法
//     * @param n
//     * @return
//     */
//    public int climbStairs2(int n) {
//        if (n == 1) {
//            // 直接跨一步
//            return 1;
//        } else if (n == 2) {
//            // 直接跨一步或者跨两步
//            return 2;
//        } else {
//            int Pn1 = climbStairs2(n - 1);
//            int Pn2 = climbStairs2(n - 2);
//            return Pn1 + Pn2;
//        }
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.climbStairs(10);
//        int ans2 = solution.climbStairs2(10);
        System.out.println(ans);
//        Assert.assertEquals(ans, ans2);
    }
}
