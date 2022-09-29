package com.pc.LeetCode.题1060;

import com.pc.LeetCode.common.Assert;


public class Solution {

    // Return how many numbers are missing until nums[idx]
    int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }

    // 官方答案
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        // If kth missing number is larger than
        // the last element of the array
        if (k > missing(n - 1, nums))
            return nums[n - 1] + k - missing(n - 1, nums);

        int left = 0, right = n - 1, pivot;
        // find left = right index such that
        // missing(left - 1) < k <= missing(left)
        while (left != right) {
            pivot = left + (right - left) / 2;

            if (missing(pivot, nums) < k) left = pivot + 1;
            else right = pivot;
        }

        // kth missing number is larger than nums[idx - 1]
        // and smaller than nums[idx]
        return nums[left - 1] + k - missing(left - 1, nums);
    }

//    解答错误
//    public int missingElement(int[] nums, int k) {
//        int[] diff = new int[nums.length];
//
//        int interval = 1;
//        while (interval <= nums.length) {
//            // 每个子区间的起始左边界
//            for (int i = 0; i + interval < nums.length; i = i + interval + interval) {
//                int right = Math.min(i + interval * 2, nums.length);
//                merge(diff, nums, i, i + interval - 1, right - 1);
//            }
//            interval = interval * 2;
//        }
//
//        System.out.println(Arrays.toString(diff));
//        for (int i = 0; i < diff.length; i++) {
//            if (diff[i] >= k) {
//                return nums[i-1] + k - diff[i-1];
//            }
//
//        }
//        int last = nums.length - 1;
//        return nums[last] + k - diff[last];
//    }
//
//    private void merge(int[] diff, int[] nums, int left, int mid, int right) {
//        if (left != mid) {
//            diff[mid] = nums[mid] - nums[left] - 1 + diff[left];
//        }
//        diff[mid + 1] = nums[mid + 1] - nums[mid] - 1 + diff[mid];
//
//        if (mid+1 != right) {
//            diff[right] = nums[right] - nums[mid + 1] - 1 + diff[mid + 1];
//        }
//
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;
        ans = solution.missingElement(new int[]{4, 7, 9, 10}, 1);
        Assert.assertEquals(ans, 5);
        ans = solution.missingElement(new int[]{4, 7, 9, 10}, 3);
        Assert.assertEquals(ans, 8);
        ans = solution.missingElement(new int[]{1, 2, 4}, 3);
        Assert.assertEquals(ans, 6);
        ans = solution.missingElement(new int[]{2, 3, 5, 7}, 1);
        Assert.assertEquals(ans, 4);
//        System.out.println(ans);
    }
}
