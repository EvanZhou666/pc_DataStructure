package com.pc.LeetCode.题11.盛最多水的容器;

/**
 *@author Evan
 *@since 2020/9/26 17:40
 */
public class Solution {

	/**
	 * 时间复杂度 n^2
	 * 12 分钟前	通过	381 ms	39.5 MB	Java
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
//		int i 头指针
//		int j 尾指针
		int maxcapability = 0;
		for (int i = 0;i<height.length;i++) {
			for (int j = i+1;j<height.length;j++) {

				int capability = (j-i) * Math.min(height[i],height[j]);

				if (capability>maxcapability) {
					maxcapability = capability;
				}

			}
		}

		return maxcapability;
	}

	/**
	 * 时间复杂度n的解法
	 * 几秒前	通过	3 ms	39.3 MB	Java
	 * @param height
	 * @return
	 */
	public static int maxArea2(int[] height) {
		//		int i 左边界
		//		int j 右边界
		int i = 0;
		int j = height.length-1;
		int maxcapability = 0;
		while (j>i) {
			int capability = (j-i) * Math.min(height[i],height[j]);
			if (capability>maxcapability) {
				maxcapability = capability;
			}
			if (height[i]<=height[j]) {
				i++;
			} else {
				j--;
			}
		}

		return maxcapability;
	}
	public static void main(String[] args) {
		int[] height = new int[]{1,8,6,2,5,4,8,3,7};
		System.out.println(maxArea2(height));
	}
}
