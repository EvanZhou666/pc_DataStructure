package com.pc.LeetCode.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *@author Evan
 *@since 2021/2/21 22:00
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	public static TreeNode conver2Tree (List<List<Integer>> list) {
		Integer rootVal = list.get(0).get(0);
		TreeNode root = new TreeNode(rootVal);
		List<TreeNode> roots = new ArrayList<>();
		roots.add(root);

		for (int i=1;i<list.size();i++) {
			List<TreeNode> child = recoverFromLevel(roots, list.get(i));
			roots = child;
		}
		return root;

	}

	private static List<TreeNode> recoverFromLevel(List<TreeNode> parents,List<Integer> intValues) {
		List<TreeNode> list = new ArrayList<>();
		for (int i =0;i<parents.size();i++) {
			if (parents.get(i)!=null) {
				if (intValues.get(i*2)!=null) {
					TreeNode left= new TreeNode(intValues.get(i*2));
					parents.get(i).left = left;
				}
				if (intValues.get(i*2+1)!=null) {
					TreeNode right= new TreeNode(intValues.get(i*2+1));
					parents.get(i).right = right;
				}
				list.add(parents.get(i).left);
				list.add(parents.get(i).right);
			} else {
				list.add(null);
				list.add(null);
			}
		}
		return list;
	}

	public static List<Integer> listOfElecments(Integer ... intvalues) {
		List<Integer> list = new ArrayList<>();
		for (Integer intvalue : intvalues) {
			list.add(intvalue);
		}
		return list;
	}

	/**
	 * 层序遍历二分搜索树
	 * @param root
	 */
	public static void levelOrder(TreeNode root){
		LinkedList<TreeNode> quque = new LinkedList();
		if (root!=null){
			quque.offer(root);
			doLevelOrder(quque);
		}
	}

	//    层序遍历：每次出队一个元素，便将该节点的左右孩子节点分别入队。
	private static void doLevelOrder(LinkedList queue){
		while (!queue.isEmpty()){
			TreeNode poll = (TreeNode)queue.poll();
			System.out.println(poll.val);
			if (poll.left!=null){
				queue.offer(poll.left);
			}
			if (poll.right!=null){
				queue.offer(poll.right);
			}
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(listOfElecments(1));
		ll.add(listOfElecments(null,3));
		ll.add(listOfElecments(null,null,5,null));
		TreeNode treeNode = conver2Tree(ll);
		System.out.println(treeNode);
	}
}
