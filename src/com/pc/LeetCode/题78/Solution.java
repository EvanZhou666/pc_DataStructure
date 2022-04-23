package com.pc.LeetCode.题78;


import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Solution {

    /**
     * 巧解法，利用规律
     * 【1，2，3】的子集，等于【1，2】的每个子集集合里添加【3】，然后再加上【1，2】的子集。
     * 也就是说 【1，2】的每个子集集合里添加【3】，然后再加上【1，2】的子集 = 【1，2，3】的子集
     * ....
     *【1】的子集 + 【1】的每个子集集合添加【2】 = 【1，2】的子集
     * 【】 + （【】+1） = 【1】的子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newList = copy(result);
            for (List<Integer> ll : newList) {
                ll.add(nums[i]);
            }
            result.addAll(newList);
        }
        return result;
    }

    /**
     * 拷贝数组
     * @param result
     * @return
     */
    private  List<List<Integer>> copy(List<List<Integer>> result) {
        List<List<Integer>> copied = new ArrayList<>();
        for (List<Integer> list : result) {
            List<Integer> l = new ArrayList<>();
            for (Integer num : list) {
                l.add(num);
            }
            copied.add(l);
        }
        return copied;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> subsets = solution.subsets(new int[]{1, 2});
        System.out.println(subsets);
    }

}
