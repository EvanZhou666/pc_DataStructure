package com.pc.LeetCode.题278;

/**
 * 278. 第一个错误的版本
 */
public class Solution {

    public int firstBadVersion(int n) {
        int left = 0, right = n - 1;
        int mid;
        while (left <= right) {
            mid = left + ( right - left) / 2;

            // [left,mid]
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean isBadVersion(int version){
        return false;
    }

}
