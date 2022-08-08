package com.pc.LeetCode.题1763;

import java.util.HashMap;
import java.util.Map;

/**
 * 1763. 最长的美好子字符串
 * <p>
 * 当一个字符串 s包含的每一种字母的大写和小写形式 同时出现在 s中，就称这个字符串s是 美好 字符串。比方说，"abABB"是美好字符串，因为'A' 和'a'同时出现了，且'B' 和'b'也同时出现了。然而，"abA"不是美好字符串因为'b'出现了，而'B'没有出现。
 * <p>
 * 给你一个字符串s，请你返回s最长的美好子字符串。如果有多个答案，请你返回最早出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-nice-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public static String longestNiceSubstring(String s) {
        String res = "";

        // 初始化字符计数器
        Map<Character, Integer> upperCase = new HashMap<>();
        Map<Character, Integer> lowerCase = new HashMap<>();
        for (int i = 65; i <= 90; i++) {
            upperCase.put((char) i, 0);
        }

        for (int i = 97; i <= 122; i++) {
            lowerCase.put((char) i, 0);
        }

        int length = s.toCharArray().length;

        // 根据题意，完美字符串至少是两个字符以上的字符串
        if (length < 2) {
            return res;
        }


        // 初始化滑动指针
        int l =0;
        int r =0;


        char[] chars = s.toCharArray();
        // 非固定大小的滑动窗口，且窗口需要不断扩大，才能找到最终解
        while (r< length) {
            // daBAbDs

            // 更新滑动窗口状态
            if (upperCase.containsKey(chars[r])) {
                upperCase.put(chars[r], upperCase.get(chars[r]) + 1);
            } else {
                lowerCase.put(chars[r], lowerCase.get(chars[r]) + 1);
            }

            // 检查滑动窗口是否满足条件
            if (!checkIsPerfectString(upperCase, lowerCase)) {
                l ++;
            } else {
                r ++;
            }

            // 窗口

        }


        return res;
    }

    private static boolean checkIsPerfectString(Map<Character, Integer> upperCase, Map<Character, Integer> lowerCase) {
        for (Character character : lowerCase.keySet()) {
            if (!lowerCase.get(character).equals(upperCase.get((character-26)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println((int) 'A');

        System.out.println((int) 'Z');

        System.out.println((int) 'a');
        System.out.println((int) 'z');

        longestNiceSubstring("");

    }
}
