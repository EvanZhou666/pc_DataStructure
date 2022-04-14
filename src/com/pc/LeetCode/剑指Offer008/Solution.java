package com.pc.LeetCode.剑指Offer008;


import com.pc.LeetCode.common.Assert;

import java.util.Arrays;


public class Solution {

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。<br/>
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。<br/>
     * <p>
     * 来源：力扣（LeetCode）<br/>
     * 链接：https://leetcode-cn.com/problems/2VG8Kg<br/>
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<br/>
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        boolean haxFind = false;
        int length = nums.length;

        printArray(nums, l, r);
        while (r < length) {
            if (sum(nums, l, r) < target) {
                // 如果从来没有找到过可行解，则扩大窗口
                if (!haxFind) { // 别问我，这里又是怎么写出来的。为什么要判断haxFind，提交了LeetCode 5次，才得到的这个边界条件
                    r++;
                    printArray(nums, l, r);
                } else {
                    l++;
                    r++;
                    printArray(nums, l, r);
                }

            } else {   // l，r 已经是一个潜在的候选答案了，找到了可行解，现在找最优解，考虑不断缩小窗口
                haxFind = true;
                if (sum(nums, l + 1, r) >= target) {
                    // 标记一下，我们已经找到了一个可行解.
                    l++;
                    printArray(nums, l, r);
                } else { // 找到可行解后，我们窗口不会再扩大了，只会再缩小。因为我们要找最优解，最优解的子串长度一定是小于等于当前窗口的长度，所以滑动窗口长度保持不变，整体向后移动1格
                    l++;
                    r++;
                    printArray(nums, l, r);

                }

            }
        }
        return haxFind ? r - l + 1 : 0;
    }

    public static int sum(int[] nums, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 打印数组以及双指针，方便调试用
     *
     * @param nums
     * @param l
     * @param r
     */
    private static void printArray(int[] nums, int l, int r) {
        if (r >= nums.length) {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=nil");
        } else {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=" + r + " sum=" + sum(nums, l, r));
        }
    }


    /**
     * 官方解法
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int i = 0;

//        i = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
//        Assert.assertEquals(i, 2);

//        i = minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1});
//        Assert.assertEquals(i, 0);

//        i = minSubArrayLen(15, new int[]{1, 2, 3, 4, 5});
//        Assert.assertEquals(i, 5);

//        i = minSubArrayLen(11, new int[]{1, 2, 3, 4, 5});
//        Assert.assertEquals(i, 3);

        i = minSubArrayLen(15, new int[]{5, 1, 3, 5, 10, 7, 4, 9, 2, 8});
        Assert.assertEquals(i, 2);
    }
}
