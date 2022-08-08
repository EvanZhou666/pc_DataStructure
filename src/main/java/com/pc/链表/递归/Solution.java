package com.pc.链表.递归;

import com.pc.链表.LinkedList;

/**
 * @author zhouzixiang 2018年10月11日 下午11:18:32 
 * 使用递归来删除值为xxx的元素 
 * @param <T>
 */
public class Solution<T> {
	public LinkedList<T> remove(LinkedList list , T val) {
		
		return null;
	}
	
	public LinkedList<T> remove(T t,T val ,LinkedList list) {
		
		return null;
	}
	
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addLast(1);
		list.addLast(6);
		list.addLast(2);
		list.addLast(5);
		list.addLast(6);
		System.out.println(list);
	}
}
