package com.pc.LeetCode.题440;

import com.google.common.base.Stopwatch;
import com.pc.LeetCode.common.Assert;

public class Solution3 {

    private static int seq = 0;
    private static int ans = 0;
    private int n = 0;

    /**
     * Solution2 在内存中建立一个10叉字典树，前序遍历的过程就是从1找到第k个最小数的过程。由于需要从1数到k效率很低下，
     * 有没有什么批量数的方式呢？还有n最大等于1亿，导致内存容量不够,能不能舍弃掉在内存中建立字典树的过程
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        ans = 0;
        seq = 1;
        this.n = n;
        dfs(seq, 1L, k);
        return ans;
    }

    /**
     *
     * @param seq 当前节点的序号,代表当前节点是第seq小的数
     * @param number 当前节点的数值大小.一定要用long类型,因为number最大可以是九亿九千九百九十九万九千九百九十九,用int会整型溢出,不能通过所有测试用例.
     * @param k 要寻找的序号
     */
    private void dfs(int seq, long number, int k) {
        if (seq == k) {
            ans = (int) number;
            return;
        }

        int seqChildrenCount = countSeqChildren(number);
//        System.out.println("number:"+number+"的子节点个数="+seqChildrenCount);
        if (k <= seq + seqChildrenCount) {
            // 第k小的元素在以seq为根的子树上，跳到下一层，将deep + 1
            seq = seq + 1;
            dfs(seq, number * 10, k);
        } else {
            // 核心思路：第k小的元素不在以seq为根的子树上，看兄弟节点。
            seq = seq + seqChildrenCount + 1;
            dfs(seq, number + 1, k);
        }
    }

    // 计算seq子节点的个数
    private int countSeqChildren(long number) {
        long left = 10 * number;
        long right = 10 * number + 9;

        int count = 0;
        while (right <= n) {
            count += (right - left + 1);
            left = 10 * left;
            right = 10 * right + 9;
        }

        // 说明该层不是"满叉树"
        if (n >= left && n < right) {
            count += n - left + 1;
        }
        return count;
    }


    public static void main(String[] args) {
        Solution3 solution2 = new Solution3();
        int kthNumber;

        kthNumber = solution2.findKthNumber(100, 90);
        Assert.assertEquals(kthNumber, 9);

        kthNumber = solution2.findKthNumber(4289384,1922239);
        Assert.assertEquals(kthNumber, 2730010);
    }

}



