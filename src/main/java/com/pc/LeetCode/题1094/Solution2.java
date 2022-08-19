package com.pc.LeetCode.题1094;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1094. 拼车
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 */
public class Solution2 {

    /**
     * 差分数组类型，题目转换有一个空的数组diff，长度为1000，trip[i]是一个三元组操作
     * 定义了(i,j)的区间内
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];

        // 这样构造差分数组 diff，就可以快速进行区间增减的操作，如果你想对区间 nums[i..j] 的元素全部加 3，
        // 那么只需要让 diff[i] += 3，然后再让 diff[j+1] -= 3 即可：
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }


        if (diff[0] > capacity) {
            return false;
        }

        for (int i = 1; i < diff.length; i++) {
            diff[i] = diff[i-1] + diff[i];
            if (diff[i] > capacity) {
                return false;
            }
        }


        return true;
    }


    public static void main(String[] args) {
        boolean ans;
        ans = carPooling(new int[][]{{3,6,9},{10,2,3},{1,6,8},{2,1,6},{9,3,9}},12);
        Assert.assertIsFalse(ans);

        ans = carPooling(new int[][]{{3,2,8},{4,4,6},{10,8,9}},11);
        Assert.assertIsTrue(ans);

        ans = carPooling(new int[][]{{2,1,5},{3,3,7}},4);
        Assert.assertIsFalse(ans);

        ans = carPooling(new int[][]{{2,1,5},{3,3,4}},6);
        Assert.assertIsTrue(ans);
    }
}
