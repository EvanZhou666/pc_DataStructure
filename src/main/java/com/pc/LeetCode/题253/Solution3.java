package com.pc.LeetCode.题253;

import com.pc.LeetCode.common.Assert;

import java.util.*;

public class Solution3 {

    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了20.13%的用户
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了10.78%的用户
     * 通过测试用例：
     * 78 / 78
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {

        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new int[]{interval[0], 1});
            list.add(new int[]{interval[1], -1});
        }

        // 开始的时间从小到大 结束时间从小到大，为了保证会议室先释放再分配
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o2[1], o1[1]);
                } else {
                    return Integer.compare(o1[0], o2[0]);
                }
            }
        });

        int ans = 0;
        int count = 0;
        for (int[] point : list) {
            count += point[1];
            ans = Math.max(count, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int ans = 0;

        ans = solution.minMeetingRooms(new int[][]{{13,15},{1,13}});
        Assert.assertEquals(ans, 1);

        ans = solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}});
        Assert.assertEquals(ans, 2);

        ans = solution.minMeetingRooms(new int[][]{{7,10},{2,4}});
        Assert.assertEquals(ans, 1);

        ans = solution.minMeetingRooms(new int[][]{{5,8},{5,9}});
        Assert.assertEquals(ans, 2);

        ans = solution.minMeetingRooms(new int[][]{{2, 11}, {6, 16}, {11, 16}});
        Assert.assertEquals(ans, 2);

    }
}
