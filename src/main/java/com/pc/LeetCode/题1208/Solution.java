package com.pc.LeetCode.题1208;


import com.pc.LeetCode.common.Assert;

/**
 * 题1208.尽可能使字符串相等；
 */
public class Solution {

    public static int equalSubstring(String s, String t, int maxCost) {
        // 最长重复字符串长度
        int maxLen = 0;
        int l = 0, r = 0;
        // 剩余的cost值
        int k = maxCost;

        while (r < s.length()) {
            int cost = Math.abs(s.charAt(r) - t.charAt(r));
            if (cost <= k) {
                k = k - cost;
                r ++;
            } else {
                maxLen = Math.max(maxLen, r - l);
                k += Math.abs(s.charAt(l) - t.charAt(l));
                l ++;
            }
        }
        return Math.max(maxLen, r - l);
    }

    public static void main(String[] args) {
        int ans ;
        ans = equalSubstring("abcd", "bcdf", 3);
        Assert.assertEquals(ans, 3);
    }

}
