package com.pc.栈;

public interface Stack<E> {
	boolean isEmpty();
	/**
	 * 获取栈的大小
	 * @return
	 */
	int getCapasity();
	void push(E e);
	/**
	 * 出栈
	 * @param e
	 * @return
	 */
	E pop();
	/**
	 * 查看栈顶元素
	 * @param e
	 * @return
	 */
	E peek();
}
