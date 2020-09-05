package com.pc.LeetCode.题27.移除元素;

import java.util.Arrays;

/**
 *给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2020/9/5 11:39
 */
public class Solution {
	// 定义0到j是数组的无val区间，记做 A[0,j) ,左闭右开的区间更适合表示一个空数组,并且j刚好就是这个无val区间数组的长度
	// 则[0,0) 代表是一个空数组
	public static int removeElement(int[] nums, int val) {
		int j = 0;
		for (int i =0;i<nums.length;i++) {
			if (val != nums[i]) {
				nums[j] = nums[i];
				j++;
			}

		}
		return  j;
	}

	public static void main(String[] args) {

		int[] array = { 0,1,2,2,3,0,4,2 };
		int i = removeElement(array, 2);
		System.out.println(Arrays.toString(array));
		System.out.println(i);
	}
}
