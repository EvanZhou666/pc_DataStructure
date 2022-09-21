package com.pc.LeetCode.题38;

/**
 * 38. 外观数列
 */
public class Solution {
    public static String countAndSay(int n) {
        String[] preResult = new String[n];
        preResult[0] = "1";

        for (int i = 1; i < n; i++) {
            preResult[i] = countSay(preResult[i - 1]);
        }

        return preResult[n-1];
    }

    private static String countSay(String s) {
        StringBuilder builder = new StringBuilder();
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(pre)) {
                builder.append(i - pre).append(s.charAt(pre));
                pre = i;
            }

            if (i + 1 == s.length()) {
                builder.append(i - pre + 1).append(s.charAt(i));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = countAndSay(4);
        System.out.println(s);
    }
}
