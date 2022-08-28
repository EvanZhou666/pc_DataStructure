package com.pc.LeetCode.题494;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

public class Solution3 {

    /**
     * 动态规划解法
     * 首先，如果我们把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，那么他们和 target 存在如下关系：
     * sum(A) - sum(B) = target
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * 2 * sum(A) = target + sum(nums)
     * 因此问题转换成了在背包找某些物品，把背包装满的的情况下（背包容量记作C， 则C=sum(A)），有多少种装载方法。（类似于凑零钱、爬楼梯问题）
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sumA_2 = Arrays.stream(nums).sum() + target;

        // 背包容量不可能为小数，如果sumA不能整除，return 0
        if (sumA_2 % 2 != 0 || sumA_2 < 0) {
            return 0;
        }

        int C = sumA_2 / 2;
        // 使用滚动数组进行优化将空间复杂度优化至一维
        int[] dp = new int[C + 1]; // 定义dp[i]为考虑在[0...n]物品区间凑出i的组合个数, 则 dp[i] = dp’[i] + dp‘[j- num[i]]; 0<=n<nums.length
        // 这里很细节，很特殊。这种情况下，可以理解在物品[0,0]区间内选取物品凑出0的组合个数，不选取任何物品也可看作一种组合 所以dp[0] = 1;
        dp[0] = 1;
        // 先遍历物品
        for (int j = 0; j < nums.length; j++) {
            // 再遍历背包容量，由于滚动数组空间是复用的，dp[i]依赖它前面的dp[i-nums[j]],所以数组要从后面开始覆盖
            for (int i = C; i - nums[j] >= 0; i--) {
                dp[i] += dp[i - nums[j]];
            }

        }

        return dp[C];
    }


    public static void main(String[] args) {
        int ans;

        ans = findTargetSumWays(new int[]{0}, 0);
        Assert.assertEquals(ans, 2);

        ans = findTargetSumWays(new int[]{1, 0}, 1);
        Assert.assertEquals(ans, 2);

        ans = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        Assert.assertEquals(ans, 5);

//        ans = findTargetSumWays(new int[]{0,0,1}, 1);
//        Assert.assertEquals(ans, 4);
    }
}
