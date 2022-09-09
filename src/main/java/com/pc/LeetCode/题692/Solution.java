package com.pc.LeetCode.题692;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 */
public class Solution {

    /**
     * 不想写了 随便搞的 本质上不是Trie系列的题
     * 本题用哈希表后者优先队列更佳
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }


        Map<String, Integer> cnt = new HashMap<String, Integer>();

        for (String word : words) {
            int frequence = trie.search(word).frequence;
            System.out.println(word+":"+ frequence);
            cnt.put(word, frequence);
        }



        List<String> rec = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        Collections.sort(rec, new Comparator<String>() {
            public int compare(String word1, String word2) {
                return cnt.get(word1) == cnt.get(word2) ? word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1);
            }
        });
        return rec.subList(0, k);

    }

}

class Trie {

    // 当前节点字符值 a,b,c... 也可能不存储，因为可以根据数组下标计算得出
    private char val;

    // 子节点👉可以看作是新的Trie
    private Trie[] children;

    // 字符串结束标识
    private boolean isFinish;

    // 字符串结束时候才有值，存储整个字符串链路的值，也就是insert方法插入的单词值
    private String str;

    public int frequence;

    public Trie() {
        children = new Trie[26];
    }

    public Trie(char val) {
        this.val = val;
        children = new Trie[26];
    }

    public void insert(String word) {

        Trie node = this;
        for (int i = 0; i < word.length(); i++) {

            //子节点不存在。创建一个新的子节点，记录在children 数组的对应位置上
            if (node.children[word.charAt(i) - 97] == null) {
                node.children[word.charAt(i) - 97] = new Trie(word.charAt(i));
            }

            // 然后沿着指针移动到子节点，继续搜索下一个字符。
            // 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
            node = node.children[word.charAt(i) - 97];
            if (i == word.length() - 1) {
                node.isFinish = true;
                node.str = word;
                node.frequence = node.frequence + 1;
            }
        }

    }

    public Trie search(String word) {

        // 可以递归查找，也可以for循环
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 97] == null) {
                return null;
            }
            node = node.children[word.charAt(i) - 97];
        }
        return node.isFinish ? node : null;

    }

    public boolean startsWith(String prefix) {
        // 可以递归查找，可以for循环
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.children[prefix.charAt(i) - 97] == null) {
                return false;
            }
            node = node.children[prefix.charAt(i) - 97];
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2);
    }
}
