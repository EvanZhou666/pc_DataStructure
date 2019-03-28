package com.pc.链表.递归;

/**
 * @author zhouzixiang
 * 使用递归来实现 链表的增删该查
 */

import java.util.Iterator;


public class LinkedList2<E> {
	/**
	 * 虚拟头节点
	 */
	private Node dummyHead;
	/**
	 * 指向下一个节点的索引位置，同时代表链表的长度
	 */
	private int size;
	
	public LinkedList2() {
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
	
	public E find(E e) {
		return Find(dummyHead.next,e,0);
	}
	

	/**
	 * 使用递归思想查找值为val的元素
	 * @param head
	 * @param val
	 * @param depth 递归的深度  方便调试
	 * @return
	 */
	private E Find(Node head,E val,int depth) {
		for(int i =0 ;i<depth;i++) {
			System.out.print("--");
		}
		System.out.println(head);
		if (head.next == null) {
			return null;
		}
		if (head.data .equals(val)) {
			return head.data;
		}
		
		for(int i =0 ;i<depth;i++) {
			System.out.print("--");
		}
		System.out.println("call Find");
		 E find = Find(head.next, val,depth+1);
		 
		 for(int i =0 ;i<depth;i++) {
				System.out.print("--");
			}
		 System.out.println("After Return:"+find);
		return find;
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
	/*	if (dummyHead.next ==null) {
			throw new RuntimeException("当前链表是空的");
		}
		
		solveRemove(dummyHead, dummyHead.next,e);*/
		
		solveRemove2(dummyHead ,e);
	}
	
	/**
	 * @param pre 头结点前一节点
	 * @param head 头结点  （一个头结点 就可以代表一条链表）
	 * @param e
	 */
	private void solveRemove(Node pre, Node head ,E e){
		//解决最小的问题
		if (head == null) {
			return;
		}
		
		if (head.data.equals(e)) {
			pre.next = head.next;
			head.next = null;
			size--;
			solveRemove(pre, pre.next,e);
		} else {
			solveRemove(head, head.next,e);
		}
	}
	
	/**
	 * 用递归来删除链表中元素 写法二
	 * @param head  头结点
	 * @param e  要删除的值
	 * @return  删除后的链表
	 */
	private Node solveRemove2(Node head ,E e) {
		if (head == null) {
			return null;
		}

		if (head.data == e) {
			size --;
			return solveRemove2(head.next, e);
		} else {
			head.next =  solveRemove2(head.next, e);
			return head;
		}
		
//		等价于于上面的写法
		//  代表一条子链表
/*		 head.next = solveRemove2(head.next, e);
		 return head.data==e ? head.next:head;*/
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
		StringBuilder builder = new StringBuilder("size:"+size+"["+dummyHead.toString()+"]");
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
//		递归删除测试：
		LinkedList2<Integer> list = new LinkedList2<Integer>();
		list.addLast(6);
		list.addLast(1);
		list.addLast(2);
		list.addLast(6);
		list.addLast(4);
		list.addLast(6);
		list.addLast(5);
		list.find(4);
//		System.out.println(list);
//		list.remove(6);
//		System.out.println(list);
		
	}
}

