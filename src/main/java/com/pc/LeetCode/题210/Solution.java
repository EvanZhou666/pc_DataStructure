package com.pc.LeetCode.题210;

import java.util.*;

public class Solution {

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
