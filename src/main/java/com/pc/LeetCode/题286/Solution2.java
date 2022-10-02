package com.pc.LeetCode.题286;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2 {

    int[][] d = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * 多源bfs解法
     * @param rooms
     */
    public void wallsAndGates(int[][] rooms) {
        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                // 把有门的格子加入到队列
                if (rooms[i][j] == 0) {
                    queue.push(new int[]{i, j});
                }

            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int dis = rooms[poll[0]][poll[1]];
            for (int i = 0; i < d.length; i++) {
                int row = poll[0] + d[i][0];
                int col = poll[1] + d[i][1];

                if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length) {
                    continue;
                }

                // 遇到障碍物，就没必要加入队列了，因为从障碍物出发，不能达到任何门。
                if (rooms[row][col] == -1) {
                    continue;
                }

                // 不存在这种情况，因为一开始我就将所有的门入队了
                // if (rooms[row][col] == 0) {
                //    queue.add(new int[]{row, col});
                //    System.out.println("error");
                // }
                if (rooms[row][col] == Integer.MAX_VALUE) {
                    rooms[row][col] = dis + 1;
                    queue.add(new int[]{row, col});
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        solution.wallsAndGates(new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}});
    }
}

