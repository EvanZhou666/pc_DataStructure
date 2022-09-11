package com.pc.LeetCode.题386;

import com.alibaba.fastjson.JSON;
import com.pc.LeetCode.common.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 *
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 1 <= n <= 5 * 104
 */
public class Solution {


    public List<Integer> lexicalOrder(int n) {

        List<Integer> ans = new LinkedList<>();
        int seq = 1;
        int number = 1;
        ans.add(1);

        while (seq < n) {

            while (hasChildren(number, n) && seq < n) {
                number = number * 10;
                ans.add(number);
                seq++;
            }

            while (!hasSlibing(number, n) && seq < n) {
                number = number / 10;
            }

            number = number + 1;
            ans.add(number);
            seq ++;
        }

        return ans;

    }

    // number右边是否还有紧邻着的兄弟节点
    private boolean hasSlibing(int number, int n) {
        return number % 10 != 9 && number != n;
    }

    // number是否还有孩子节点
    private boolean hasChildren(int number, int n) {
        return 10 * number <= n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> ans;
        ans = solution.lexicalOrder(192);
        System.out.println(JSON.toJSONString(ans));
        ans = solution.lexicalOrder(13); //[1,10,11,12,13,2,3,4,5,6,7,8,9]
        Assert.assertEquals(JSON.toJSONString(ans), "[1,10,11,12,13,2,3,4,5,6,7,8,9]");
//        System.out.println(JSON.toJSONString(ans));
        ans = solution.lexicalOrder(34); //[1,10,11,12,13,2,3,4,5,6,7,8,9]
        Assert.assertEquals(JSON.toJSONString(ans), "[1,10,11,12,13,2,3,4,5,6,7,8,9]");

//        System.out.println(JSON.toJSONString(ans));// [1,10,11,12,13,14,15,16,17,18,19,2,20,21,22,23,24,25,26,27,28,29,3,30,31,32,33,34,4,5,6,7,8,9]

    }


}
