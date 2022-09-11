package com.pc.LeetCode.题648;


import java.util.List;

/**
 * https://leetcode.cn/problems/replace-words/
 * 648. 单词替换<br/>
 * <img src="./img.png">
 */
public class Solution {

    /**
     * 基于数组实现的前缀树
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();

        String[] words = sentence.split(" ");
        for (String s : dictionary) {
            trie.insert(s);
        }

        for (int i = 0; i < words.length; i++) {
            String dictRoot = trie.searchDictRoot(words[i]);
            if (dictRoot != null) {
                words[i] = dictRoot;
            }
        }

        return String.join(" ", words);

    }

    static class Trie {

        private char val;
        private Trie[] children;
        private boolean finished;
        private String str;

        Trie() {
            children = new Trie[26];
        }

        public void insert(String word) {
            Trie node = this;

            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    node.children[word.charAt(i) - 'a'] = new Trie();
                }

                node = node.children[word.charAt(i) - 'a'];
                node.val = word.charAt(i);
                if (i == word.length() - 1) {
                    node.finished = true;
                    node.str = word;
                }
            }
        }

        // 查找词根
        public String searchDictRoot(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    return null;
                }

                node = node.children[word.charAt(i) - 'a'];
                if (node.finished) {
                    return node.str;
                }
            }
            return null;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Trie trie = new Trie();
        trie.insert("bbab");
        trie.searchDictRoot("bbab");
    }

}