package com.pc.LeetCode.题720;

import com.pc.前缀树.Trie;

import java.util.TreeSet;

public class Solution {

    /**
     * 非最佳答案，需要优化
     * 优化点一：检查单词的前缀是否在字典书中，O(K^2)，能不能修改Trie的标准模板，在O(K)内搞定
     * 优化点2：优化空间，能不能不用treemap
     * @param words：
     * @return
     */
    public String longestWord(String[] words) {

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }

        TreeSet<String> treeSet = new TreeSet<>();
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            boolean allPrefixInExsits = true;
            // 检查所有的前缀是否出现在字典表里
            for (int j = 1; j < words[i].length(); j++) {
                if (!trie.search(words[i].substring(0, j))) {
                    allPrefixInExsits = false;
                    break;
                }
            }

            if (allPrefixInExsits) {
                if (words[i].length() == maxLength) {
                    treeSet.add(words[i]);
                } else if (words[i].length() > maxLength) {
                    maxLength = words[i].length();
                    treeSet.clear();
                    treeSet.add(words[i]);
                }
            }

        }

        return treeSet.isEmpty() ? "" : treeSet.first();

    }

}
