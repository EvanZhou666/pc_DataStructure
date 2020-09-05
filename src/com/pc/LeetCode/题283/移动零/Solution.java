package com.pc.LeetCode.题283.移动零;

import java.util.Arrays;

/**
 *
 *@author Evan
 *@since 2020/9/5 13:17
 */
public class Solution {
	public static void moveZeroes(int[] nums) {
		//  定义[0，j)是非零区间，0到j之间的元素都是非零的，记作A[0,j),则A[j,Length）一定是元素全为0的区间 。
		//  A[0,0) 代表非零区间为空，
		int j = 0;
		int temp;
		for (int i =0 ;i<nums.length;i++){
			if (nums[i] != 0) {
				// 如果我们在迭代中保证A[0,j)的定义一直满足，按照定义 nums[j] 此时一定是0
				temp = nums[j];
				nums[j] = nums[i];
				// 因为temp此时为0，所以0一定要移动到右区间
//				nums[i] = temp;
				j ++;
			}
		}
		System.out.println(Arrays.toString(nums));
	}

	public static void main(String[] args) {
//		int[] arr = new int[]{0,1,0,3,12};
//		int[] arr = new int[]{1};
		int[] arr = new int[]{2,1,0,0,0,3,3,3,3};
		moveZeroes(arr);
	}
}
