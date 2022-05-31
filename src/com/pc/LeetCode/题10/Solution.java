package com.pc.LeetCode.题10;

import com.pc.LeetCode.common.Assert;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        char[] chars = s.toCharArray();
        char[] patterns = p.toCharArray();

        int index = 0;

        Character lastChar = null;

        for (char pattern : patterns) {

           if (pattern == '.') {
               if (index >= chars.length) {
                   return false;
               }
               lastChar = '.';
               index ++;
           } else if (pattern == '*') {
               while (index < chars.length) {
                   if (lastChar == '.') {
                       index ++;

                   } else {
//                   上1个字符是字母，那么当前字符必须也是字母
                       if (chars[index] != lastChar) {
                           return false;
                       }
                       lastChar = chars[index];
                       index ++;
                   }
               }
               return true;

           } else {
               if (index >= chars.length || pattern != chars[index]) {
                   return false;
               }
               lastChar = chars[index];
               index++;
           }
        }
        return index >= chars.length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        Assert.assertIsFalse(solution.isMatch("aa", "a"));
//        Assert.assertIsTrue(solution.isMatch("aa", "a*"));
//        Assert.assertIsTrue(solution.isMatch("ab", ".*"));
        Assert.assertIsTrue(solution.isMatch("aab", "c*a*b"));
    }
}
