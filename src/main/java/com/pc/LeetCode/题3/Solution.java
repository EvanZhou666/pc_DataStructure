package com.pc.LeetCode.题3;

import com.pc.LeetCode.common.GoodQuestion;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 滑动窗口系列经典题目
 */
@GoodQuestion(type = "滑动窗口")
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;
        int maxLen = 0;
        Map<Character, Integer> frequence = new HashMap<>();

        while (r < s.length()) {
            if (frequence.getOrDefault(s.charAt(r), 0) != 0) {
                maxLen = Math.max(r - l, maxLen);
                frequence.put(s.charAt(l), frequence.get(s.charAt(l)) - 1);
                l++;
            } else {
                frequence.put(s.charAt(r), 1);
                r++;
            }
        }

        return Math.max(maxLen, r - l);
    }

}
