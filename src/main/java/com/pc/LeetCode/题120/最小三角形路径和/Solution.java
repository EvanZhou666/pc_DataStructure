package com.pc.LeetCode.题120.最小三角形路径和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *@author EvanZhou
 *@since 2020/9/2
 *
 */

public class Solution {

	private static int min(int a, int b) {
		return a <= b ? a : b;
	}

	private static int dp(int i, int j, List<List<Integer>> triangle, Integer[][] cache) {

		if (cache[i][j] != null) {
			return cache[i][j];
		}

		if (i == 0 && j == 0) {
			cache[i][j] = triangle.get(0).get(0);
			return cache[i][j] ;
		}

		if (j == 0) {
			cache[i][j] = dp(i - 1, 0, triangle.subList(0, i), cache) + triangle.get(i).get(0) ;
		} else if (i == j) {
			cache[i][j] =  dp(i - 1, j - 1, triangle.subList(0, i), cache)+ triangle.get(i).get(j);
		} else {
			int left = dp(i - 1, j - 1, triangle.subList(0, i), cache);
			int right = dp(i - 1, j, triangle.subList(0, i), cache);
			cache[i][j] =  min(left, right) + triangle.get(i).get(j);
		}
		return cache[i][j];
	}

	/**
	 * 使用递归+记忆化搜索的解法
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {

		int[] dp = new int[triangle.size()];
		Integer[][] cache = new Integer[triangle.size()][triangle.size()];
		//	Arrays.stream(cache).forEach(c-> System.out.println(Arrays.toString(c)));
		int min = 0;
		for (int j = 0; j < triangle.get(triangle.size() - 1).size(); j++) {
			dp[j] = dp(triangle.size() - 1, j, triangle, cache);
			if (dp[min] > dp[j]) {
				min = j;
			}
		}

		//		System.out.println(Arrays.toString(dp));
		return dp[min];
	}

	    /*
		 *       	   [-1]
		 *      	  [9,-2]
		 *     		 [0,4,5]
		 *     		[7,4,-4,-5]
		 *     	   [9,6,0,5,7]
		 *     	 [9,2,-9,-4,5,-2]
		 *      [-4,-9,-5,-7,-5,-5,-2]
		 *     [-9,5,-6,-4,4,1,6,-4]
		 *    [-4,3,9,-2,8,6,-9,-2,-2]
		 *
		 */
	public static void main(String[] args) {

		ArrayList<List<Integer>> triangle = new ArrayList<>();

		List<Integer> list1 = buildList(-1);
		List<Integer> list2 = buildList(9, -2);
		List<Integer> list3 = buildList(0, 4, 5);
		List<Integer> list4 = buildList(7, 4, -4, -5);
		List<Integer> list5 = buildList(9, 6, 0, 5, 7);
		List<Integer> list6 = buildList(9, 2, -9, -4, 5, -2);
		List<Integer> list7 = buildList(-4, -9, -5, -7, -5, -5, -2);
		List<Integer> list8 = buildList(-9, 5, -6, -4, 4, 1, 6, -4);
		List<Integer> list9 = buildList(-4, 3, 9, -2, 8, 6, -9, -2, -2);
		List<Integer> list10 = buildList(-4, 3, 9, -2, 8, 6, -6, -9, -2, -2);
		List<Integer> list11 = buildList(-4, 3, 9, -2, 8, 6, -66, -3, -9, -2, -2);
		List<Integer> list12 = buildList(-1, 2, 3, -4, 5, 6, -70, -8, -9, 10, 11, 12);

		triangle.add(list1);
		triangle.add(list2);
		triangle.add(list3);
		triangle.add(list4);
		triangle.add(list5);
		triangle.add(list6);
		triangle.add(list7);
		triangle.add(list8);
		triangle.add(list9);
		triangle.add(list10);
		triangle.add(list11);
		triangle.add(list12);
		Long beginTime = System.currentTimeMillis();
		int minimumTotal = minimumTotal(triangle);
		Long endTime = System.currentTimeMillis();
		System.out.println("最短路径是" + minimumTotal + "耗时:" + (endTime - beginTime));
	}

	private static List<Integer> buildList(Integer... nums) {
		return Arrays.asList(nums);
	}
}
