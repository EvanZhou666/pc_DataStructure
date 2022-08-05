package com.pc.LeetCode.题128;

import com.pc.LeetCode.common.Assert;
import com.pc.并查集.MyUnionFind;

import java.util.*;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class Solution {
    public int longestConsecutive(int[] nums) {

        MyUnionFind myUnionFind = new MyUnionFind(nums.length);
        Map<Integer, Integer> maps = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (!maps.containsKey(nums[i])) {
                // 如果存在 nums[i] - 1存在，则num[i]-1和num[i]节点是连通的
                if (maps.containsKey(nums[i] - 1)) {
                    myUnionFind.union(i, maps.get(nums[i] - 1));
                }
                if (maps.containsKey(nums[i] + 1)) {
                    myUnionFind.union(i, maps.get(nums[i] + 1));
                }
                maps.put(nums[i], i);

            }
        }

        return myUnionFind.getMaxConnectSize();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans;
        ans = solution.longestConsecutive(new int[]{1,2,3,4,5});
        Assert.assertEquals(ans, 5);
        ans = solution.longestConsecutive(new int[]{100,4,200,1,3,2});
        Assert.assertEquals(ans, 4);
        //                                    [0,4,2,3,5,3]

//        int ans = solution.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1});
        ans = solution.longestConsecutive(new int[]{1,2,0,1});
        Assert.assertEquals(ans, 3);
    }
}
