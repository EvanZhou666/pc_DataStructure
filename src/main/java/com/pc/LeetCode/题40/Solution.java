package com.pc.LeetCode.题40;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的每个数字在每个组合中只能使用一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        // 统计候选者出现过多少次
        Map<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.get(candidate) == null) {
                counter.put(candidate, 1);
            } else {
                counter.put(candidate, 1 + counter.get(candidate));
            }
        }

        Integer[] candidates2 = counter.keySet().toArray(new Integer[]{});

        List<List<Integer>> result = new ArrayList<>();

        Deque<Integer> deque = new ArrayDeque();
        dfs(candidates2, counter, 0, target, deque, result);
        return result;
    }

    /**
     * 回溯算法的核心 递归
     * 先统计重复元素的重复次数，再依次遍历元素，如果遇到有重复的元素，步骤如下：
     * 选择重复1次，再选择下一个不重复的元素；
     * 选择重复2次，再选择下一个不重复的元素；
     * 选择重复3次，再选择下一个不重复的元素；
     * ....
     * 直到N次
     * @param candidates 候选数组
     * @param index      从候选数组的第index个元素开始寻找答案， index从0开始
     * @param target     目标元素，要寻找的目标target
     * @param deque      存放操作路径，用于存储选择路径或者回退到上一步
     * @param result     存放全局的答案。
     */
    private static void dfs(Integer[] candidates, Map<Integer, Integer> counter, int index, int target, Deque<Integer> deque, List<List<Integer>> result) {

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

        // 做选择，选择某个候选者candidate
        for (int i = index; i < candidates.length; i++) {
            // 如果candidates[i]重复，则可以继续选择candidates[i]。比如重复的2有3个，我们可以依次选择1个2，两个2，3个2
            for (int j = 1; j <= counter.get(candidates[i]); j++) {
                deque.offerLast(candidates[i]);
                dfs(candidates, counter, i + 1, target - candidates[i] * j, deque, result);
            }

            // 选择了N次，就要撤销N次
            for (int j = 1; j <= counter.get(candidates[i]); j++) {
                // 撤销选择
                deque.pollLast();
            }
        }

    }

    private static void printDoublebList(List<List<Integer>> list) {
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }

    public static void main(String[] args) {
//        List<List<Integer>> result = combinationSum(new int[]{2, 3, 6, 7}, 7);
        List<List<Integer>> result = combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
//        printDoublebList(result);
//        List<List<Integer>> result = combinationSum(new int[]{2,5,2,1,2}, 5);
        printDoublebList(result);

    }

}
