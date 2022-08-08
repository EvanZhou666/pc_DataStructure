package com.pc.LeetCode.题130.被围绕的区域;

import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.Uitls;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。<br/>
 * <img src="./img_1.png"></img>
 * <p>输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]</p>
 * <p>输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]</p>
 * <p>解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。</p>
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 */
public class Solution3 {

    /**
     * 方向数组，方向搜索常用套路
     */
    int[][] d = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 解法1的优化，使用dfs深度遍历，不过不同点在于从边界上的O开始遍历，如果遇到'O'将其替换为‘K’，等最后再将K替换为O
     * @param board
     */
    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // 先遍历左右边界上的'O'
        for (int i = 0; i < rows; i++) {
            dfs(i, 0, board);
            dfs(i, cols - 1, board);
        }

        // 再遍历上下边界上的'O'
        for (int j = 0; j < cols; j++) {
            dfs(0, j, board);
            dfs(rows - 1, j, board);
        }


        // 再遍历一遍数组，将'K'还原为'O'，为'k'的点代表能和边界上的'O'相连通；或者就是边界上的'O'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'K') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    private void dfs(int i, int j, char[][] board) {

        if (i < 0 || j < 0 || i > board.length -1 || j > board[0].length - 1) {
            return;
        }

        if (board[i][j] != 'O') {
            return;
        }

        if (board[i][j] == 'O') {
            board[i][j] = 'K';
            for (int[] ints : d) {
                int x = ints[0] + i;
                int y = ints[1] + j;
                dfs(x, y, board);
            }
            // 如果board[i][j]是边界上的不进行替换， 这么做可能导致死循环啊！！！
//            if (i != 0 || j != 0 || i != board.length - 1 || j != board[0].length - 1) {
//                board[i][j] = 'K';
//            }
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String input = "[['O','O','X','O','O','X','O','O','O','O','O','O','O','O','X','X','O','X','O','O'],['O','X','O','O','O','O','X','X','O','O','O','X','O','O','X','X','O','O','O','O'],['X','O','X','X','O','O','O','O','O','X','O','O','O','X','O','X','X','X','X','O'],['X','X','O','O','O','O','O','O','O','O','X','O','O','X','X','X','X','X','X','X'],['O','O','O','O','O','X','O','O','O','X','X','X','X','O','X','O','O','O','O','O'],['O','X','X','X','O','O','O','X','O','X','O','X','O','O','X','O','X','X','O','O'],['O','O','O','O','O','O','O','O','X','X','X','O','O','X','X','O','O','O','O','O'],['O','X','X','O','O','O','O','O','X','O','X','X','O','X','X','O','O','X','O','O'],['O','O','X','X','X','O','O','X','O','O','O','O','O','O','O','X','X','X','O','X'],['X','X','O','O','O','X','O','X','O','O','O','X','X','O','O','X','O','X','X','O'],['O','O','O','O','O','O','X','O','X','X','O','O','X','O','X','X','X','X','O','X'],['O','O','X','X','O','O','X','O','X','O','O','X','O','O','X','O','O','X','O','X'],['O','X','O','O','O','O','O','X','O','O','O','O','O','O','X','X','X','O','O','O'],['O','O','X','O','X','O','O','X','X','O','X','X','X','O','O','X','X','O','O','X'],['X','O','X','O','X','O','X','O','O','O','O','O','O','O','X','O','O','X','X','O'],['X','O','X','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','X','X'],['X','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X'],['O','O','O','O','O','O','X','O','O','O','X','O','X','O','X','X','O','X','O','X'],['X','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X','X','X'],['O','O','O','X','X','O','O','O','O','O','O','O','O','X','O','O','O','X','O','O']]";
        char[][] board = Uitls.convert(input);
        Uitls.printBoard(board, "替换前");
        solution.solve(board);
        Uitls.printBoard(board, "替换后");
        String exceptAns = "[['O','O','X','O','O','X','O','O','O','O','O','O','O','O','X','X','O','X','O','O'],['O','X','O','O','O','O','X','X','O','O','O','X','O','O','X','X','O','O','O','O'],['X','X','X','X','O','O','O','O','O','X','O','O','O','X','X','X','X','X','X','O'],['X','X','O','O','O','O','O','O','O','O','X','O','O','X','X','X','X','X','X','X'],['O','O','O','O','O','X','O','O','O','X','X','X','X','O','X','O','O','O','O','O'],['O','X','X','X','O','O','O','X','O','X','X','X','O','O','X','O','X','X','O','O'],['O','O','O','O','O','O','O','O','X','X','X','O','O','X','X','O','O','O','O','O'],['O','X','X','O','O','O','O','O','X','O','X','X','O','X','X','O','O','X','O','O'],['O','O','X','X','X','O','O','X','O','O','O','O','O','O','O','X','X','X','O','X'],['X','X','O','O','O','X','O','X','O','O','O','X','X','O','O','X','X','X','X','O'],['O','O','O','O','O','O','X','X','X','X','O','O','X','O','X','X','X','X','O','X'],['O','O','X','X','O','O','X','X','X','O','O','X','O','O','X','X','X','X','O','X'],['O','X','O','O','O','O','O','X','O','O','O','O','O','O','X','X','X','O','O','O'],['O','O','X','O','X','O','O','X','X','O','X','X','X','O','O','X','X','O','O','X'],['X','O','X','O','X','O','X','O','O','O','O','O','O','O','X','O','O','X','X','O'],['X','O','X','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','X','X'],['X','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X'],['O','O','O','O','O','O','X','O','O','O','X','O','X','O','X','X','O','X','X','X'],['X','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X','X','X'],['O','O','O','X','X','O','O','O','O','O','O','O','O','X','O','O','O','X','O','O']]";
        char[][] ex = Uitls.convert(exceptAns);
        Uitls.printBoard(ex, "期望值");
        Assert.assertEquals(board, ex);
    }

}
