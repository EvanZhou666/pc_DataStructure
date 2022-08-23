package com.pc.LeetCode.题63;

import java.util.Arrays;

/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。<br/>
 *
 * <img src="https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg"/>
 *
 */
public class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        int top;
        int left;

        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        // 初始化第0行
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }

        System.out.println(Arrays.toString(dp[0]));
        // 初始化第0列
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        // 横向遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    // dp[i-1][j]当前节点的上邻节点
                    top = dp[i - 1][j];
                    // 当前节点的左邻节点
                    left = dp[i][j - 1];
                    // dp[i][j] 当前节点  ,
                    dp[i][j] = left + top;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
