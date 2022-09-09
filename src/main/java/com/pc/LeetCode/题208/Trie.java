package com.pc.LeetCode.题208;

import com.pc.LeetCode.common.Assert;

/**
 * Trie本质上就只是一颗多叉树而已。
 */
public class Trie {

    // 当前节点字符值 a,b,c... 也可能不存储，因为可以根据数组下标计算得出
    private char val;

    // 子节点👉可以看作是新的Trie
    private Trie[] children;

    // 字符串结束标识
    private boolean isFinish;

    // 字符串结束时候才有值，存储整个字符串链路的值，也就是insert方法插入的单词值
    private String str;

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
            }
        }

    }

    public boolean search(String word) {

        // 可以递归查找，也可以for循环
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 97] == null) {
                return false;
            }
            node = node.children[word.charAt(i) - 97];
        }
        return node.isFinish;

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
        Trie trie = new Trie();
        trie.insert("apple");
        boolean a = trie.search("apple");
        Assert.assertIsTrue(a);
        a = trie.search("app");
        Assert.assertIsFalse(a);
        a = trie.startsWith("app");
        Assert.assertIsTrue(a);
//        a = trie.startsWith("ab");
//        Assert.assertIsTrue(a);
    }

}
