package com.pc.LeetCode.题5.最长回文子串;


/**
 * @author Evan
 * @since 2020/9/7 14:39
 */
public class Solution {
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        String maxStr = "";
        for (int i = 0; i < chars.length; i++) {
            // 以该节点和该节点的下一个节点之间的点（虚拟的点，实际不存在的情况），不断向两边延伸，生成的偶数回文串
            String s1 = extend(i, i, s, chars);
            // 以该节点为中心，不断向两边延伸，生成的奇数回文串
            String s2 = extend(i, i + 1, s, chars);
            if (Math.max(s1.length(), s2.length()) > maxStr.length()) {
                if (s1.length()>s2.length()) {
                    maxStr = s1;
                } else {
                    maxStr = s2;
                }
            }
        }
        return maxStr;
    }

    /**
     * 伸展回文串
     * @param left
     * @param right
     * @param s
     * @param chars
     * @return
     */
    private static String extend(int left, int right, String s, char[] chars) {
        while (left >= 0 && right < s.length() && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public static void main(String[] args) {
        String s = "adad";

        String s1 = longestPalindrome(s);
        System.out.println(s1);
    }
}
