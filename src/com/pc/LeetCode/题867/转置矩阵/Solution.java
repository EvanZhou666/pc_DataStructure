package com.pc.LeetCode.题867.转置矩阵;

/**
 *
 *@author Evan
 *@since 2020/9/5 17:58
 */
public class Solution {
	public static int[][] transpose(int[][] A) {

		int[][] b = new int[A[0].length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				b[j][i] = A[i][j];

			}
		}
		return b;

	}

	public static void main(String[] args) {
		int[][] A = new int[][] {{ 1, 2, 3},{ 4, 5, 6 },{ 7, 8, 9 }};
		System.out.println(A);
		System.out.println(transpose(A));;
	}
}
