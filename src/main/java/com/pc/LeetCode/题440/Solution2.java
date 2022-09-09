package com.pc.LeetCode.题440;

import com.google.common.base.Stopwatch;
import com.pc.LeetCode.common.Assert;

public class Solution2 {

    private static int seq = 0;
    private static int ans = 0;
    private static boolean stop = false;

    /**
     * 在内存中建立一个字典树，依旧还是超时
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        ans = 0;
        seq = 0;
        stop = false;
        Trie trie = new Trie();
        dfsInsert(trie, n, k);
        return ans;
    }

    private void dfsInsert(Trie trie, int n, int k) {

        if (trie.number > n) {
            seq--;
            return;
        }

        if (k == seq) {
            ans = trie.number;
            stop = true;
            return;
        }

        for (int i = 0; i < trie.children.length && !stop ; i++) {
            if (trie.children[i] == null) {
                Trie node = new Trie(i);
                node.val = i;
                trie.children[i] = node;
            }

            Trie node = trie.children[i];
            node.number = trie.number * 10 + node.val;
            seq++;

//            System.out.println("seq="+seq+" node="+node.number);
            dfsInsert(node, n, k);
        }

    }

    class Trie {

        private int val;

        private Trie[] children;

        private int number;

        // 第一层只有9个数
        public Trie() {
            children = new Trie[9];
            for (int i = 0; i < children.length; i++) {
                children[i] = new Trie(i + 1);
            }
        }

        // 第2层开始才是10个数
        private Trie(int val) {
            this.val = val;
            children = new Trie[10];
        }

    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        int kthNumber;
        Stopwatch stopwatch = Stopwatch.createStarted();
        kthNumber = solution2.findKthNumber(4289384,1922239);
        System.out.println(stopwatch.stop());
        Assert.assertEquals(kthNumber, 2730010);

//        kthNumber = solution2.findKthNumber(13, 2);
//        Assert.assertEquals(kthNumber, 10);

//        kthNumber = solution2.findKthNumber(255, 11);
//        Assert.assertEquals(kthNumber, 108);

//        kthNumber = solution2.findKthNumber(255, 15);
//        Assert.assertEquals(kthNumber, 111);
    }

}



