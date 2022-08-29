package com.pc.LeetCode.题139;

import com.pc.LeetCode.common.Assert;

import java.util.*;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class Solution3 {

    public static boolean wordBreak(String s, List<String> wordDict) {
        // 定义dp[i]为[0,i]区间内字符串是否能拆分. 如果能拆分 dp[i] = true； 不能拆分dp[i] = false
        // 定义check[i,j]为 字符串[i,j]区间的子字符串是否是字典内的单词
        // 令 n = s.length - 1 则
        // dp[n] = ( dp[0] && check[1,n] ) || ( check[1] && check[2,n] ) || ( check[2] && check[3,n] ) ||
        //          (dp[j] && check[j+1,n]) ... || (dp[n-1] && check[n,n]) || check[0,n]

        // 例如 dp[0] = true
        // 例如 dp[1] = (dp[0] && check[1,1] ) || check(0,1)
        // 例如 dp[2] = (dp[0] && check[1,2] ) || (dp[1] && check[2,2]) || check(0,2)
        // 例如 dp[3] = (dp[0] && check[1,3] ) || (dp[1] && check[2,3]) || (dp[2] && check[3,3]) || check(0,3)
        // ...
        // dp[n] = ...

        Map<String, Integer> map = new HashMap<>();

        for (String word : wordDict) {
            map.put(word, word.length());
        }

        int n = s.length();
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == i) {
                    dp[i] = map.containsKey(s.substring(0, i + 1));
                } else {
                    dp[i] = dp[j] && map.containsKey(s.substring(j + 1, i + 1));
                }
                // dp[i]为true终止循环
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[n-1];
    }


    // 还是方法1的思路，做一些代码上的优化
    public static boolean wordBreak2(String s, List<String> wordDict) {
        // 定义dp[i]为[0,i]区间内字符串是否能拆分. 如果能拆分 dp[i] = true； 不能拆分dp[i] = false
        // 定义check[i,j]为 字符串[i,j]区间的子字符串是否是字典内的单词
        // 令 n = s.length - 1 则
        // dp[n] = ( dp[0] && check[1,n] ) || ( check[1] && check[2,n] ) || ( check[2] && check[3,n] ) ||
        //          (dp[j] && check[j+1,n]) ... || (dp[n-1] && check[n,n]) || check[0,n]

        // 例如 dp[0] = true
        // 例如 dp[1] = (dp[0] && check[1,1] ) || check(0,1)
        // 例如 dp[2] = (dp[0] && check[1,2] ) || (dp[1] && check[2,2]) || check(0,2)
        // 例如 dp[3] = (dp[0] && check[1,3] ) || (dp[1] && check[2,3]) || (dp[2] && check[3,3]) || check(0,3)
        // ...
        // dp[n] = ...

        Map<String, Integer> map = new HashMap<>();

        for (String word : wordDict) {
            map.put(word, word.length());
        }

        int n = s.length();
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < n; i++) {
            // 先检查[0,i]区间是否能够直接匹配的
            dp[i] = map.containsKey(s.substring(0, i + 1));
            if (!dp[i]) {
                for (int j = 0; j < i; j++) {
                    dp[i] = dp[j] && map.containsKey(s.substring(j + 1, i + 1));
                    // dp[i]为true终止循环
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }

        return dp[n-1];
    }


    public static void main(String[] args) {
        boolean ans;

        ans = wordBreak2("leetcode", Arrays.asList("leet", "code"));
        System.out.println(ans);
        Assert.assertIsTrue(ans);

        ans = wordBreak2("applepenapple", Arrays.asList("apple", "pen"));
        System.out.println(ans);
        Assert.assertIsTrue(ans);

        ans = wordBreak2("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(ans);
        Assert.assertIsFalse(ans);
    }
}
