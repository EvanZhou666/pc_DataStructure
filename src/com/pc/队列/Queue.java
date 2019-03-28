package com.pc.队列;

public interface Queue <E>{
	boolean isEmpty();
	int size();
	
	/**
	 * 出队
	 * @return
	 */
	E deQueue();
	
	/**
	 * 入队
	 * @return
	 */
	E enQueue(E e);
	
	/**
	 * 查看队首元素
	 * @return
	 */
	E getFront();
}
