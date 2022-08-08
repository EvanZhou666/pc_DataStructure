package com.pc.LeetCode.题46;

import java.util.*;

/**
 * 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet(Arrays.asList(nums));
        dfs(nums, 0, new ArrayList<>(nums.length), set, result);
        return result;
    }

    /**
     *
     * @param nums 候选元素
     * @param position 将要确认第position个位置的元素
     * @param path 路径
     * @param visited 已经访问过的元素
     * @param result 结果集
     */
    private void dfs(int[] nums, int position, List<Integer> path, Set<Integer> visited, List<List<Integer>> result) {

        if (position >= nums.length) {
            List<Integer> copied = new ArrayList<>(path.size());
            for (Integer value : path) {
                copied.add(value);
            }
            result.add(copied);
            return;
        }

        for (int num : nums) {
            // 如果从来未被加入过路径
            if (!visited.contains(num)) {
                // 做选择
                path.add(0, num);
                visited.add(num);
                dfs(nums, position + 1, path, visited, result);
                visited.remove(num);
                path.remove(0);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{});
        System.out.println(permute);
    }
}
