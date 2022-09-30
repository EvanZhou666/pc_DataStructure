package com.pc.LeetCode.题140;


import com.alibaba.fastjson.JSON;
import com.pc.LeetCode.common.GoodQuestion;

import java.util.*;

/**
 * 140. 单词拆分 II
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 * <p>
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 */
@GoodQuestion(type = "回溯算法")
public class Solution2 {

    /**
     * 解法2要比解法1，思路更加清晰
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {

        List<String> ans = new ArrayList<>(16);

        Map<String, Integer> map = new HashMap<>();

        for (String word : wordDict) {
            if (!"".equals(word)) {
                map.put(word, word.length());
            }
        }
        Map<Integer, List<List<String>>> memo = new HashMap<>(s.length() / 2);
        List<List<String>> dfs = dfs(s, 0, map, memo);
        for (List<String> df : dfs) {
            ans.add(String.join(" ", df));
        }
        return ans;
    }

    /**
     * 返回字符串s[i,len)区间的字符拆分情况
     * @param s
     * @param i
     * @param map
     * @param memo
     * @return
     */
    private static List<List<String>> dfs(String s, int i, Map<String, Integer> map, Map<Integer, List<List<String>>> memo) {

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        if (i >= s.length()) {
            List<String> arrayList = new ArrayList<>();
            List<List<String>> ret = new ArrayList<>();
            // 这里很细节，插入一个空的list。代表子串已经匹配完毕了，刚开始就是这些边界条件想不明白
            // 或者也可以想象成[s.length,+无穷)的字串由""组成
            ret.add(arrayList);
            return ret; // ret = [[]]
        }

        List<List<String>> dfs = new ArrayList<>();
        // 遍历字符串以entry.getKey为前缀，检查剩下的字符串能否拆分，则找到一种可行解，遍历完的时候，则当前规模问题解决。
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (s.startsWith(entry.getKey(), i)) {
                List<List<String>> list = dfs(s, i + entry.getValue(), map, memo);
                // 如果字串不能够被拆分，那么返回的list是[]，这种情况是不会进入for循环的.注意[] 和 [[]]是两种不同的场景。[[]]代表最小规模的子串已经解决完毕了，并找到一个可行解
                // 如果字串能够被拆分，那么返回的可能是类似于[[x,xx,xx],[xxx,xx,x]]
                for (List<String> l : list) {
                    // 要拷贝新的数组
                    List<String> newL = new ArrayList<>(l);
                    newL.add(0, entry.getKey());
                    dfs.add(newL);
                }
            }
        }
        memo.put(i, dfs);
        return dfs;
    }

    public static void main(String[] args) {
        List<String> ans;
        Solution2 solution = new Solution2();
        ans = solution.wordBreak("a", new ArrayList<>(Arrays.asList("")));
        ans = solution.wordBreak("catsanddog", new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
//        ans = solution.wordBreak("catsanddog", new ArrayList<>(Arrays.asList("cats", "and", "dog")));
//        ans = solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
        System.out.println(JSON.toJSONString(ans));
    }
}

