package com.pc.LeetCode.题53.求最大子序和;

/**
 *
 *@author EvanZhou
 *@since 2020/9/1
 */
public class Solution {

	public int maxSubArray(int[] nums) {
//		int max = nums.length;
//		int i = 0;
//		int j = nums.length - 1;
//		while ((nums[i] < 0 || nums[j] < 0) && j > i) {
//			max--;
//			if (nums[i] > nums[j]) {
//				j--;
//			} else if (nums[i] < nums[j]) {
//				i++;
//			} else {
//				i++;
//				j--;
//			}
//		}
		return 0;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.maxSubArray(new int[]{21,-2222,-999999,111111,11}));
	}
}
