package com.pc.LeetCode.题62;

import com.pc.LeetCode.common.Assert;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 * 
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        int top = 0;
        int left = 0;

        // 初始化第0行
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 初始化第0列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 横向遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }

                if (i - 1 >= 0) {
                    // dp[i-1][j]当前节点的上邻节点
                    top = dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    // 当前节点的左邻节点
                    left = dp[i][j - 1];
                }
                // dp[i][j] 当前节点  ,
                dp[i][j] = left + top;
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans=0;
        ans = solution.uniquePaths(3,2);
        Assert.assertEquals(ans, 3);
    }

}
