package com.pc.LeetCode.题39;

import java.util.*;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque();
        dfs(candidates, 0, target, deque, result);
        return result;
    }

    /**
     * 回溯算法的核心 递归
     * @param candidates 候选数组
     * @param index 从候选数组的第index个元素开始寻找答案， index从0开始
     * @param target 目标元素，要寻找的目标target
     * @param deque 存放操作路径，用于存储选择路径或者回退到上一步
     * @param result 存放全局的答案。
     */
    private static void dfs(int[] candidates, int index, int target, Deque<Integer> deque, List<List<Integer>> result) {

        // 递归终止条件，target = 0，说明找到了一条可行解路径
        if (target == 0) {
            List<Integer> ans = new ArrayList<>();
            deque.forEach(val -> ans.add(val));
            result.add(ans);
            return;
        }

        // 因为数组中都是正整数，所以不可能还能找到解
        if (target < 0) {
            return;
        }

        if (index >= candidates.length) {
            return;
        }

        // 做选择，选择某个候选者candidate
        for (int i = index; i < candidates.length; i++) {
            deque.offerLast(candidates[i]);
            dfs(candidates, i, target - candidates[i], deque, result);
            // 撤销选择
            deque.pollLast();
        }

    }

    private static void printDoublebList(List<List<Integer>> list) {
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }
    public static void main(String[] args) {
//        List<List<Integer>> result = combinationSum(new int[]{2, 3, 6, 7}, 7);
        List<List<Integer>> result = combinationSum(new int[]{2, 3, 5}, 8);
//        List<List<Integer>> result = combinationSum(new int[]{2}, 1);
        printDoublebList(result);

    }

}
