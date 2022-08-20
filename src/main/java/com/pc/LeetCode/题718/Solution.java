package com.pc.LeetCode.题718;

public class Solution {

    /**
     * 暴力解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

//        nums1.length < =nums2.length/;
        int ans = 0;
        int l = 0, r = 0;
        while (r < nums1.length) {
            boolean contains = contains(nums2, nums1, l, r);

            while (l < r && !contains) {
                l ++;
                contains = contains(nums2, nums1, l, r);
            }

            if (contains) {
                ans = Math.max(ans , r -l + 1);
            }
            r ++;

        }
        return ans;
    }

    public static boolean contains(int[] father, int[]son, int left, int right) {
        for (int i = 0; i < father.length; i++) {

            int fa = i;
            int l = left;
            while (fa < father.length && father[fa] == son[l]) {
                if (l == right) {
                    return true;
                }
                fa ++;
                l ++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        boolean contains = contains(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}, 0, 1);
        System.out.println(contains);
    }
}
