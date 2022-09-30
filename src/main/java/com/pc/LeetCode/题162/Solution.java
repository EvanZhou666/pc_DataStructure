package com.pc.LeetCode.题162;

import java.util.Random;

/**
 * 162. 寻找峰值
 * https://leetcode.cn/problems/find-peak-element/
 */
public class Solution {

    Random random = new Random();

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        if (left == right) {
            return 0;
        }

        return doFindPeakElement(nums, 0, right);
    }

    private int doFindPeakElement(int[] nums, int left, int right) {

        if (left > right) {
            return 0;
        }


        int mid = left + (right - left) / 2;

        if (isGraterThanNeighborhood(nums, mid)) {
            return mid;
        }

        if (random.nextInt(Integer.MAX_VALUE) % 2 == 0) {
            int ret = doFindPeakElement(nums, left, mid - 1);
            if (ret != 0) {
                return ret;
            } else {
                return doFindPeakElement(nums, mid + 1, right);
            }
        } else {
            int ret = doFindPeakElement(nums, mid + 1, right);
            if (ret != 0) {
                return ret;
            } else {
                return doFindPeakElement(nums, left, mid - 1);
            }
        }
    }

    private boolean isGraterThanNeighborhood(int[] nums, int mid) {
        if (mid == 0 && mid + 1 <= nums.length - 1) {
            return nums[mid] > nums[mid + 1];
        }

        if (mid == nums.length - 1 && mid - 1 >= 0) {
            return nums[mid] > nums[mid - 1];
        }

        if (mid > 0 && mid < nums.length - 1) {
            return nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1];
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;

        ans = solution.findPeakElement(new int[]{1, 2, 3, 1});
        System.out.println(ans);

        ans = solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
        System.out.println(ans);

    }

}
