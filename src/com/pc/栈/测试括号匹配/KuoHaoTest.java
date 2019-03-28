package com.pc.栈.测试括号匹配;

import com.pc.栈.ArrayStack;
import com.pc.栈.Stack;

public class KuoHaoTest {
	public static boolean test(String toTest) {
		Stack<Character> stack =  new ArrayStack<Character>();
		if ("".equals(toTest)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		
		for (char c : toTest.toCharArray()) {
			if ('{'== c || '('== c || '[' == c) {
				stack.push(c);
			}
			
			if ('}'== c&& '{'!=(char)stack.pop()) {
					return false;
			}
			
			if (')'== c && '('!=(char)stack.pop()) {
					return false;
			}
			
			if (']'== c && '['!=(char)stack.pop()) {
					return false;
			}
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		System.out.println(test("{()[123456]}"));
	}
}
