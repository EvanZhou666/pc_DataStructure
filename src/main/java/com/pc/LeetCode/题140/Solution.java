package com.pc.LeetCode.题140;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 140. 单词拆分 II
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 * <p>
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 */
public class Solution {

    List<String> ans;
    Trie root;
    private int maxLen;

    /**
     * 回溯算法，trie用来判断在s串的i位置是否是字典表中某个单词的结尾部分
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {

        this.ans = new ArrayList<>(16);

        Trie trie = new Trie();

        root = trie;

        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
            trie.insert(word);
        }

        List<String> path = new ArrayList<>(16);
        dfs(s, 0, trie, path, 0);
        return ans;
    }

    /**
     * 计算s的子串[offset,s.length）的拆分情况
     *
     * @param s
     * @param offset      偏移量，子串的起始位置
     * @param currentNode 当前字典树节点
     * @param path        存储拆分情况
     * @param length      已经拆分了多少个字符
     */
    private void dfs(String s, int offset, Trie currentNode, List<String> path, int length) {

        // 递归终止条件 有点难想到
        if (offset >= s.length()) {
            // length == s.length() 判断s是否已经全部被拆分完了，不能剩下
            if (!path.isEmpty() && length == s.length()) {
                StringBuilder builder = new StringBuilder();
                for (String s1 : path) {
                    builder.append(" ").append(s1);
                }
                ans.add(builder.substring(1));
            }
            return;
        }

        // 无法拆分
        if (currentNode.children[s.charAt(offset) - 'a'] == null) {
            return;
        }
        currentNode = currentNode.children[s.charAt(offset) - 'a'];
        // 可以拆分 则进行拆分
        if (currentNode.finished) {
            path.add(currentNode.str);
            dfs(s, offset + 1, root, path, length + currentNode.str.length());
            path.remove(path.size() - 1);
        }
        // 不拆分或不能拆分的场景
        dfs(s, offset + 1, currentNode, path, length);
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


    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> ans;
        ans = solution.wordBreak("catsanddog", new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(JSON.toJSONString(ans));

        ans = solution.wordBreak("pineapplepenapple", new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(JSON.toJSONString(ans));

        ans = solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
        System.out.println(JSON.toJSONString(ans));
    }

}

