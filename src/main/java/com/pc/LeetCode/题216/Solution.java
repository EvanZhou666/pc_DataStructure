package com.pc.LeetCode.题216;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class Solution {

    // 可以用几个数
    private int enabledNum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {

        enabledNum = k;

        List<List<Integer>> result = new ArrayList<>();

        // 候选者 只能选择1-9，必须用到k个数
        int[] candidates = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        Deque<Integer> path = new ArrayDeque<>();

        // 每个数只能用1次
        dfs(candidates, 0, n, path, result);
        return result;

    }

    /**
     * @param candidates 候选者数组
     * @param index      从第index位置开始搜索候选者
     * @param target     目标和
     * @param path       路径
     * @param result     结果集
     */
    private void dfs(int[] candidates, int index, int target, Deque<Integer> path, List<List<Integer>> result) {

        // 剪枝，不可能还能找到答案了
        if (target < 0 || (enabledNum == 0 && target != 0) || index > candidates.length) {
            return;
        }

        // 找到1条可行解
        if (target == 0 && enabledNum == 0) {
            // 拷贝答案
            List<Integer> copy = new ArrayList<>(path.size());
            for (Integer value : path) {
                copy.add(value);
            }
            result.add(copy);

        }

        for (int i = index; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            enabledNum --;
            dfs(candidates, i + 1, target - candidates[i], path, result);
            enabledNum ++;
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.combinationSum3(9, 45);
        System.out.println(result);
    }
}
