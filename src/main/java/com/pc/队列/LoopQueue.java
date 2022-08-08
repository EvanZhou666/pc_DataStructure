package com.pc.队列;


/**
 * @author zhouzixiang 2018年10月5日 下午8:47:54 
 *循环队列  ----基于数组的实现 
 */
public class LoopQueue<E> implements Queue<E>{
	private E[] data;
	/**
	 * 队首节点
	 */
	private int front;
	/**
	 * 队尾节点
	 */
	private int tail ;
	/**
	 * size 队列的大小   
	 * 该字段可要可不要， 实际上等于(tail+data.length-front)%data.length；
	 * 
	 */
	private int size;
	private static final int DEFAULT_CAPASITY=10;
	
	public LoopQueue(){
		this(DEFAULT_CAPASITY);
	}
	
	/**
	 * @param capasity 队列的容量
	 * 实际容量分配的数组空间是用户期望的大小加1
	 */
	@SuppressWarnings("unchecked")
	public LoopQueue(int capasity) {
		data = (E[]) new Object[capasity+1];
		front = tail =0;
		size = 0;
	}
	

	@Override
	public boolean isEmpty() {
		
		return front==tail? true:false;
	}

	@Override
	public int size() {
//		System.out.println((size+" || "+((tail+data.length-front)%data.length)));
		return size;
	}

	@Override
	public E deQueue() {
		if (front == tail ) {
			throw new IllegalArgumentException("队列为空 ，没有队首元素");
		}
		E tempE = data[front] ;
		data[front] =null;
		front = (front+1)%data.length;
		return tempE;
	}

	/**
	 * 队列扩容
	 * @param capasity
	 */
	private void resize(int capasity) {
		@SuppressWarnings("unchecked")
		E[] newData = (E[]) new Object[capasity+1];
		for (int i = front ,j=0; j < (tail+data.length-front)%data.length; i=(i+1)%data.length,j++) {
			newData[j] =  data[i];
		}
		data = newData;
	}

	@Override
	public E enQueue(E e) {
		if (front == (tail+1)%data.length) {
			resize(data.length*2);
		}
		data[size] = e;
		size++;
		tail = (tail +1)%data.length;
		return e;
	}

	@Override
	public E getFront() {
		if (front == tail ) {
			throw new IllegalArgumentException("队列为空，没有队首节点");
		}
		return data[tail-1];
	}
	
	public String toString(){
		StringBuilder builder  = new StringBuilder();
		builder.append("size:"+(tail+data.length-front)%data.length+"[");
		for (int i = front,j=0; j < (tail+data.length-front)%data.length; i=(i+1)%data.length,j++) {
			builder.append(data[i]+",");
		}
		builder.append("]");	
		return builder.toString();
		
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LoopQueue<Integer>(2);
		for(int i =0 ;i<5;i++){
			queue.enQueue(i);
			queue.size();
		}
		System.out.println(queue.toString());
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
			System.out.println(queue.toString());
		}
//		queue.deQueue();
	}
}
