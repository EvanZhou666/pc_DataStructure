package com.pc.LeetCode.题394;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 */
public class Solution {

    public static String decodeString(String s) {

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> letterStack = new ArrayDeque<>();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int k = i;
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                while (k + 1 < s.length() && Character.isDigit(s.charAt(k + 1))) {
                    num = 10 * num + Character.getNumericValue(s.charAt(k + 1));
                    k++;
                }
                numStack.push(num);
                i = k;
            } else if ('[' == c) {
                letterStack.push("[");
            } else if (Character.isAlphabetic(c)) {
                letterStack.push(String.valueOf(c));
            } else {
                int operation = 0;
                if (!numStack.isEmpty()) {
                    operation = numStack.pop();
                }

                StringBuilder builder = new StringBuilder();
                while (!letterStack.isEmpty() && letterStack.peek() != "[") {
                    builder.insert(0, letterStack.pop());
                }

                if (!letterStack.isEmpty() && letterStack.peek() == "[") {
                    letterStack.pop();
                }

                String str = builder.toString();
                for (int j = 0; j < operation - 1; j++) {
                    builder.insert(0, str);
                }
                letterStack.push(builder.toString());
            }
            i++;
        }

        StringBuilder builder = new StringBuilder();
        while (!letterStack.isEmpty()) {
            builder.insert(0, letterStack.pop());
        }

        return builder.toString();
    }

}
