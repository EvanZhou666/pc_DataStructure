package com.pc.LeetCode.题116.填充每个节点的下一个右侧节点指针;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *@author Evan
 *@since 2021/2/28 17:24
 */
public class Solution {


	public Node connect(Node root) {

		Deque<Node> queue = new LinkedList<>();

		if (root!=null) {
			queue.addLast(root);
		}

		List<Node> nextLevelNodes = new ArrayList<Node>();
		Node poll = null;
		while (!queue.isEmpty()) {
			poll = queue.removeFirst();
			if (poll.left!=null) {
//				nextLevelNodes.c
				nextLevelNodes.add(poll.left);
			}

			if (poll.right!=null) {
				nextLevelNodes.add(poll.right);
			}

			if (queue.isEmpty()) {
				for (int i =0;i<nextLevelNodes.size()-1;i++) {
					nextLevelNodes.get(i).next = nextLevelNodes.get(i+1);
				}
				queue.addAll(nextLevelNodes);
				nextLevelNodes.clear();
			}
		}

		return root;
	}

	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}
