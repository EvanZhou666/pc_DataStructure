package com.pc.LeetCode.题139;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class Solution {

    public static boolean wordBreak(String s, List<String> wordDict) {

        Map<String, Integer> map = new HashMap<>();

        for (String word : wordDict) {
            map.put(word, word.length());
        }
        Boolean[] memo = new Boolean[s.length()];
        boolean canbeBreak = dfs(s, 0, map, memo);
        return canbeBreak;
    }

    private static boolean dfs(String s, int i, Map<String, Integer> map, Boolean[] memo) {

        if ( i >= s.length()) {
            return true;
        }

        if (memo[i] != null) {
            return memo[i];
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (s.startsWith(entry.getKey(), i)) {
                 boolean canBeBreak = dfs(s, i + entry.getValue(), map, memo);
                 if (canBeBreak) {
                     return true;
                 }
            }
        }
        memo[i] = false;
        return false;
    }

    public static void main(String[] args) {
        boolean ans;

        ans = wordBreak("leetcode", Arrays.asList("leet", "code"));
        System.out.println(ans);

        ans = wordBreak("applepenapple", Arrays.asList("apple", "pen"));
        System.out.println(ans);

        ans = wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(ans);
    }
}
