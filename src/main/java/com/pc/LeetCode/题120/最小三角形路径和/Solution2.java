package com.pc.LeetCode.题120.最小三角形路径和;

import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/triangle/">120. 三角形最小路径和</a>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 */

public class Solution2 {

	/**
     * 解法2：动态规划
	 * @param triangle
     * @return
     */
	public int minimumTotal(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[][] dp = new int[n][n];
		dp[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < n ; i++) {
			for (int j = 0; j <= i ; j++) {
				// 边界问题 第0个特殊
				if (j == 0) {
					dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
				}
				// 边界问题，最后一个也特殊
				else if (j == i) {
					dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
				}
				else {
					dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
				}
				// System.out.print("dp[i][j]=" + dp[i][j] + " ");
			}
			// System.out.println();
		}

		int minSum = Integer.MAX_VALUE;
		for (int i=0;i < triangle.get(n-1).size(); i++) {
			minSum = Math.min(minSum, dp[n-1][i]);
		}
		return minSum;
	}

}
