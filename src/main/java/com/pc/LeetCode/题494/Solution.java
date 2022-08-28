package com.pc.LeetCode.题494;

import com.pc.LeetCode.common.Assert;

public class Solution {
    private static int ans;

    /**
     * 使用回溯算法,初始值维currentVal，对于每个nums[i]，有两种选择，要么加num[i],要么减nums[i]
     * 最后只要检查currentVal是否等于target,等于则表示一条有效路径
     *
     * 由于nums的数据规则只有20个，因此回溯算法也是可以通过提交的。
     * 时间复杂度：O(2^n)
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     *
     */
    public static int findTargetSumWays(int[] nums, int target) {
        ans = 0;
        dfs(nums, 0, 0, target);
        return ans;
    }

    private static void dfs(int[] nums, int i, int currentVal, int target) {

        if (i == nums.length) {
            if (currentVal == target) {
                ans += 1;
            }
            return;
        }

        dfs(nums, i + 1, currentVal + nums[i], target);
        dfs(nums, i + 1, currentVal - nums[i], target);

    }

    public static void main(String[] args) {
        int ans;
        ans = findTargetSumWays(new int[]{1,1,1,1,1}, 3);
        Assert.assertEquals(ans, 5);
    }
}
