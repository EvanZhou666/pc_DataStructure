package com.pc.LeetCode.题96.不同的二叉搜索数;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/2/21 12:04
 */
public class Solution {

	private static Integer cache[][] ;

	public int getTree(int start, int end) {

		if (end < start) {
			return 1;
		}
		if (start == end) {
			return 1;
		}

		if (cache[start][end] != null) {
			return cache[start][end];
		}

		int c =0;
		for (int i = start; i <= end; i++) {
			int left,right;

			if (i == start) {
				left = getTree(start,i);
			} else {
				left = getTree(start,i-1);
			}

			if (i == end) {
				right = getTree(end,end);
			}else {
				right =getTree(i+1,end);
			}
			c+= left*right;
		}
		cache[start][end] = c;
		return c;
	}

	public int numTrees(int n) {
		cache = new Integer[n+1][n+1];
		return getTree(1, n);
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int i = solution.numTrees(19);
		System.out.println(i);
	}

}
