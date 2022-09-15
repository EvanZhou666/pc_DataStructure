package com.pc.LeetCode.题210;

import java.util.*;

/**
 * https://leetcode.cn/problems/course-schedule-ii/
 * 210. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 */
public class Solution {

    /**
     * 拓扑排序其实就是广度优先搜索或者深度优先搜索
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 新建出度数组，表示当前课程依赖多少其它门课。（改为出度更符合业务逻辑，更好理解）
        int[] outdegree = new int[numCourses];
        // 2. 建立邻接表，描述节点-节点之间的边关系，这里选用hash表，也可以用数组
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // 遍历有哪些预先课程，建立邻接表
        for (int[] prerequisite : prerequisites) {
            // 表示有哪些其它课程依赖我，其实就是存储我的入度节点
            List<Integer> edges = graph.getOrDefault(prerequisite[1], new ArrayList<>());
            edges.add(prerequisite[0]);
            graph.put(prerequisite[1], edges);
            // prerequisite[0] 课程依赖 prerequisite[1]
            // 课程0的出度加1，因为它依赖其它课程的学习
            outdegree[prerequisite[0]] += 1;
        }

        Deque<Integer> queue = new ArrayDeque<>();

        // 放入所有出度为0的课程，因为它不需要提前学习其它课程
        for (int i = 0; i < outdegree.length; i++) {
            if (outdegree[i] == 0) {
                queue.push(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 出队代表pop课程已经学习
            Integer pop = queue.pop();
            ans.add(pop);
            // 看还有哪些课程依赖与该课程的
            List<Integer> edges = graph.get(pop);
            if (edges != null) {
                for (int i = 0; i < edges.size(); i++) {
                    outdegree[edges.get(i)] -= 1;
                    if (outdegree[edges.get(i)] == 0) {
                        queue.push(edges.get(i));
                    }
                }
            }

        }
        // 或者看出度数组中是否还存在出度不为0的节点
        return ans.size() == numCourses ? toarray(ans) : new int[0];
    }

    private int[] toarray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findOrder(2, new int[][]{{1,0}});
    }

}
