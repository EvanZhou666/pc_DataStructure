package com.pc.LeetCode.题253;

import com.pc.LeetCode.common.Assert;

import java.util.*;

public class Solution2 {

    /**
     * 方法1的优化思路，方法1坐标值每次都需要自增1，如果在该坐标轴上，有1个会议开始，则所需资源count + 1.反之有会议结束，则count-1，
     * 如果同时遇到会议开始和会议结束，count 先减再加。 坐标值每次自增1，导致效率低下。
     * 如果我们能够在排好序的”有效坐标值“上，进行"游走"，检查该坐标值处的会议情况就好了，不需要每次坐标值+1，然后轮询所有的intervals
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {

        int ans = 0;

        TreeMap<Integer, Node> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            Node startNode = map.getOrDefault(intervals[i][0], new Node(intervals[i][0]));
            startNode.start += 1;
            map.put(intervals[i][0], startNode);

            Node endNode = map.getOrDefault(intervals[i][1], new Node(intervals[i][1]));
            endNode.end += 1;
            map.put(intervals[i][1], endNode);
        }

        int count = 0;
        for (Integer index : map.keySet()) {
            count -= map.get(index).end;
            count += map.get(index).start;
            ans = Math.max(count, ans);
        }
        return ans;
    }

    static class Node {

        public Node(int index) {
            this.index =  index;
        }
        // 坐标值
        int index;
        int start;
        int end;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int ans = 0;

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
