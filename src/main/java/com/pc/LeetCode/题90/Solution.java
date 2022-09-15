package com.pc.LeetCode.题90;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/subsets-ii
 */
public class Solution {

    /**
     * 精髓在于如何处理重复的数，
     * 先把数组排序，排序的目的下面的讲解中会涉及到
     * 假如nums[i]重复了2两次，那么在遍历i的时候，
     * 考虑分别考虑选取1个nums[i]加入到path路径中，然后递归[i+2...length]这一区间
     * 考虑分别考虑选取2个nums[i]加入到path路径中，然后递归[i+2...length]这一区间
     * ....
     * 最后不考虑把nums[i]放入到path中，然后递归[i+2...length]这一区间
     * 因此，排序的目的是方便我们跳过重复的元素
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, counter, path, ans);
        return ans;
    }

    private void dfs(int index, int[] nums, Map<Integer, Integer> counter, List<Integer> path, List<List<Integer>> ans) {

        if (index >= nums.length) {
            copyPathToResult(path, ans);
            return;
        }
        int count = counter.get(nums[index]);
        for (int i = 1; i <= count; i++) {
            path.add(nums[index]);
            dfs(index + count, nums, counter, path, ans);
        }
        for (int i = 1; i <= count; i++) {
            path.remove(path.size() - 1);
        }
        dfs(index + count, nums, counter, path, ans);
    }

    private void copyPathToResult(List<Integer> path, List<List<Integer>> ans) {
        if (path.isEmpty()) {
            ans.add(new ArrayList<>());
        } else {
            List<Integer> copied = new ArrayList<>(path);
            ans.add(copied);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.subsetsWithDup(new int[]{1, 2, 2})));
    }

}
