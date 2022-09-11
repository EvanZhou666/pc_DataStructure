package com.pc.LeetCode.题386;

import com.alibaba.fastjson.JSON;
import com.pc.LeetCode.common.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 5 * 104
 */
public class Solution2 {

    // 已经遍历到第几个元素了
    private int seq;

    /**
     * 字典树深度遍历-递归
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new LinkedList<>();
        this.seq = 0;
        dfs(1, n, ans);
        return ans;
    }

    // number 当前节点值
    private void dfs(int number, int n, List<Integer> ans) {

        if (number > n) {
            return;
        }

        if (seq >= n) {
            return;
        }
        // 本质还是前序遍历。先访问自己
        ans.add(number);
        seq += 1;
        // 步骤一：递归第一个左孩子
        dfs(10 * number, n, ans);
        // max=number的最大右兄弟节点
        int max = Math.min((number / 10) * 10 + 9, n);
        // 如果number还有右兄弟节点
        if (number < max) {
            // 步骤2：递归第一个右邻接兄弟
            dfs(number + 1, n, ans);
        }
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        List<Integer> ans;
        ans = solution.lexicalOrder(13); //[1,10,11,12,13,2,3,4,5,6,7,8,9]
        System.out.println(JSON.toJSONString(ans));
        Assert.assertEquals(JSON.toJSONString(ans), "[1,10,11,12,13,2,3,4,5,6,7,8,9]");

        ans = solution.lexicalOrder(34);
        // [1,10,11,12,13,14,15,16,17,18,19,2,20,21,22,23,24,25,26,27,28,29,3,30,31,32,33,34,4,5,6,7,8,9]
        System.out.println(JSON.toJSONString(ans));

    }


}
