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
public class Solution {

    /**
     * 解答错误，利用前缀和期望一次遍历搞定
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling(int[][] trips, int capacity) {

        int[] dp = new int[1000];
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int i = 0;
        while (i < trips[0][1]) {
            dp[i] = 0;
            i ++;
        }
        dp[trips[0][1]] = trips[0][0];
        dp[trips[0][2]] = dp[trips[0][2]] - trips[0][0];

        int t = 1;
        for (i = trips[0][1] + 1; i < dp.length && t < trips.length; i++) {

            int nextTrip = trips[t][1];
            boolean pickupFirst = true;
            boolean isBreakFromWhile = false;
            while (t < trips.length && i == trips[t][1]) {
                isBreakFromWhile = true;
                // 下一拨人也是在这里上车
                if (pickupFirst) {
                    dp[i] = dp[i] + dp[i-1] + trips[t][0];
                    pickupFirst = false;
                } else {
                    dp[i] += trips[t][0];
                }
                // 下车人站坑位
                dp[trips[t][2]] = dp[trips[t][2]] - trips[t][0];
                t ++;
            }

            pickupFirst = true;

            if (i < nextTrip && !isBreakFromWhile) {
                // 这里可能也有下车的人
                dp[i] = dp[i - 1] + dp[i];
            }

            if (dp[i] > capacity) {
                return false;
            }

        }
        return true;
    }

    /**
     * 参考别人的思路
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling2(int[][] trips, int capacity) {
        int sites[] = new int[1001];
        for (int[] trip : trips) {
            // 上车加
            sites[trip[1]] += trip[0];
            // 下车减
            sites[trip[2]] -= trip[0];
        }
        // 从始发站计数，超过capacity则超载
        int total = 0;
        for (int i : sites) {
            total += i;
            if (total > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean ans;
        ans = carPooling2(new int[][]{{3,6,9},{10,2,3},{1,6,8},{2,1,6},{9,3,9}},12);
        Assert.assertIsFalse(ans);

        ans = carPooling2(new int[][]{{3,2,8},{4,4,6},{10,8,9}},11);
        Assert.assertIsTrue(ans);

        ans = carPooling2(new int[][]{{2,1,5},{3,3,7}},4);
        Assert.assertIsFalse(ans);

        ans = carPooling2(new int[][]{{2,1,5},{3,3,4}},6);
        Assert.assertIsTrue(ans);
    }
}
