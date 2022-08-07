package com.pc.LeetCode.題130.被围绕的区域;

import com.alibaba.fastjson.JSONArray;
import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.Uitls;

import java.util.*;
class Solution {

    /**
     * <p>缓存深度遍历的结果，解决超时问题。</p>
     * <img src="img.png"/>
     * <p>如图所示：存在如下遍历顺序(9,3)->(9,4)->(10,4)->(10,5)->(11,5)->(12,5)->(12,4)->(11,4)</p>
     * <p>由于在我们的遍历路径中(10,4)刚刚”走过“，如果这个时候继续向上遍历(10,4)节点，便会陷入死循环。</p>
     * <p>因此为了避免陷入死循环，在代码中遇到已经走过的点，会 return false，如果这个时候缓存(11,4)的连通性</p>
     * <p>并且错误的认为(11,4）不能够"连通"到边界上就错了。实际上(11，4)还没有向上遍历过(10，4),很明显（11，4）是具有边界连通性的</p>
     * <p>在遍历的时候只能缓存能够连通到边界的节点</p>
     * <p>经过leetcode验证，依旧没法解决超时问题，在M*N特别大的时候复杂度很高，靠缓存依旧无法解决<a href="https://leetcode.cn/submissions/detail/347129278/">@see</a></p>
     */
    private Map<String, Boolean> cache = new HashMap<>();

    /**
     * 只有和边界上的'O'相连接的O，才不会被替换。因此可以考虑从矩形的中间进行深度遍历(依次进行上下左右的搜索)，如果该位置是'O'则进行上下左右的搜索，看能否到达边界上的'O'
     * 果遇到‘x’则跳过，换一个方向
     * @param board
     */
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        // i代表行号 j代表列号
        for (int i = 1; i < rows-1; i++) {  // 最后一行 board[rows-1]
            for (int j = 1; j < cols-1 ; j++) { // 最后一列 board[][cols-1]
                // 不需要替换的格子，跳过。继续遍历当前行的下一列。
                if (board[i][j] == 'X') {
                    continue;
                }

                if (checkUp(i, j, board) || checkLeft(i, j, board)) {
                    continue;
                }

                Set<String> set = new HashSet<>();
                set.add(i + "," + j);
                // 检查右邻节点是否能够连通到边界上
                Tuple ok_right = dfs(i, j + 1, board, "left", set);
                Tuple ok_down = dfs(i + 1, j, board, "up", set);
                set.remove(i + "," + j);

                // 右邻接点或者左邻接点不能够连接到边界上的'O'，所以替换
                if (!ok_right.success && !ok_down.success) {
                    board[i][j] = 'X';
                }
            }

        }
        cache.clear();
    }

    /**
     * 检查board[i][j]的上邻节点是否等于'O'
     * @param i
     * @param j
     * @param board
     * @return
     */
    private boolean checkUp(int i, int j, char[][] board) {
        return board[i-1][j] == 'O';
    }

    /**
     * 检查board[i][j]的左邻节点是否等于’O‘
     * @param i
     * @param j
     * @param board
     * @return
     */
    private boolean checkLeft(int i, int j, char[][] board) {
        return board[i][j-1] == 'O';
    }


    /**
     * 深度遍历dfs，检查当前board[i][j]的上下左右4个邻节点的"连通性"。
     * @param i 行id 从0开始
     * @param j 列id 从0开始
     * @param board 待修改的二维数组
     * @param except 排除某个方向的邻节点，不检查该方向上的节点是否具有“连通性”
     * @param paths 已经访问过的board[i][j]，防止深度遍历出现死循环
     * @return
     */
    private Tuple dfs(int i, int j, char[][] board, String except, Set<String> paths) {

        if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
            // 能连通到边界
            return Tuple.of(board[i][j] == 'O');
        }

        if (i >= board.length || j >= board[0].length) {
            return Tuple.of(false);
        }

        if (board[i][j] == 'X') {
            return Tuple.of(false);
        }

        if (paths.contains(i + "," + j)) {
            return Tuple.of (false, "duplicate");
        }

        Boolean cacheValue = getNodeValue(i, j , except);
        if (cacheValue != null) {
            return Tuple.of (cacheValue, "cacheValue");
        }


        paths.add(i + "," + j);
        // 检查右邻节点是否能够联通到边界上
        if ("left".equals(except)) {
            // 往右遍历
            Tuple ok_right = dfs(i, j + 1, board, "left", paths);
            // 往下遍历
            Tuple ok_down = dfs(i + 1, j, board, "up", paths);
            // 往上遍历
            Tuple ok_up = dfs(i- 1, j, board, "down", paths );

            // ***************************************************
            // 这一段垃圾代码是为了解决超时问题写的。不是主逻辑，但是“然并卵”
            paths.remove(i + "," + j);
            String description = "unkown";
            if (!"duplicate".equals(ok_right.description) &&
                    !"duplicate".equals(ok_down.description) &&
                    !"duplicate".equals(ok_up.description)
            ) {
                cacheWhenNodeCanTouchBoard(i, j, except,ok_right.success || ok_down.success || ok_up.success);
                description = "";
            }
            // *********************************************

            return Tuple.of(ok_right.success || ok_down.success || ok_up.success, description);
        }

        else if ("right".equals(except)) {
            // 往左遍历
            Tuple ok_left = dfs(i, j -1 , board, "right", paths);
            // 往下遍历
            Tuple ok_down = dfs(i + 1, j, board, "up", paths);
            // 往上遍历
            Tuple ok_up = dfs(i - 1, j, board, "down", paths);
            paths.remove(i + "," + j);

            // ***************************************************
            // 这一段垃圾代码是为了解决超时问题写的。不是主逻辑，但是“然并卵”
            String description = "unkown";
            if (!"duplicate".equals(ok_left.description) &&
                    !"duplicate".equals(ok_down.description) &&
                    !"duplicate".equals(ok_up.description)
            ) {
                cacheWhenNodeCanTouchBoard(i, j, except,ok_left.success || ok_down.success || ok_up.success);
                description = "";
            }
            // ***************************************************

            return Tuple.of(ok_left.success || ok_down.success || ok_up.success, description);
        }

        else if ("up".equals(except)) {
            // 往左遍历
            Tuple ok_left = dfs(i, j - 1 , board, "right", paths);
            // 往右遍历
            Tuple ok_right = dfs(i, j + 1, board, "left", paths);
            // 往下遍历
            Tuple ok_down = dfs(i + 1, j, board, "up", paths);

            paths.remove(i + "," + j);

            // ***************************************************
            // 这一段垃圾代码是为了解决超时问题写的。不是主逻辑，但是“然并卵”
            String description = "unkown";
            if (!"duplicate".equals(ok_left.description) &&
                    !"duplicate".equals(ok_down.description) &&
                    !"duplicate".equals(ok_right.description)
            ) {
                cacheWhenNodeCanTouchBoard(i, j, except,ok_left.success || ok_down.success || ok_right.success);
                description = "";
            }
            // ***************************************************
            return Tuple.of(ok_left.success || ok_right.success || ok_down.success, description);
        }

        else if ("down".equals(except)) {
            // 往左遍历
            Tuple ok_left = dfs(i, j -1 , board, "right", paths);
            // 往右遍历
            Tuple ok_right = dfs(i, j + 1, board, "left", paths);
            // 往上遍历
            Tuple ok_up = dfs(i - 1, j, board, "down", paths);

            paths.remove(i + "," + j);

            // ***************************************************
            // 这一段垃圾代码是为了解决超时问题写的。不是主逻辑，但是“然并卵”
            String description = "unkown";
            if (!"duplicate".equals(ok_left.description) &&
                    !"duplicate".equals(ok_up.description) &&
                    !"duplicate".equals(ok_right.description)
            ) {
                cacheWhenNodeCanTouchBoard(i, j, except,ok_left.success || ok_up.success || ok_right.success);
                description = "";
            }
            // ***************************************************
            return Tuple.of(ok_left.success || ok_right.success || ok_up.success, description);
        }

        else return Tuple.of(false);
    }

    /**
     * 缓存board[i][j]的”连通性“
     * @param i
     * @param j
     */
    private void cacheWhenNodeCanTouchBoard(int i, int j, String except, boolean value) {
        cache.put(String.format("%d,%d,%s", i, j, except), value);
    }

    private Boolean getNodeValue(int i, int j, String except) {
        return cache.get(String.format("%d,%d,%s", i, j, except));
    }

    static class Tuple {
        public Tuple(Boolean success) {
            this.success = success;
        }

        public Tuple(Boolean success, String description) {
            this.success = success;
            this.description = description;
        }

        public boolean success() {
            return this.success;
        }
        public static Tuple of (Boolean success) {
            return new Tuple(success);
        }

        public static Tuple of(Boolean success, String description) {
            return new Tuple(success, description);
        }
        private Boolean success;
        private String description;
    }

    public static void main(String[] args) {
        String input = "[['O','O','X','O','O','X','O','O','O','O','O','O','O','O','X','X','O','X','O','O'],['O','X','O','O','O','O','X','X','O','O','O','X','O','O','X','X','O','O','O','O'],['X','O','X','X','O','O','O','O','O','X','O','O','O','X','O','X','X','X','X','O'],['X','X','O','O','O','O','O','O','O','O','X','O','O','X','X','X','X','X','X','X'],['O','O','O','O','O','X','O','O','O','X','X','X','X','O','X','O','O','O','O','O'],['O','X','X','X','O','O','O','X','O','X','O','X','O','O','X','O','X','X','O','O'],['O','O','O','O','O','O','O','O','X','X','X','O','O','X','X','O','O','O','O','O'],['O','X','X','O','O','O','O','O','X','O','X','X','O','X','X','O','O','X','O','O'],['O','O','X','X','X','O','O','X','O','O','O','O','O','O','O','X','X','X','O','X'],['X','X','O','O','O','X','O','X','O','O','O','X','X','O','O','X','O','X','X','O'],['O','O','O','O','O','O','X','O','X','X','O','O','X','O','X','X','X','X','O','X'],['O','O','X','X','O','O','X','O','X','O','O','X','O','O','X','O','O','X','O','X'],['O','X','O','O','O','O','O','X','O','O','O','O','O','O','X','X','X','O','O','O'],['O','O','X','O','X','O','O','X','X','O','X','X','X','O','O','X','X','O','O','X'],['X','O','X','O','X','O','X','O','O','O','O','O','O','O','X','O','O','X','X','O'],['X','O','X','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','X','X'],['X','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X'],['O','O','O','O','O','O','X','O','O','O','X','O','X','O','X','X','O','X','O','X'],['X','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X','X','X'],['O','O','O','X','X','O','O','O','O','O','O','O','O','X','O','O','O','X','O','O']]";

        char[][] board = Uitls.convert(input);

        Solution solution = new Solution();
        solution.solve(board);
        String exceptAns = "[['O','O','X','O','O','X','O','O','O','O','O','O','O','O','X','X','O','X','O','O'],['O','X','O','O','O','O','X','X','O','O','O','X','O','O','X','X','O','O','O','O'],['X','X','X','X','O','O','O','O','O','X','O','O','O','X','X','X','X','X','X','O'],['X','X','O','O','O','O','O','O','O','O','X','O','O','X','X','X','X','X','X','X'],['O','O','O','O','O','X','O','O','O','X','X','X','X','O','X','O','O','O','O','O'],['O','X','X','X','O','O','O','X','O','X','X','X','O','O','X','O','X','X','O','O'],['O','O','O','O','O','O','O','O','X','X','X','O','O','X','X','O','O','O','O','O'],['O','X','X','O','O','O','O','O','X','O','X','X','O','X','X','O','O','X','O','O'],['O','O','X','X','X','O','O','X','O','O','O','O','O','O','O','X','X','X','O','X'],['X','X','O','O','O','X','O','X','O','O','O','X','X','O','O','X','X','X','X','O'],['O','O','O','O','O','O','X','X','X','X','O','O','X','O','X','X','X','X','O','X'],['O','O','X','X','O','O','X','X','X','O','O','X','O','O','X','X','X','X','O','X'],['O','X','O','O','O','O','O','X','O','O','O','O','O','O','X','X','X','O','O','O'],['O','O','X','O','X','O','O','X','X','O','X','X','X','O','O','X','X','O','O','X'],['X','O','X','O','X','O','X','O','O','O','O','O','O','O','X','O','O','X','X','O'],['X','O','X','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','X','X'],['X','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X'],['O','O','O','O','O','O','X','O','O','O','X','O','X','O','X','X','O','X','X','X'],['X','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X','X','X'],['O','O','O','X','X','O','O','O','O','O','O','O','O','X','O','O','O','X','O','O']]";
        char[][] ex = Uitls.convert(exceptAns);
        Uitls.printBoard(ex, "期望值");
        Assert.assertEquals(board, ex);
    }
}