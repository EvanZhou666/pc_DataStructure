package com.pc.LeetCode.题48.旋转图像;

import com.pc.LeetCode.common.Uitls;

import java.util.Arrays;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Solution {

    /**
     * 解法1：原地替换 利用规则：matrix[i][j] 旋转90度后会被移动到matrix[j][n-1-x]</p>
     * <p>以matrix[0][0]为例：</p>
     * <p>初始状态的matrix[0][0]移动到 matrix[0][2]</p>
     * <p>原来的matrix[0][2]移动到matrix[2][2]</p>
     * <p>原来的matrix[2][2]移动到matrix[2][0]</p>
     * <p>原来的matrix[2][0]移动到matrix[0][0]</p>
     * <p>解析来就是确定遍历的区间: 由上面的推导可知：区间肯定不是n*n，那这样就会重复移动了</p>
     * <p>经观察得出：
     *
     * <p>n=偶数时候：遍历区间是 (n/2) * (n/2)</p>  <p><img src="n为偶数.png"/></p>
     * <p>n=奇数时候：遍历区间是 （n/2+1） * n/2</p> <p><img src="n为奇数.png"/></p>
     * </p>
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // n=偶数 m = n/2 n=奇数 m = n/2 + 1
        int m = n % 2 == 0 ? n / 2 : n / 2 + 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 方式1：使用循环 交换元素
                // swapLoopUntil(i, j, matrix);
                // 方式2：使用dfs深度遍历交换
                dfs(i, j, j, n - 1 - i, matrix, n);
            }
        }

    }

    // 深度遍历，交换(i1，j1)和（j1，n - 1 - i1）
    private void dfs(int i, int j, int i1, int j1, int[][] matrix, int n) {
        // (i1,j1)等于了标记点（i，j）,这个时候应该要终止遍历。
        if (i == i1 && j == j1) {
            return;
        }
        dfs(i, j, j1, n - 1 - i1, matrix, n);
        // 找到了终止点，交换(i1，j1)和（i1，j1）的下一个元素：即(j1, n - 1 - i1)。
        swap(i1, j1, j1, n - 1 - i1, matrix);
    }

    private void swapLoopUntil(int i, int j, int[][] matrix) {
        int n = matrix.length;
        int x = j;
        int y = n-1-i;

        int temp = 0;
        while (!(x == i && y == j)){
            System.out.println("i:"+i +" j:"+j + " => x:"+x +" y:"+y);
            // 交换matrix[i][j]和matrix[x][y]
            swap(i, j, x, y, matrix);
            temp = x;
            x = y;
            y = n - 1 - temp;
        }

    }

    private void swap(int i, int j, int x, int y, int[][] matrix) {
        int temp;
        temp = matrix[x][y];
        matrix[x][y] = matrix[i][j];
        matrix[i][j] = temp;
    }

    public static void main(String[] args) {
//        int[][] matrix = Uitls.convertToInts("[[1,2,3],[4,5,6],[7,8,9]]");
        int[][] matrix = Uitls.convertToInts("[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]");
        Solution solution = new Solution();
        solution.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
