package com.pc.LeetCode.题139;

import java.util.*;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class Solution2 {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        // 定义dp[i] 表示字符串s前i个字符组成的字符串 s[0..i-1]是否能被空格拆分成若干个字典中出现的单词。
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }
    public static void main(String[] args) {
        boolean ans;

//        ans = wordBreak("leetcode", Arrays.asList("leet", "code"));
//        System.out.println(ans);

//        ans = wordBreak("applepenapple", Arrays.asList("apple", "pen"));
//        System.out.println(ans);

        ans = wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(ans);
    }
}
