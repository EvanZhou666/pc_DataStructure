package com.pc.LeetCode.题207;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] courses = new int[numCourses];

        int[] memo = new int[numCourses];

        Map<Integer, List<Integer>> reversed = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            List<Integer> list = reversed.getOrDefault(prerequisite[0], new ArrayList<>());
            list.add(prerequisite[1]);
            reversed.put(prerequisite[0], list);
        }

        for (int i = 0; i < numCourses; i++) {
            boolean canfinish = judgeCanFinish(i, courses, reversed, memo);
            if (!canfinish) {
                return false;
            }

        }
        return true;
    }

    private boolean judgeCanFinish(int i, int[] courses, Map<Integer, List<Integer>> reversed, int[] memo) {

        if (courses[i] == 1) {
            return false;
        }

        if (reversed.get(i) == null) {
            return true;
        }

        if (memo[i] != 0) {
            return true;
        }

        // 标记已经访问过
        courses[i] = 1;
        for (int j = 0; j < reversed.get(i).size(); j++) {
            boolean judged = judgeCanFinish(reversed.get(i).get(j), courses, reversed, memo);
            if (!judged) {
                return false;
            }
        }
        courses[i] = 0;
        memo[i] = 1;
        return true;
    }

}
