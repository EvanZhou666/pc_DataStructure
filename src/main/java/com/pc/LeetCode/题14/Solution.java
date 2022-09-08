package com.pc.LeetCode.题14;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 */
public class Solution {

    /**
     * 【字符串系列】模拟法，先找出最短的字符串，在以它为标准逐位比较
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {

        int end = 0;
        int minLenStrIndex = 0;
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < strs[minLenStrIndex].length()) {
                minLenStrIndex = i;
            }
        }


        boolean isEquals = true;
        for (int i = 0; i < strs[minLenStrIndex].length() && isEquals; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[minLenStrIndex].charAt(i) != strs[j].charAt(i)) {
                    isEquals = false;
                    break;
                }
            }
            if (isEquals) {
                end++;
            }
        }
        return strs[minLenStrIndex].substring(0, end);

    }
}
