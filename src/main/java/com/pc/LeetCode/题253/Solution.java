package com.pc.LeetCode.题253;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    /**
     * 方法1的优化思路，方法1坐标值每次都需要自增1，如果在该坐标轴上，有1个会议开始，则所需资源count + 1.反之有会议结束，则count-1，
     * 如果同时遇到会议开始和会议结束，count 先减再加。 坐标值每次自增1，导致效率低下。
     * 如果我们能够在排好序的”有效坐标值“上，进行游走，检查该坐标值处的会议情况就好了，不需要每次坐标值+1，然后轮询所有的intervals
     * @param intervals
     * @return
     */
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
