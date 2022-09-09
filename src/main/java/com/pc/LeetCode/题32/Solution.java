package com.pc.LeetCode.题32;

import com.pc.LeetCode.common.Assert;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Solution {

    /**
     * <img src="./img.png"/>
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {

        if ("".equals(s)) { // 空字符串 直接return 0
            return 0;
        }

        char[] chars = s.toCharArray();
        // 定义dp[i]是以右括号结尾的最长有效括号
        int[] dp = new int[chars.length];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {

            // 如果以左括号结尾，肯定不能构成有效的字串，所以为0
            if (chars[i] == '(') {
                dp[i] = 0;
            } else { // 以右括号结尾
                // 如果前一个字符是左括号，则构成的有效括号为“以前前一个字符结尾的有效括号长度”+2，但是要处理边界，“以前前一个字符”可能并不存在
                if (chars[i - 1] == '(') {
                    if (i - 1 > 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else { // 处理边界
                        dp[i] = 2;
                    }
                }

                // 如果是右括号，情况稍微复杂点，我们要倒回“i - dp[i - 1]”位置，看“这个位置“前面是不是左括号，如果是左括号，还得加上左括号前面的一个位置的有效括号长度
                if (chars[i - 1] == ')') {
                    if (i - 1 - dp[i - 1] > 0 && chars[i - 1 - dp[i - 1]] == '(') {
                        dp[i] = dp[i - 1] + 2 + dp[i - 2 - dp[i - 1]];
                    } else if (i - 1 - dp[i - 1] == 0 && chars[i - 1 - dp[i - 1]] == '(') { // 写着很复杂，其实只是在处理边界条件
                        dp[i] = dp[i - 1] + 2;
                    }
                }

            }
        }

        // 轮询dp数组，找出最长的有效括号字串
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int ans;
        ans = longestValidParentheses(")()())");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("(()()");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("((()()");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("()(())");
        Assert.assertEquals(6, ans);

        ans = longestValidParentheses("()((())");
        Assert.assertEquals(4, ans);

    }

}

