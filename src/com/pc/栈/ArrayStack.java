package com.pc.栈;

import java.util.Arrays;

import com.pc.数组.MyArray;

public class ArrayStack<E> implements Stack<E> {
	private MyArray<E> stack;

	public ArrayStack() {
		stack = new MyArray<E>();
	}

	public ArrayStack(int Capsity) {
		stack = new MyArray<E>(Capsity);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int getCapasity() {
		return stack.capisity();
	}

	@Override
	public void push(E e) {
		stack.addElement(e);
		return ;
	}

	@Override
	public E pop() {
		return stack.removeLast();
	}

	@Override
	public E peek() {
		return stack.get();
	}
	
	public String toString(){
//		System.out.println(Arrays.asList(stack));
		return Arrays.toString(new Object[]{stack}).substring(1,Arrays.toString(new Object[]{stack}).length()-1);
	}
	public static void main(String[] args) {
		Stack stack = new ArrayStack<Character>();
		stack.push("{");
		System.out.println(stack.pop());
		System.out.println(stack.toString());
	}
}
