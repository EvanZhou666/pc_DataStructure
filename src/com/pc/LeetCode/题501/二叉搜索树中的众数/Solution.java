package com.pc.LeetCode.题501.二叉搜索树中的众数;

import com.pc.LeetCode.common.TreeNode;

import java.util.*;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].n
 *@since 2021/3/6 18:53
 */
public class Solution {

	Map<Integer,Integer> map = new HashMap<>();

	/**
	 * 没有想到其它方法，第一次深度遍历，用map记录每个数出现的次数
	 * @param root
	 * @return
	 */
	public int[] findMode(TreeNode root) {
		List<Integer> list =  new ArrayList<>();
		dfs(root);
		int maxCount = 0;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > maxCount) {
				maxCount = entry.getValue();
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxCount) {
				list.add(entry.getKey());
			}
		}

		int[] res = new int[list.size()];
		for (int i=0;i<list.size();i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(root.val)) {
			map.put(root.val,1);
		} else {
			map.put(root.val,map.get(root.val)+1);
		}
		dfs(root.left);
		dfs(root.right);
	}

}
