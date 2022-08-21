package 每日一题;

import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * Easy题，为了赚积分
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 */
public class _66Solution {

    public static int[] plusOne(int[] digits) {
        // 返回1 代表最高位溢出了 返回-1代表没有溢出
        int isOverFlow = dfs(digits.length - 1, digits, 1);
        if (isOverFlow == 1) {
            int[] arr = new int[digits.length + 1];
            arr[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                arr[i+1] = digits[i];
            }
            return arr;
        }
        return digits;
    }

    private static int dfs(int i, int[] digits, int plus) {

        if (i == -1) {
            return 1;
        }

        if (digits[i] + plus < 10) {
            digits[i] = digits[i] + 1;
            return -1;
        }

        if (digits[i] + plus >= 10) {
            digits[i] = digits[i] + plus - 10;
            return dfs(i -1, digits, 1);
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] plusOne = plusOne(new int[]{1, 2, 3});
        int[] plusOne = plusOne(new int[]{9,9});
        System.out.println(Arrays.toString(plusOne));
    }
}
