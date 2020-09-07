package com.pc.LeetCode.题7.整数翻转;

import java.util.Stack;

/**
 *
 *@author Evan
 *@since 2020/9/7 8:44
 */
public class Solution {

	// 用栈的方法，先求出x的每位数是多少，最后出栈的方法实现很简单，但是溢出问题很难处理
	//	public static int reverse(int x) {
	//
	//		Stack<Integer> stack = new Stack<>();
	//		int j = 1;
	//		while (x / j != 0) {
	//			// 如果j大于10的9次方 这里会溢出
	//			j *= 10;
	//		}
	//
	//		int maxL = j / 10;
	//		int mod = maxL;
	//		int sum = 0;
	//
	//		while (mod >= 1) {
	//			stack.push(x / mod);
	//			x = x - mod * (x / mod);
	//			mod = mod / 10;
	//		}
	//
	//		while (!stack.empty()) {
	//			if (stack.peek() >= 7 && maxL >= 1000000000) {
	//				return 0;
	//			}
	//			sum += stack.pop() * maxL;
	//			maxL = maxL / 10;
	//		}
	//
	//		return sum;
	//	}

	// 这里用到了一个数学方法  1234= 1*10^3 + 2*10^2 + 3*10^1+4*10^0 = ((1*10+2) +3)*10 + 4
	// 也就是说我们在求x倒过来是多少的时候我们完全没必要先全部求出x的每一位是多少，然后倒过来。
	// 1234的导 = 4321 = ((4*10+3) +2)*10 + 1
	public static int reverse(int x) {
		int sum = 0;
		while (x != 0) {
			int j = x % 10;
			// 21 4748 3647
			if (Math.abs(sum) > Integer.MAX_VALUE / 10|| (Math.abs(sum) == Integer.MAX_VALUE / 10 && j > 7)) {
				return 0;
			}
				//			if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && j > 7)) {
				//				return 0;
				//			} else if (sum < Integer.MIN_VALUE / 10 || (sum == Integer.MIN_VALUE / 10 && Math.abs(j) > 7) ) {
				//				return 0;
				//			}
				sum = sum * 10 + j;
			x = x / 10;
		}

		return sum;
	}

	public static void main(String[] args) {
		//		int reverse = reverse(123);
		//				int reverse = reverse(1534236469);
		int reverse = reverse(900000);
		//				int reverse = reverse(-2147483412);
		//				int reverse = reverse(-2147483647);
		System.out.println(reverse);
		//		System.out.println((2147483647 + 2));
		//		System.out.println(Integer.MIN_VALUE);
		//		System.out.println(Integer.MIN_VALUE - 1);
		System.out.println(1534236469 & 0xFFFFFFFF);
	}
}
