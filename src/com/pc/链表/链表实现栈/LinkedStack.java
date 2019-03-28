package com.pc.链表.链表实现栈;

import com.pc.栈.Stack;
import com.pc.链表.LinkedList;

public class LinkedStack<E> implements Stack<E> {
	LinkedList<E> stack;
	public  LinkedStack() {
		 stack = new LinkedList<E>();
	}
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int getCapasity() {
		return stack.getSize();
	}

	@Override
	public void push(E e) {
		stack.addLast(e);
	}

	@Override
	public E pop() {
		E last = stack.getLast();
		stack.RemoveLast();
		return last;
	}

	@Override
	public E peek() {
		return stack.getLast();
	}
	
	public static void main(String[] args) {
		Stack stack = new LinkedStack<Integer>();
		for(int i=0;i<6;i++){
			stack.push(i);
		}
		
		for(int i=0;i<6;i++){
			System.out.println("size:"+stack.getCapasity()+" element:"+stack.pop());
		}
		System.out.println(stack.isEmpty());
	}
}
