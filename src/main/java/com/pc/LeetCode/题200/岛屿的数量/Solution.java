package com.pc.LeetCode.题200.岛屿的数量;

import com.pc.LeetCode.common.Uitls;
import com.pc.并查集.MyUnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        MyUnionFind myUnionFind = new MyUnionFind(row * col + 1);
        int[][] d = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                for (int k = 0; k < d.length; k++) {
                    int x = d[k][0] + i;
                    int y = d[k][1] + j;
                    if (x >= 0 && y >= 0 && x < row && y < col) {
                        if (grid[i][j] == '0') {
                            // 连接到虚拟节点
                            myUnionFind.union(i * col + j, row * col);
                        }
                        if (grid[x][y] == grid[i][j] & grid[x][y] == '1') {
                            myUnionFind.union(x * col + y, i * col + j);
                        }
                    }
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    int root = myUnionFind.find(i * col + j);
                    set.add(root);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        String arrayJsonStr = "[['1','1','1','1','0'],['1','1','0','1','0'],['1','1','0','0','0'],['0','0','0','0','0']]";
        char[][] board = Uitls.convert(arrayJsonStr);
        Solution solution = new Solution();
        int nums = solution.numIslands(board);
        System.out.println(nums);
    }

}
