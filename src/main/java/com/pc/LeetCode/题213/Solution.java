package com.pc.LeetCode.题213;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 */
public class Solution {

    /**
     * 由于题目是环形数组，所以问题可以分为两次求解，最后比较最大值
     * 1. 考虑从第1间房开始打劫，因此考虑打劫的打劫区间为[0,n-2]
     * 2. 不考虑从第1间开始打劫，因此考虑打劫的打劫区间为[1,n-1]
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int ans1 = doRob(nums, 0, n - 2);
        int ans2 = doRob(nums, 1, n - 1);
        return Math.max(ans1, ans2);
    }

    /**
     * f(0) = max(v(start) + f(start+2)、... v(start+1)+f(start+3)、... v(end-2)、v(end-1))
     * 且
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int doRob(int[] nums, int start, int end) {
        int n = end - start + 1;
        // dp[i] = 考虑在[i+start,end]区间打劫可以获得的最大利润
        int[] dp = new int[n];
        dp[n - 1] = nums[end];
        int maxValue = 0;
        // dp数组小标和nums的下表偏移量为start
        for (int i = n - 2; i >= 0; i--) {
            maxValue = Math.max(Math.max(nums[end - 1], nums[end]), maxValue);
            for (int j = i + start; j < end - 1; j++) {
                maxValue = Math.max(nums[j] + dp[j - start + 2], maxValue);
            }
            dp[i] = maxValue;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int ans = 0;
        ans = rob(new int[]{1,2,3,1});
        System.out.println(ans);
    }

}
