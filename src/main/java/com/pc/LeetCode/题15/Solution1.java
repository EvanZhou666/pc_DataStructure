package com.pc.LeetCode.题15;

import com.pc.LeetCode.common.GoodQuestion;
import com.pc.LeetCode.common.Uitls;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
@GoodQuestion(type = "双指针系列")
public class Solution1 {

    /**
     * <img src="./img.png"/>
     * 哈希表 超时！！！ feat:【两数之和系列】15. 三数之和 暴力解法1-超时！！
     * @param nums
     * @return
     */
//    public static List<List<Integer>> threeSum(int[] nums) {
//
//        Map<Integer, List<List<Integer>>> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                int twoSum = nums[i] + nums[j];
//                map.putIfAbsent(twoSum, new ArrayList<>());
//                map.get(twoSum).add(new ArrayList<>(Arrays.asList(i, j)));
//            }
//        }
//
//        Set<String> set = new HashSet<>();
//
//        List<List<Integer>> ans = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (map.get(-nums[i]) != null) {
//                List<List<Integer>> lists = map.get(-nums[i]);
//                Iterator<List<Integer>> iterator = lists.iterator();
//
//                while (iterator.hasNext()) {
//                    List<Integer> next = iterator.next();
//                    if (!next.contains(i)) {
//                        next.add(i);
//                        List<Integer> result = new ArrayList<>(4);
//                        for (Integer val : next) {
//                            result.add(new Integer(nums[val]));
//                        }
//                        Collections.sort(result);
//                        String key = result.stream().map(String::valueOf).collect(Collectors.joining(","));
//                        if (!set.contains(key)) {
//                            set.add(key);
//                            ans.add(result);
//                        }
//                        iterator.remove();
//                    }
//                }
//            }
//        }
//
//        return ans;
//    }


    /**
     * 双指针解法，优化时间复杂度至O（N^2）
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        // 固定1个元素，求两数之和
        for (int i = 0; i < nums.length; i++) {
//        [-4, -1, -1, 0, 1, 2]
//        因为nums被排序过，当前nums[i]的值可能和上个相等，造成重复值。
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> two = twoSum(nums, i + 1, -nums[i]);
            for (List<Integer> t : two) {
                t.add(0, nums[i]);
            }
            result.addAll(two);
        }
        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int index, int target) {
        int left = index;
        int right = nums.length - 1;

        List<List<Integer>> ans = new ArrayList<>();
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                List<Integer> result = new ArrayList<>();
                result.add(nums[left]);
                result.add(nums[right]);
                ans.add(result);
                left++;

                // 防止重复值
                while (left > 0 && left< nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }

                right--;

                // 防止重复值
                while (right > 0 && nums[right] == nums[right + 1]) {
                    right --;
                }

            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        System.out.println(JSON.toJSONString(lists));
        Uitls.printList(lists);
    }
}
