package com.pc.LeetCode.题31;

import java.util.*;

/**
 * 31. 下一个排列
 * https://leetcode.cn/problems/next-permutation/
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class Solution {

    public void nextPermutation(int[] nums) {

        // 找右边比自己大的数
        int i = nums.length - 1;
        boolean findx = false;

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        while (i >= 0) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    // 在自己的右边找一个比自己略微大点的元素
                    if (nums[j] < min) {
                        min = Math.min(min, nums[j]);
                        minIndex = j;
                        findx = true;
                    }
                }
            }
            if (findx) {
                swap(nums, i, minIndex);
                // mmp的想不到这里，交换后，还需要对后面的序列进行排序
                Arrays.sort(nums, i + 1, nums.length);
//                circleMove(nums, i, minIndex);
                break;
            }
            i--;
        }
        if (!findx) {
//            List<Integer> list = new ArrayList();
//            for (int j = 0; j < nums.length; j++) {
//                list.add(nums[j]);
//            }

//            这里也有问题，不应该对数组做字典序排列
//            Collections.sort(list, new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    return String.valueOf(o1).compareTo(String.valueOf(o2));
//                }
//            });

//            for (int j = 0; j < list.size(); j++) {
//                nums[j] = list.get(j);
//            }
            // 其实把整个数组排序，得到的就是最小字典序
            Arrays.sort(nums);
        }

        System.out.println(Arrays.toString(nums));

    }

    private void circleMove(int[] nums, int i, int minIndex) {
        int last = nums[minIndex];
        for (int j = minIndex; j > i; j--) {
            nums[j] = nums[j - 1];
        }
        nums[i] = last;
    }

    private void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nextPermutation(new int[]{1, 2, 3});
        solution.nextPermutation(new int[]{1, 3, 2});
    }
}
