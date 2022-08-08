package com.pc.LeetCode.题130.被围绕的区域;

import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.Uitls;
import com.pc.并查集.MyUnionFind;

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
public class Solution2 {

    /**
     *
     *<p>这里用到了一个将二维数组转换为一维数组的常用技巧</p>
     *<p><img src="./二维坐标转换为一维坐标.png"></img></p>
     * 使用并查集解决
     * @param board
     */
    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // 为什么rows*cols后 还要加1? 是为了留给虚拟节点用的，虚拟节点占据一维数组的最后一个位置。
        MyUnionFind unionFind = new MyUnionFind(rows * cols + 1);

        // 先遍历左右边界上的'O'
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                // rows * cols 是虚拟节点的一维坐标
                unionFind.union(i * cols + 0, rows * cols);
            }

            if (board[i][cols - 1] == 'O') {
                unionFind.union(i * cols + (cols - 1), rows * cols);
            }

        }

        // 再遍历上下边界上的'O'
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                unionFind.union(0 * cols + j, rows * cols);
            }

            if (board[rows - 1][j] == 'O') {
                unionFind.union((rows - 1) * cols + j, rows * cols);
            }
        }

        int[][] d = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int[] ints : d) {
                        int x = ints[0] + i;
                        int y = ints[1] + j;
                        if (board[x][y] == 'O') {
                            unionFind.union(x * cols + y, i * cols + j);
                        }
                    }
                }

            }
        }

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (unionFind.find(i * cols + j) != unionFind.find(rows * cols)) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
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
