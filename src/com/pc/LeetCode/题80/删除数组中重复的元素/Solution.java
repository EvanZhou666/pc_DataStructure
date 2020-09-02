package com.pc.LeetCode.题80.删除数组中重复的元素;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2020/9/2 22:25
 */
public class Solution {

	public static int removeDuplicates(int[] nums) {
		int i = 0;
		int j = i + 1;
		while (i < nums.length && j < nums.length) {

			if (nums[j] > nums[i]) {
				if (j - i >= 2) {
					nums[i + 1] = nums[j];
					i += 1;
				} else {
					i += 1;
				}
				j = i + 1;

			} else {
				j++;
			}
		}

		if (j - i >= 2 && nums[j - 1] > nums[i]) {
			i++;
		}
		System.out.println("删除后的数组：" + Arrays.toString(nums) + "删除后数组长度:" + (i + 1));
		return i+1;
	}

	public static void main(String[] args) {
//		int[] arr = { 1,1,1,2,2,3 };
		int[] arr = { 0,0,1,1,1,1,2,3,3};
		removeDuplicates(arr);
	}
}
