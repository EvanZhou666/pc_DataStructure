package com.pc.数组;

import java.util.Arrays;

public class MyArray<E> {
	private E[] data;
	/**
	 * 数组大小 从零开始计数
	 */
	private int size;
	private static final int DEFAULT_SIZE = 8;

	public MyArray() {
		this(DEFAULT_SIZE);
	}
	

	@SuppressWarnings("unchecked")
	public MyArray(int Capsity){
		data = (E[]) new Object[Capsity];
	}
	
	/**
	 * 在数组的尾节点插入元素
	 * @param e
	 * @return
	 */
	public E addElement(E e){
		if(size == data.length) {
			resize(2*data.length);
		}
		data[size] = e;
		size ++;
//		System.out.println("size 大小"+size);
		return e;
	}
	
	/**
	 * 在数组index 处插入元素
	 * @param e
	 * @param index
	 * @return
	 */
	public E addAt(E e ,int index) {
		if(size == data.length) {
			resize(2*data.length);
		}
		for(int i = size ; i>=index ;i--) {
			data[i+1] = data [i];
		}
		data[index] = e;
		size ++;
		return e;
	}
	
	/**
	 * 在数组的首节点处插入元素
	 * @param e
	 * @return
	 */
	public E addFirst(E e) {
		return addAt(e, 0);
	}
	
	/**
	 * 动态扩容数组
	 * @param capisity
	 */
	public void resize(int capisity){
		@SuppressWarnings("unchecked")
		E[] newData = (E[]) new Object[capisity];
		System.arraycopy(data, 0, newData, 0, size);
		data = newData ;
	}
	
	/**
	 * @return  数组容量
	 */
	public int capisity (){
		return size;
	}
	
	/**
	 * 删除指定位置的元素
	 * @param index
	 * @return
	 */
	public E removeElment(int index) {
		if (index<0 || index > size) {
			throw new IllegalArgumentException("参数不合法");
		}
		E tempE = data[index];
		
		data[index] = null;
		for(int i = index ;i <size-1 ;i++) {
			data [i] = data [i +1];
		}
		size --;
		return tempE;
	}
	
	/**
	 * 删除尾节点的元素
	 * @return
	 */
	public E removeLast() {
		return removeElment(size-1);
	}	
	
	public boolean isEmpty(){
		return size == 0 ? true :false;
	}
	
	/**
	 * 获取指定位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		if(index<0 || index > size) {
			throw new IllegalArgumentException("参数不合法");
		}
		return data[index];
	}
	
	/**
	 * 获取最后一个元素
	 * @return
	 */
	public E get(){
		return get(size-1);
	}
	/* 
	 * effective java  推荐使用Arrays.toString()来打印数组，不推荐Arrays.asList();
	 */
	public String toString(){
//		System.out.println(Arrays.toString(data));
		return "" +Arrays.asList(data);
	}
	public static void main(String[] args) {
		MyArray<Integer> array = new MyArray<Integer>(10);
		array.addElement(1);
		array.addElement(2);
		array.addElement(3);
		array.addElement(4);
		array.addElement(5);
		array.addElement(6);
		array.addElement(7);
		array.addElement(8);
		array.addElement(9);
		array.addElement(10);
		array.addElement(11);
		array.addElement(12);
		array.addElement(13);
		array.addElement(14);
		array.addElement(15);
		array.addElement(16);
		array.addElement(17);
		array.addElement(18);
		System.out.println(""+array);
		array.removeElment(2);
		System.out.println(""+array);
		System.out.println(array.addAt(100, 1));
		System.out.println("插入元素后:"+array);
		array.addFirst(199);
		System.out.println("插入元素后:"+array);
	}
}
