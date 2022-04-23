package com.pc.LeetCode.题78;

import java.util.*;

public class Solution2 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        dfs(nums, 0, path, result);
        return result;
    }

    /**
     * 递归，回溯算法核心 数组总的每个元素相对于每个子集来说只有两种状态：要么存在，要么不存在
     *
     * @param nums 待搜索的数组
     * @param index 从数组的第index位置上开始搜索
     * @param path   路径，新元素始终放队头
     * @param result 存放所有的搜索结果
     */
    private void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> result) {

        // 搜索到数组末尾，返回
        if (index >= nums.length) {
            List<Integer> res = new ArrayList<>(8);
            for (Integer value : path) {
                res.add(value);
            }
            result.add(res);
            return;
        }

        // 做选择，针对于每个元素都有选择或者不选择两条"分支"
        // 第1种选择 选择当前元素
        path.add(0, nums[index]);
        dfs(nums, index + 1, path, result);
        path.remove(0);

        // 第2中元素 不选择当前元素
        dfs(nums, index + 1, path, result);
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2});
        System.out.println(subsets);
    }
}
