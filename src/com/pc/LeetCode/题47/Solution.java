package com.pc.LeetCode.题47;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * <p>
 * 输入：输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
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

        Deque<Integer> deque = new ArrayDeque<>(nums.length);
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        dfs(nums, 0, deque, set, result);
        return result;
    }

    /**
     * @param nums     候选元素
     * @param position 将要确认第position个位置的元素
     * @param path     路径
     * @param visited  已经访问过的元素
     * @param result   结果集
     */
    private void dfs(int[] nums, int position, Deque<Integer> path, Set<Integer> visited, List<List<Integer>> result) {

        if (position >= nums.length) {
            List<Integer> copied = new ArrayList<>(path.size());
            for (Integer value : path) {
                copied.add(value);
            }
            result.add(copied);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果从来未被加入过路径
            if (!visited.contains(i)) {
                // 减枝,候选者数组会被排序，如果有多个重复的数字记作 1a，1b，只有始终按照1a，1b的顺序才不会出现重复的排列
                if (i > 0 && nums[i] == nums[i - 1] && !visited.contains(i - 1)) {
                    // do nothing
                } else {
                    // 做选择
                    path.addLast(nums[i]);
                    visited.add(i);
                    dfs(nums, position + 1, path, visited, result);
                    visited.remove(i);
                    path.removeLast();
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> permute = solution.permute(new int[]{1, 1, 2});
        System.out.println(permute);
    }
}
