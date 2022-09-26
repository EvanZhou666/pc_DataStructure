package com.pc.LeetCode.题76;

import com.pc.LeetCode.common.GoodQuestion;

import java.util.*;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 提示：
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 */
@GoodQuestion(type = "滑动窗口") // 经典题目，值得重做
public class Solution {

    public String minWindow(String s, String t) {

        int minLen = Integer.MAX_VALUE;
        String ans = "";
        Map<Character, Integer> frequence = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            frequence.put(t.charAt(i), frequence.getOrDefault(t.charAt(i), 0) + 1);
        }

        int l = 0, r = 0;

        while (r < s.length()) {
            if (frequence.get(s.charAt(r)) != null) {
                frequence.put(s.charAt(r), frequence.get(s.charAt(r)) - 1);
            }

            while (isFinish(frequence)) {
                if (frequence.get(s.charAt(l)) != null) {
                    frequence.put(s.charAt(l), frequence.get(s.charAt(l)) + 1);
                }
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    ans = s.substring(l, r + 1);
                }
                l++;
            }

            r++;
        }
        return ans;
    }

    private boolean isFinish(Map<Character, Integer> frequence) {
        for (Map.Entry<Character, Integer> entry : frequence.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        s = solution.minWindow("a", "aa");
        System.out.println(s);
    }
}
