package com.pc.二分搜索树;

/**
 * @author 10430
 *下午10:41:23 2018年10月21日
 * 
 */
public class BinarySearchTree <E extends Comparable<E>>{
	
	Node root;
	
	public BinarySearchTree (E e) {
		root = new Node(e);
	}
	
	public BinarySearchTree () {
		root = null;
	}
	/**
	 * 向二分搜索树中增加节点 非递归实现
	 * @param e
	 */
	public void add (E e) {
		if (root == null) {
			root = new Node(e);
			return;
		}
		Node dummyRoot = root ;
		while (true){
			if (e.compareTo(dummyRoot.data)<0 ) {
				if (dummyRoot.left == null) {
					dummyRoot.left = new Node(e);
					return;
				} else {
					dummyRoot = dummyRoot.left;
					continue;
				}

			}
			
			if (e.compareTo(dummyRoot.data)>0 ) {
				if (dummyRoot.right == null) {
					dummyRoot.right = new Node(e);
					return;
				} else {
					dummyRoot = dummyRoot.right;
					continue;
				}

			}
		}
	
	}
	
	/**
	 * 查询tree中是否包含某个节点 递归实现
	 * @param e
	 */
	public boolean find (E e) {
		return contains(root ,e);
	}
	
	private boolean contains (Node root , E e) {
		if (root == null) {
			return false;
		} 
		
		if (root.data.compareTo(e)>0) {
			return contains(root.left,e);
		} else if (root.data.equals(e)) {
			return true ;
		}else {
			return contains(root.right,e);
		}
		
	}
	
	/**
	 * 二分搜索树 查询元素 （非递归实现）
	 * @param e
	 * @return
	 */
	public boolean findNotRecursive (E e) {
		if (root == null) {
			return false;
		}
		Node dummyRoot = root ;
		while (true){
			if (e.compareTo(dummyRoot.data) == 0) {
				return true;
			}
			
			if (e.compareTo(dummyRoot.data)<0 ) {
				if (dummyRoot.left == null) {
					return false;
				} else {
					dummyRoot = dummyRoot.left;
					continue;
				}

			}
			
			if (e.compareTo(dummyRoot.data)>0 ) {
				if (dummyRoot.right == null) {
					return false;
				} else {
					dummyRoot = dummyRoot.right;
					continue;
				}

			}
		}
	}
	private class Node {
		private E data;
		
		public Node left;
		
		public Node right;
		
		private Node(E e) {
			this.data = e;
			left = null;
			right = null ;
		}
	}
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(10);
		tree.add(8);
		tree.add(12);
		tree.add(6);
		tree.add(100);
		tree.add(128);
//		tree.findNotRecursive(6);
		System.out.println(tree.find(127)+" "+tree.findNotRecursive(127));
		System.out.println(tree.find(6)+" "+tree.findNotRecursive(6));
		System.out.println(tree.find(11)+" "+tree.findNotRecursive(11));
		System.out.println(tree.find(128)+" "+tree.findNotRecursive(128));
	}
}
