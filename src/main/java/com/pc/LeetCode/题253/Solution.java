package com.pc.LeetCode.题253;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {


    public int minMeetingRooms(int[][] intervals) {
        // 按会议开始时间从小到大排序，如果开始时间相同，则按结束时间从大到小排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o2[1], o1[1]);
                } else {
                    return Integer.compare(o1[0], o2[0]);
                }
            }
        });

        int count = 0;
        int ans = 0;

        int start = intervals[0][0];
        int end = intervals[intervals.length - 1][1];
        int i = start;
        while (i <= end) {
            for (int j = 0; j < intervals.length; j++) {
                if (i == intervals[j][1]) {
                    count--;
                } else if (i == intervals[j][0]) {
                    count++;
                }

                if (i < intervals[j][0]) {
                    break;
                }

                ans = Math.max(ans, count);
            }
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;
        ans = solution.minMeetingRooms(new int[][]{{5,8},{5,9}});
        Assert.assertEquals(ans, 2);

        ans = solution.minMeetingRooms(new int[][]{{2, 11}, {6, 16}, {11, 16}});
        Assert.assertEquals(ans, 2);

    }
}
