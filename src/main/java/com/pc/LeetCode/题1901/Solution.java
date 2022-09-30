package com.pc.LeetCode.题1901;

import com.alibaba.fastjson.JSON;
import com.pc.LeetCode.common.GoodQuestion;

/**
 * 1901. 寻找峰值 II
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * <p>
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 * <p>
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * <p>
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法<br/>
 *
 * <img src="https://assets.leetcode.com/uploads/2021/06/08/1.png"/>
 */
@GoodQuestion(type = "二分搜索系列")
public class Solution {

    /**
     * 二分搜索系列，”为什么可以二分搜索以及怎么二分搜索“比较隐藏。
     * 有点像是用二分法在猜答案
     * @param mat
     * @return
     */
    public int[] findPeakGrid(int[][] mat) {
        int[] res = new int[0];

        int start = 0, end = mat.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            // 求第mid行的最大值，返回值为最大值所在的列
            int midCur = maxValueReturnCol(mat, mid);

            // 没有上一行了,有下一行
            if (mid - 1 < start && mid + 1 <= end) {
                // 求第mid + 1行的最大值，返回值为最大值所在的列
                int midNext = maxValueReturnCol(mat, mid + 1);
                // 第mid行的最大值比mid+1行的最大值还要大，那那么mid，midCur就是峰值了
                if (mat[mid][midCur] > mat[mid + 1][midNext]) {
                    return new int[]{mid, midCur};
                } else {
                    // 反之 mid+1. midNext是峰值
                    start = mid + 1;
//                    return new int[]{mid, midCur};
                }
            } else if (mid - 1 >= start && mid + 1 > end) {
                // 有上一行 没有下一行
                // 求第mid-1行的最大值，返回值为最大值所在的列
                int midPre = maxValueReturnCol(mat, mid - 1);
                if (mat[mid][midCur] > mat[mid + 1][midPre]) {
                    return new int[]{mid, midPre};
                } else {
                    end = mid - 1;
                }
            } else if (mid - 1 < start && mid + 1 > end) {
                // 上下行，都没有了，那么峰值就是当前行的最大值所处位置
                return new int[]{mid, midCur};
            } else { // 核心逻辑，上面的if都是边界情况

                // 1. 如果mid行的最大值比它的上下两行的最大值还要大，那么mid行的最大值一定是峰值咯
                // 2. 如果mid行的上一行的最大值比剩余两行的最大值还要大，那么我们往上搜索即在[start,mid-1]区间搜索，遇到峰值的可能性更高（实际上一定会有峰值），并不意味着在[mid+1,end]区间就没有峰值
                // 3. 如果mid行的下一行的最大值比剩余两行的最大值还要大，那么我们往上搜索即在[mid+1,end]区间搜索，遇到峰值的可能性更高（实际上一定会有峰值），并不意味着在[start,mid-1】区间就没有峰值

                // 求第mid-1行的最大值，返回值为最大值所在的列
                int midPre = maxValueReturnCol(mat, mid - 1);
                // 求第mid行的最大值，返回值为最大值所在的列
                midCur = maxValueReturnCol(mat, mid);
                // 求第mid + 1行的最大值，返回值为最大值所在的列
                int midNext = maxValueReturnCol(mat, mid + 1);
                if (mat[mid - 1][midPre] > mat[mid][midCur] && mat[mid - 1][midPre] > mat[mid + 1][midNext]) {
                    end = mid - 1;
                } else if (mat[mid + 1][midNext] > mat[mid][midCur] && mat[mid + 1][midNext] > mat[mid - 1][midPre]) {
                    start = mid + 1;
                } else {
                    return new int[]{mid, midCur};
                }
            }
        }
        return new int[0];
    }

    /**
     * 求第row行最大值在哪一列
     *
     * @param mat
     * @param row
     * @return
     */
    private int maxValueReturnCol(int[][] mat, int row) {
        // 越界返回-1
        if (row < 0 || row >= mat.length) {
            return -1;
        }

        int col = 0;
        for (int i = 0; i < mat[row].length; i++) {
            if (mat[row][i] > mat[row][col]) {
                col = i;
            }
        }
        return col;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans;
//        ans = solution.findPeakGrid(new int[][]{{1,4},{3,2}});
//        System.out.println(JSON.toJSONString(ans));

        ans = solution.findPeakGrid(new int[][]{{10, 20, 15}, {21, 30, 14}, {7, 16, 32}});
        System.out.println(JSON.toJSONString(ans));
    }
}
