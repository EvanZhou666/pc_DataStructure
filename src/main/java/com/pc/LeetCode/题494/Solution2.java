package com.pc.LeetCode.题494;

import com.pc.LeetCode.common.Assert;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;

public class Solution2 {

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
        if (sumA_2 % 2 != 0) {
            return 0;
        }

        int C = sumA_2 / 2;
        // 定义dp[i]凑出target=i的组合个数, 则
        // dp[i] = sum( dp[i-nums[0]] 、dp[i-nums[1]] .... + dp[i-nums[n]]]) 其中n<nums.length
        // 上面的状态转移方程是错误的，只使用于物品列表不会重复的情况。
        int[] dp = new int[C + 1]; // 定义dp[i]凑出i的组合个数, 则 dp[i] = dp’[i] + dp‘[j- num[i]]
        for (int i = 0; i < dp.length; i++) {
            if (nums[0] == 0) {
                if (nums[0] == i) {
                    dp[i] = 2;
                }
            } else {
                if (nums[0] == i) {
                    dp[i] = 1;
                }
            }
        }
        if (nums[0] != 0) {
            dp[0] = 1;
        }

        System.out.println(Arrays.toString(dp));

        for (int j = 1; j < nums.length; j++) {
            for (int i = C; i - nums[j] >= 0; i--) {
                dp[i] += dp[i - nums[j]];
            }

            System.out.println(Arrays.toString(dp));
        }

        return dp[C];
    }


    public static void main(String[] args) {
        int ans;

        ans = findTargetSumWays(new int[]{0}, 0);
        Assert.assertEquals(ans, 2);

        ans = findTargetSumWays(new int[]{1,0}, 1);
        Assert.assertEquals(ans, 2);

        ans = findTargetSumWays(new int[]{1,1,1,1,1}, 3);
        Assert.assertEquals(ans, 5);

//        ans = findTargetSumWays(new int[]{0,0,1}, 1);
//        Assert.assertEquals(ans, 4);
    }
}
