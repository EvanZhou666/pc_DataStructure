package com.pc.链表.递归;

/**
 * @author zhouzixiang
 *
 */
/**
 * @author pc
 *使用递归思想对自然数1、2、3...n求和
 */
public class Sum {
	public static int sum(int n) {
		if (n==1) {
			return 1;
		}
		return n+sum(n-1);
	}
	
	public static void main(String[] args) {
		System.out.println(sum(100));
	}
}
