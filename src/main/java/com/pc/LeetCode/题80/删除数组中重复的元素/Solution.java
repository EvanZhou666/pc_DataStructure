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
		// j定义为下一个可覆盖的位置
		int j = 1;
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i-1]) {
				count++;
				if (count <= 2) {
					// 这一步是关键，num[i]向左移动到可覆盖点,即使nums[j]已经等于nums[i]，虽然多了不必要的复制操作，但是可以减少很多边界值的判断，不信你可以试一下！！！
					nums[j] = nums[i];
					j++;
				}
			} else {
				count = 1;
				nums[j] = nums[i];
				j++;

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
