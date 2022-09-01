package com.pc.LeetCode.题151;

/**
 * 151. 反转字符串中的单词
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 */
public class Solution {

    public static String reverseWords(String s) {

        // 1. 去除前导后导空格
        String ss = s.trim();
        StringBuilder sb = new StringBuilder();
        int i = 0, j = ss.length()-1;
        // 2. 字符串中间存在多个空格，合并为1个
        while (i <= j) {
            char c = ss.charAt(i);
            if (c !=' ') {
                sb.append(c);
                // 这里处理得很巧妙，一直在想如果有多个空格怎么压缩为1个空格。如果前一个不是空字符，则当前空格字符可以追加进去
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            i ++;
        }

        char [] chars = sb.toString().toCharArray();
        i = 0; j = chars.length - 1;
        // 3. 先将字符串整个反转
        reverseWord(chars, i, j);

        // 4. 再将每个单词反转
        int start = i;
        int m = i;
        while (m < j) {
            if (chars[m] == ' ') {
                reverseWord(chars, start, m - 1);
                start = m + 1;
            }
            m ++;
        }
        reverseWord(chars, start, j);
        return new String(chars);
    }

    private static void reverseWord(char[] chars, int i, int j) {
        char temp;
        // 1. 先将整个数组反转
        while (i < j) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i ++;
            j--;
        }
    }

    public static void main(String[] args) {
        String ans = reverseWords("a good   example");
        System.out.println(ans);
    }

}
