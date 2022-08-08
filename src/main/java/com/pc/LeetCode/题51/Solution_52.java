package com.pc.LeetCode.题51;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * n皇后问题 研究的是如何将 n个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_52 {

    private int result = 0;

    public int solveNQueens(int n) {
        Deque<String> path = new ArrayDeque<>();
        boolean[][] table = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = true;
            }
        }
        dfs(0, n, table, path);
        return result;
    }

    /**
     * @param index  解决第index皇后的位置问题
     * @param table
     * @param n
     */
    private void dfs(int index, int n, boolean[][] table, Deque<String> path) {

        if (index >= n) {
            result += 1;
            return;
        }

        for (int col = 0; col < n; col++) {

            boolean flag = true;
            // 判断第index行没有出现皇后
            for (int col1 = 0; col1 < n && flag; col1++) {
                if (!table[index][col1]) {
                    flag = false;
                    break;
                }
            }

            for (int row1 = 0; row1 <n && flag ; row1++) {
                if (!table[row1][col]) {
                    flag = false;
                    break;
                }
            }
//            - - 0,0
//            - - 1,1
            // 对角线上的元素不可选择 [0,2] [1,3]
            for (int r = index, c = col; r >= 0 && c >= 0 && flag; r--, c--) {
                if (!table[r][c]) {
                    flag = false;
                    break;
                }
            }

            // 判断另外一条对角线
            // [0,2] [1,1] [2,0]
            for (int r = index, c = col; r >= 0 && c < n && flag; r--, c++) {
                if (!table[r][c]) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                continue;
            }

            // 做选择，选择第table[index][i]位置，则该元素对应的行列都不可用
            char[] chars = new char[table[index].length];

            for (int j = 0; j < chars.length; j++) {
                chars[j] = '.';
            }

            chars[col] = 'Q';
            path.addLast(new String(chars));

            // 第index行的所有元素不可选择
            table[index][col] = false;

            // 递归 解决下一个皇后的位置
            dfs(index + 1, n, table, path);

            // 撤销选择
            path.removeLast();
            // 恢复状态
            table[index][col] = true;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> result = solution.solveNQueens(3);
        System.out.println(result);
    }
}
