package com.pc.LeetCode.题1422.分隔字符串的最大得分;

import com.pc.LeetCode.common.Assert;

/**
 * 1422. 分割字符串的最大得分
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
 *
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 *
 * 示例 2：
 *
 * 输入：s = "00111"
 * 输出：5
 * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 *
 */
public class Solution {

    public static int maxScore(String s) {
        char[] chars = s.toCharArray();
        // precomputed prefix sum for counting ones ('1').
        int[] preSum = new int[chars.length];

        preSum[0] = chars[0] == '1' ? 1 : 0;
        for (int i = 1; i < chars.length; i++) {
            preSum[i] = preSum[i-1] + (chars[i] == '1' ? 1 : 0);
        }

        int count_0 = 0;
        int maxScore= 0;
        // Iterate from left to right counting the number of zeros ('0'),
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == '0') {
                count_0 ++;
            }
            // Update the answer.
            maxScore = Math.max(maxScore, count_0 + preSum[chars.length-1] - preSum[i]);

        }

        return maxScore;
    }

    public static void main(String[] args) {
        int maxScofre = 0;
        maxScofre = maxScore("111");
//        maxScofre = maxScore("011101");
        Assert.assertEquals(maxScofre, 5);
    }
}
