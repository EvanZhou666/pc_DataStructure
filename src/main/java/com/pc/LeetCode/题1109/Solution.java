package com.pc.LeetCode.题1109;

import com.pc.LeetCode.common.Uitls;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/corporate-flight-bookings/
 * 1109. 航班预订统计
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 *
 *
 */
public class Solution {
    /**
     * 差分数组系列
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n+2];

        for(int[] booking: bookings) {
            diff[booking[0]-1] += booking[2];
            diff[booking[1]] -= booking[2];
        }

        int[] ans = new int[n];
        ans[0] = diff[0];
        for (int i = 1; i< n; i++) {
            ans[i] = ans[i-1] + diff[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] input;
        input = Uitls.convertToInts("[[1,2,10],[2,3,20],[2,5,25]]");
        int[] ans;
        ans = corpFlightBookings(input, 5);
        System.out.println(Arrays.toString(ans));
    }
}
