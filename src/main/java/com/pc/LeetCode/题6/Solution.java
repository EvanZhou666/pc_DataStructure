package com.pc.LeetCode.题6;

import com.pc.LeetCode.common.Assert;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 */
public class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        char[][] twoArr = new char[numRows][length];
        int row = 0;
        int col = 0;

        boolean changeCol = false;
        boolean addRow = true;

        for (int i = 0; i < length; i++) {
            twoArr[row][col] = chars[i];

//            1. 先确定下一个数据在哪一行
            // 增加行号
            if (addRow) {
                if (row + 1 == numRows) {
                    addRow = false;
                    row = row -1;
                } else {
                    row = row + 1;
                }
            } else {
                if (row - 1 == -1) {
                    addRow = true;
                    changeCol = false;
                    row = row +1;
                } else {
                    row = row - 1;
                }
            }

            // 2. 再确定下一个元素放在哪一列
            if (changeCol) {
                col = col + 1;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char[] chars1 : twoArr) {
            for (char c : chars1) {
                if (c != 0) {
                    builder.append(c);
                }
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "ABC";
        int numRows = 1;
        String convert = solution.convert(str, numRows);
        Assert.assertEquals("PAHNAPLSIIGYIR", convert);
    }

}
