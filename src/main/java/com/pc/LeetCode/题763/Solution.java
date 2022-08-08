package com.pc.LeetCode.题763;

import java.util.*;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class Solution {

    /**
     * 看了官方答案后的解法
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            System.out.print(i+":"+chars[i]+"  ");
        }

        // 存储每个字母最大索引值
        int[] letterIndex = new int[26];

        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 97;
            letterIndex[index] =  Math.max(letterIndex[index], i);
        }

        System.out.println(Arrays.toString(letterIndex));

        // 存储砍刀的位置，从哪里开始砍断字符串的位置。包含当前位置
        LinkedList<Integer> knief = new LinkedList<>();
        // 结果集
        LinkedList<Integer> ans = new LinkedList<>();

        // 定义[start，end]为当前片段长度
        Integer start = 0 ,end = 0;

        for (int i = 0; i < chars.length; i++) {
            // 当前元素的索引位置存在三种情况。
            // 当前元素的最大索引位置位于[start，end)区间内 ---> 不需要开启新的片段
            // 当前元素的最大索引位置位于end时候 ---> 开启新的片段
            // 当前元素最大索引位置位于(end,正无穷)区间内  ----> 不存在此种情况。因为等于end的时候，就已经触发开启新的片段，end已经更新了
            int index = chars[i] - 97;
            end = Math.max(letterIndex[index], end);

            if (i < end) {
                // doNothing
            } else if (i == end) {
                knief.addLast(end);
                ans.addLast(end - start + 1);
                start = end + 1;
                end = start;
            }
        }

        System.out.println(knief);
        System.out.println(ans);
        return ans;
    }

    /**
     * 官方题解
     * @param s
     * @return
     */
    public List<Integer> partitionLabels2(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.partitionLabels("ababcbacadefegdehijhklij");
        solution.partitionLabels("vhaagbqkaq");
    }

}
