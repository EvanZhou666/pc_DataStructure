package com.pc.LeetCode.剑指offer48;

import com.pc.LeetCode.common.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
public class Solution {

    public static int lengthOfLongestSubstring(String s) {

        int l = 0, r = 0;

        Set<Character> unique = new HashSet<>();

        char[] chars = s.toCharArray();

        int length = chars.length;

        int max = 0;
        while (r < length) {

            // 如果不能加入新元素，说明已经遇到了重复元素
            if (!unique.add(chars[r])) {
                // 1. 左窗口右移1格，看看能否加入
                unique.remove(chars[l]);
                l++;
            } else {
                // 如果新元素正常加入，那我们伸长窗口
                r++;
                max = Math.max(max, unique.size());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int length = lengthOfLongestSubstring("abcabcbb");
        Assert.assertEquals(length, 3);

        length = lengthOfLongestSubstring("pwwkew");
        Assert.assertEquals(length, 3);

    }

}
