package com.pc.链表.使用链表实现队列;

import com.pc.队列.Queue;

public class LinkedListQueue<E> implements Queue<E>{
	Node head;
	Node tail;
	int size;
	public  LinkedListQueue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E deQueue() {
		if (head == null) 
			throw new RuntimeException("head is null,队列为空");
		Node node = head;
		head = node.next; 
		node.next =null;
		if (head == null) {
			 tail =head;
		}
		size--;
		return node.data;
	}

	@Override
	public E enQueue(E e) {
		if (tail == null) {
			tail = new Node(e);
			head = tail;
		}else {
			Node node = new Node(e);
			tail.next = node;
			tail = node;
		}
		size++;
		return e;
	}

	@Override
	public E getFront() {
		return head.data;
	}
	
	public String toString(){
		Node cur =head;
		StringBuilder builder = new StringBuilder();
		while(cur!=null) {
			builder.append(cur.toString());
			cur = cur.next;
		}
		return builder.toString();
	}
	
	private class Node{
		E data;
		Node next;
		public Node(){
			data = null;
			next = null;
		}
		public Node(E e, Node next) {
			this.data = e;
			this.next = next;
		}
		public Node (E e) {
			this.data = e;
			this.next = null;
		}
		
		public String toString(){
			return data+"-->";
		}
	}
	
	public static void main(String[] args) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		for(int i =0;i<5;i++){
			queue.enQueue(i);
			System.out.println(queue.toString());
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(queue.deQueue());;
			System.out.println(queue.toString());
		}
		System.out.println("-队列大小--"+queue.size());
	}
}
