package com.pc.LeetCode.题440;

import com.google.common.base.Stopwatch;

import java.util.TreeSet;

/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 示例 2:
 *
 * 输入: n = 1, k = 1
 * 输出: 1
 */
public class Solution {

    /**
     * 想用TreeSet偷鸡 结果超时了 为啥？？？
     * @param n
     * @param k
     * @return
     */
    public static int findKthNumber(int n, int k) {

        TreeSet<String> treeSet = new TreeSet<String>();
        for (int i = 1; i <= n; i++) {
            treeSet.add(String.valueOf(i));
        }


        String ans = "1";
        int j = 0;
        for (String s : treeSet) {
            if (j >= k) {
                break;
            }
            ans = s;
            j++;
        }

        return Integer.parseInt(ans);
    }

    public static void main(String[] args) {
        int ans = 0;
//        ans = findKthNumber(13, 2);
//        Assert.assertEquals(ans, 10);

//        ans = findKthNumber(1, 1);
//        Assert.assertEquals(ans, 1);

        Stopwatch stopwatch = Stopwatch.createStarted();
        findKthNumber(4289384,1922239);
        System.out.println(stopwatch.stop());
    }

}
