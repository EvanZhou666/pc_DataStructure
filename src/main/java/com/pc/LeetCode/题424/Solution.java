package com.pc.LeetCode.题424;

import com.pc.LeetCode.common.Assert;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-repeating-character-replacement/
 * 424. 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * <p>
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 */
public class Solution {

    public static int characterReplacement(String s, int k) {
        // 最长重复字符串长度
        int maxLen = 0;
        int l = 0, r = 0;
        int chance = k;
        Map<Character, Integer> frequence = new HashMap<>();

        while (r < s.length()) {
            frequence.put(s.charAt(r), frequence.getOrDefault(s.charAt(r), 0) + 1);
            Character maxChar = getMaxFrequence(frequence);
            int maxFre = frequence.get(maxChar);

            if (r - l + 1 == maxFre) {
                r++;
            } else {
                // 把滑动窗口内的字符替换为出现次数最多的字符，这样能省下k值
                int needChange = r - l + 1 - maxFre;
                if (needChange <= k) {
                    r ++;
                } else {
                    maxLen = Math.max(maxLen, r - l);
                    frequence.put(s.charAt(l), frequence.get(s.charAt(l)) - 1);
                    l ++;
                    r ++;
                }
            }

        }
        return Math.max(maxLen, r - l);
    }

    /**
     * 获取滑动窗口内，出现次数最多的字符
     * @param frequence
     * @return
     */
    private static Character getMaxFrequence(Map<Character, Integer> frequence) {
        Set<Character> keySet = frequence.keySet();
        ArrayList<Character> list = new ArrayList<>(keySet);
        Collections.sort(list, Comparator.comparingInt(frequence::get));
        return list.get(list.size() - 1);
    }


    public static void main(String[] args) {
        int ans = 0;

        ans = characterReplacement("abc", 1);
        Assert.assertEquals(ans, 2);

        ans = characterReplacement("ABAB", 2);
        Assert.assertEquals(ans, 4);

        ans = characterReplacement("AABABBA", 1);
        Assert.assertEquals(ans, 4);

        ans = characterReplacement("ABBB", 2);
        Assert.assertEquals(ans, 4);

    }
}
