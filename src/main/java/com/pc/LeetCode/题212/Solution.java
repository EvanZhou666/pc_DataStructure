package com.pc.LeetCode.题212;

import java.util.*;

/**
 *
 * <a href="https://leetcode.cn/problems/word-search-ii/">212. 单词搜索 II</a><br/>
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。<br/>
 *
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。<br/>
 *
 * <img src="https://assets.leetcode.com/uploads/2020/11/07/search1.jpg"/> <br/>
 *
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"] <br/>
 * 输出：["eat","oath"]  <br/>
 *
 */
public class Solution {

    // 方向数组，矩阵搜索常用套路
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * copy自官方的答案，加了一些注释，加快刷题速度
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {

        // 官方喜欢用基于哈希表实现的字典树，我个人喜欢用数组实现
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }

    /**
     *
     * @param board
     * @param now
     * @param i1 行
     * @param j1 列
     * @param ans
     */
    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        // 终端条件: 核心
        // 如果新增加的board[i1][j1]节点不是上一个trie节点的子节点再怎么搜索下去也不会是words中某个单词。 这一步“剪枝”十分精彩。
        // 或者 board[i1][j1]已经访问过，意味着我从board[i1][j1]出发搜索，又回到了board[i1][j1]，因此这是无意义的一次搜索。
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        // 字典树存在以board[i1][j1]开头的子树
        now = now.children.get(ch);

        // 如果word不为空，代表已经在字典树中搜索到了word中的某个元素，因此加入结果集
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }

        // 设置为‘#’ 代表已经访问过，放置重复遍历
        board[i1][j1] = '#';

        // 方向数组，遍历当前节点的上下左右4个方向
        for (int[] dir : dirs) {
            // 新节点的坐标
            int i2 = i1 + dir[0], j2 = j1 + dir[1];

            // 新节点边界合法性校验，如果合法，继续深度遍历
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }

        // board[i1][j1]的上下左右的4个方向搜索结束，清除标记，代表在后面的搜索中可以被其它节点访问到
        board[i1][j1] = ch;
    }
}

// 字典树
class Trie {
    // word结束的时候，才有值。常用套路，暗示word是否已经结束了
    String word;
    Map<Character, Trie> children;
    // 没有用到的变量
    boolean isWord;

    public Trie() {
        this.word = "";
        this.children = new HashMap<Character, Trie>();
    }

    public void insert(String word) {
        Trie cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Trie());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }

}
