package com.pc.LeetCode.题28;

import com.pc.LeetCode.common.Assert;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 * 说明：
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 */
public class Solution {

    // ps：看官方题解用的是KMP算法，算了，用固定的滑动窗口+Equals 就这样吧
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }

        int length = needle.length();
        int l = 0;
        int r = l + length - 1;

        while (r < haystack.length()) {
            if (haystack.substring(l, r + 1).equals(needle)) {
                return l;
            }
            l++;
            r++;
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.strStr("hello", "ll");
        int i2 = solution.strStr("aaaaa", "bba");
        int i3 = solution.strStr("a", "a");
        Assert.assertEquals(i, 2);
        Assert.assertEquals(i2, -1);
        Assert.assertEquals(i3, 0);
    }
}
