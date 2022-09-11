package com.pc.LeetCode.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.15. 最长单词
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 * <p>
 * 示例：
 * <p>
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 * <p>
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 */
public class Interview17_15 {

    // 和字典序关系不大，主要思路带map深度递归
    public String longestWord(String[] words) {
        Map<String, Boolean> map = new HashMap<>();

        for (String word : words) {
            map.put(word, Boolean.TRUE);
        }


        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o2.length() == o1.length()) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o2.length(), o1.length());
            }
        });

        for (String word : words) {
            boolean composed = dfs(0, word, map);
            if (composed) {
                return word;
            }
        }
        return "";
    }

    private boolean dfs(int prefixStart, String word, Map<String, Boolean> map) {
        for (int i = prefixStart; i < word.length() - 1; i++) {
            // 判断前缀组成的单词是否在map里面
            Boolean prefixExist = map.getOrDefault(word.substring(prefixStart, i + 1), false);
            // 判断后缀组成的单词是否在map里面（可以优化，如果前缀都不在，后缀也没必要判读）
            Boolean suffixExist = map.getOrDefault(word.substring(i + 1), false);
            map.put(word.substring(prefixStart, i + 1), prefixExist);
            map.put(word.substring(i + 1), suffixExist);
            if (prefixExist && suffixExist) {
                return true;
            } else if (prefixExist) {
                boolean composed =  dfs(i + 1, word, map);
                if (composed) {
                    return composed;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Interview17_15 interview17_15 = new Interview17_15();
        String ans;

        ans = interview17_15.longestWord(new String[]{"aa", "a"});
        System.out.println("ans:" + ans);

        ans = interview17_15.longestWord(new String[]{"apple", "dog", "appledog"});
        System.out.println("ans:" + ans);

        ans = interview17_15.longestWord(new String[]{"cat","banana","dog","nana","walk","walker","dogwalker"});
        System.out.println("ans:" + ans);

        ans = interview17_15.longestWord(new String[]{"qlmql","qlmqlmqqlqmqqlq","mqqlqmqqlqmqqlq","mqqlq","mqqlqlmlsmqq","qmlmmmmsm","lmlsmqq","slmsqq","mslqssl","mqqlqmqqlq"});
        System.out.println("ans:" + ans);
    }
}
