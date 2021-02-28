package com.pc.LeetCode.题113.路径总和II;

import com.pc.LeetCode.common.TreeNode;

import java.util.*;

import static com.pc.LeetCode.common.TreeNode.conver2Tree;
import static com.pc.LeetCode.common.TreeNode.listOfElecments;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/2/27 23:06
 */
public class Solution {

	private int targetSum;

	List<List<Integer>> result = new ArrayList<>();

//	Stack<Integer> stack = new Stack<>();
//  用双端队列的速度明显快于用Java.Util.Stack
	Deque<Integer> path = new LinkedList<Integer>();

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		this.targetSum = targetSum;
		dfs(root, targetSum);
		return result;
	}

	public void dfs(TreeNode root, int targetSum) {

		if (root == null) {
			return;
		}

//		stack.push(root.val);
		path.addLast(root.val);

		// 遇到叶子节点  “剪枝”
		if (root.left == null && root.right == null) {
			if (targetSum == root.val) {
				result.add(new ArrayList<>(path));
				path.pollLast();
				return;
			}
		}

		dfs(root.left, targetSum - root.val);

		dfs(root.right, targetSum - root.val);

		path.pollLast();
	}

	public static void main(String[] args) {
		List<List<Integer>> ll = new ArrayList<>();
		ll.add(listOfElecments(5));
		ll.add(listOfElecments(4,8));
		ll.add(listOfElecments(11,null,13,4));
		ll.add(listOfElecments(7,2,null,null,null,null,5,1));
		TreeNode root = conver2Tree(ll);
		System.out.println(root);
		Solution solution = new Solution();
		List<List<Integer>> lists = solution.pathSum(root, 22);
		System.out.println(lists);
	}

}
