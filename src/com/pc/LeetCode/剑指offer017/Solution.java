package com.pc.LeetCode.剑指offer017;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 * <p>
 * 给定两个字符串 s 和t 。返回 s 中包含t的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * <p>
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/M1oyTv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public static String minWindow(String s, String t) {
        char[] tArray = t.toCharArray();

        Map<Character, Integer> tMap = new HashMap<>();

        Map<Character, Integer> windowsMap = new HashMap<>();

        for (char c : tArray) {
            Integer val = tMap.getOrDefault(c, 0);
            tMap.put(c, val+1);
        }

        char[] sChars = s.toCharArray();

        // 初始化滑动窗口指针
        int l = 0;
        int r = 0;
        int length = s.length();


        int bestLength = Integer.MAX_VALUE;
        int bestLeft = -1;
        while (r < length) {
            // 不满足条件。滑动窗口继续扩大
            Integer rightValue = windowsMap.getOrDefault(sChars[r], 0);
            windowsMap.put(sChars[r], rightValue + 1);

            // 检查窗口是否满足条件
            while (compareMapEquals(tMap, windowsMap)) {
                // 记录下最短路径，不断优化答案
                if (r - l + 1 < bestLength) {
                    bestLeft = l;
                    bestLength = r - l + 1;
                }
                Integer leftValue = windowsMap.get(sChars[l]);
                windowsMap.put(sChars[l], leftValue - 1);
                l++;
            }
            r++;

        }
        return bestLeft != -1 ? s.substring(bestLeft, bestLeft + bestLength) : "";
    }

    /**
     * 判断map1是否是map2的子集
     *
     * @param map1
     * @param map2
     * @return
     */
    private static boolean compareMapEquals(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (Character key : map1.keySet()) {
            if (map1.get(key) > (map2.getOrDefault(key, 0))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String bestResult = minWindow("ADOBECODEBANC", "ABC");
        Assert.assertEquals(bestResult, "BANC");

        String bestResult2 = minWindow("a", "a");
        Assert.assertEquals(bestResult2, "a");

        String bestResult3 = minWindow("a", "aa");
        Assert.assertEquals(bestResult3, "");

        String bestResult4 = minWindow("cabwefgewcwaefgcf", "cae");
        Assert.assertEquals(bestResult4, "cwae");
    }

}
