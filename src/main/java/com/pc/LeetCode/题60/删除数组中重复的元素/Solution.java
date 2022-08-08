package com.pc.LeetCode.题60.删除数组中重复的元素;

import java.util.Arrays;

/**
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2020/9/5 11:26
 */
public class Solution {
	public static int removeDuplicates(int[] nums) {
		int j = 1;
		for (int i =1 ;i<nums.length;i++) {
			if (nums[i]>nums[i-1]) {
				nums[j] = nums[i];
				j ++;
			}
		}
		return j;

	}

	public static void main(String[] args) {
//		int[] arr = { 1,1,1,2,2,3 };
		//		int[] arr = { 1,1,1,1,2,2,3};
		int[] arr = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
		int i = removeDuplicates(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(i);
	}
}
