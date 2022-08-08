package com.pc.LeetCode.题435;


import com.pc.LeetCode.common.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104<= starti< endi<= 5 * 104
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * <a href="https://note.youdao.com/s/4Z4a8Uw7">解体思路</a>
     * 贪心算法，始终移除右边界最远的分区
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        // 先排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int campare = o1[0] - o2[0];
                if (campare == 0) {
                    return o1[1] - o2[1];
                } else {
                    return campare;
                }
            }
        });


        for (int[] interval : intervals) {
            System.out.print(Arrays.toString(interval));
        }
        System.out.println();

        int count = 0;
        int[] pre = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < pre[1]) { // 如果当前分区和上一个分区重叠，始终移除右边界最远的分区，这样最大程度的减少后面分区发生冲突的概率。
                // 移除当前分区 current = (intervals[i][0],intervals[i][1])
                if (intervals[i][1] > pre[1]) {
                    System.out.println("移除分区:[" + intervals[i][0] + "," + intervals[i][1] + "]");
                } else { // 移除pre分区
                    System.out.println("移除分区:[" + pre[0] + "," + pre[1] + "]");
                    pre[0] = intervals[i][0];
                    pre[1] = intervals[i][1];
                }
                count++;
            } else { // 如果当前分区和上一个分区不重叠，则
                pre[0] = intervals[i][0];
                pre[1] = intervals[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int minDelete = 0;
        minDelete = solution.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}});
        Assert.assertEquals(1, minDelete);

        minDelete = solution.eraseOverlapIntervals(new int[][]{{1,2}, {1,2}, {1,2}});
        Assert.assertEquals(2, minDelete);

        minDelete = solution.eraseOverlapIntervals(new int[][]{{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}});
        Assert.assertEquals(minDelete, 2);
    }
}
