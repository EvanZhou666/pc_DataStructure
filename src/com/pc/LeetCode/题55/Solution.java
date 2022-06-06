package com.pc.LeetCode.题55;

import com.pc.LeetCode.common.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 */
public class Solution {


    /**
     * 判断是否能够跳跃到数组的末尾元素
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int currentIndex  = 0;
        int target = nums.length - 1;

        // 记录"死胡同"，某些位置是永远不能在从当前位置跳跃到终点的,为了回溯算法进行"剪枝"操作
        Set<Integer> set = new HashSet<>();

        // 处理边界问题，如果当前位置已经就是数组的末尾了，自然不用处理
//        if (currentIndex ==  target) {
//            return true;
//        }

        // 我理解更像是回溯算法，等会看答案怎么做的
//        for (int i = nums[currentIndex]; i > 0; i--) {
        // 回溯算法优化，看当前位置距离目标位置还有多远，每次都从选择这个差额距离进行跳跃，这样做的目的是为了进行"剪枝"操作
        for (int i = Math.min(nums[currentIndex], target - currentIndex); i > 0; i--) {
            // 每次都是选择最大的补数进行跳跃
            boolean canJump = canJump(currentIndex + i, nums, target, set);
            if (canJump) {
                return true;
            }
        }

        // 处理边界问题，如果当前位置已经就是数组的末尾了，自然不用处理
        return currentIndex ==  target;
    }

    /**
     * 递归调用，更像是回溯，如果不能跳跃到最后，返回到上一层
     * @param currentIndex 当前所处的索引位置
     * @param nums 原数组
     * @param targetIndex 希望到达的目标位置
     * @return
     */
    private boolean canJump(int currentIndex, int[] nums, int targetIndex, Set<Integer> set ) {

        if (currentIndex == targetIndex) {
            return true;
        }

        if (currentIndex > targetIndex) {
            return false;
        }

        // "剪枝"，如果当前节点是死胡同
        if (set.contains(currentIndex)) {
            return false;
        }

        if (nums[currentIndex] == 0) {
            return false;
        }

        for (int i = nums[currentIndex]; i > 0; i--) {
            // 每次都是选择最大的补数进行跳跃
            boolean canJump = canJump(currentIndex + i, nums, targetIndex, set);
            if (canJump) {
                return true;
            }
        }
        set.add(currentIndex);
        return false;
    }

    public static void main(String[] args) {
        Solution solution =  new Solution();
        boolean canJump;
        canJump = solution.canJump(new int[]{2,3,1,1,4});
        Assert.assertIsTrue(canJump);

//        canJump = solution.canJump(new int[]{3,2,1,0,4});
//        Assert.assertIsFalse(canJump);

//        canJump = solution.canJump();
        Assert.assertIsTrue(canJump);
    }


}
