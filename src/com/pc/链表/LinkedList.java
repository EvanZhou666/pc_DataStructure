package com.pc.链表;

import java.util.Iterator;


public class LinkedList<E> {
	/**
	 * 虚拟头节点
	 */
	private Node dummyHead;
	/**
	 * 指向下一个节点的索引位置，同时代表链表的长度
	 */
	private int size;
	
	public LinkedList() {
		dummyHead = new Node();
		size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty() {
		return size==0? true: false;
	}
	
	public Node addAt(E e ,int index) {
		Node node = new Node(e);
		Node pre = dummyHead;
		for (int i =0 ;i<index;i++){
			pre = pre.next;
		}
		node.next = pre.next;
		pre.next = node;
		size++;
		return node;
		
	}
	
	public Node addFirst (E e){
		return addAt(e, 0);
	}
	
	public Node addLast(E e) {
		return addAt(e, size);
	}
	
	public Node removeAt(int index) {
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		Node node = pre.next; 
		pre.next = node.next;
		node.next = null;
		size --;
		return node;
	}
	
	public Node removeFirst(){
		return removeAt(0);
	}
	
	public Node RemoveLast(){
		return removeAt(size-1);
	}
	
	public E get (int index) {
		Node cur = dummyHead.next;
		for (int i =0 ;i<index;i++){
			cur = cur.next;
		}
		return cur.data;
	}
	public E getFirst(){
		return get(0);
	}
	
	public E getLast(){
		return get(size-1);
	}
	
	/**
	 * 使用递归删除 链表中值为e的所有元素
	 * @param e
	 */
	public void remove(E e) {
		if (dummyHead.next ==null) {
			throw new RuntimeException("当前链表是空的");
		}
		
		solveRemove(dummyHead.next, e);
	}
	
	/**
	 * @param dummyHead 虚拟头结点
	 * @param e
	 */
	private void solveRemove(Node head,E e){
		//解决最小的问题
		if (head == null) {
			return;
		}
		
		if (head.data.equals(e)) {
//			删除head，继续迭代head.next
			head = head.next;
			size--;
			solveRemove(head, e);
		} else {
			solveRemove(head.next, e);
		}
	}
	
	
	
	public boolean contains(E e) {
		Node node = new Node(e);
		Node cur = dummyHead.next;
		while(cur!=null) {
			if (cur.data.equals(node.data)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	public Iterator<Node> iterator(){
		return new LinkIterator();
	}
	
	
	public String toString () {
		//System.out.println("size:"+size+"["+dummyHead.toString()+"]");
		StringBuilder builder = new StringBuilder("size:"+size+"["+dummyHead.toString()+"]");
	/*	Node cur = dummyHead.next;
		builder.append("size:");
		builder.append(size);
		builder.append("dummyhead");
		builder.append(dummyHead.toString()); 
		builder.append("[");
		
		while (cur.next != null){
			builder.append(cur.toString());
			cur = cur.next;
		}
		builder.append("]");*/
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
			return data+"-->"+next;
		}
	}
	
	private class LinkIterator implements Iterator<Node> {
		Node cur = dummyHead;
		@Override
		public boolean hasNext() {
			if(cur.next != null) return true; 
			return false;
		}

		@Override
		public Node next() {
			Node node = cur.next;
			cur = cur.next;
			return node;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remome Operation unsupported");
		}
		
	}
	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		for(int i =0 ;i<6;i++){
			linkedList.addLast(i);
			System.out.println(linkedList);
		}
		linkedList.addLast(1000);
		System.out.println(linkedList);
		Iterator<LinkedList<Integer>.Node> iterator = linkedList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("----"+linkedList.contains(1000));
		for(int i =0 ;i<6;i++){
//			linkedList.removeFirst();
			linkedList.RemoveLast();
			System.out.println(linkedList);
		}
		
		
		
		
	}
}
