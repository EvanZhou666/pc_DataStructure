package com.pc.LeetCode.题300;

import com.pc.LeetCode.common.Assert;
import com.pc.LeetCode.common.GoodQuestion;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
@GoodQuestion(type = "贪心算法系列")
@GoodQuestion(type = "二分搜索系列")
public class Solution2 {

    /**
     * 官方解法2：贪心算法+二分搜索
     * 定义：
     * d[j]表示长度为j的最长上升子序列的末尾元素的最小值
     * d[i]表示长度为i的最长上升子序列的末尾元素的最小值
     * 推论：d[i] 是关于i的严格单调上升的函数。
     * 用数学公式表达推论：j < i and d[j] < d[i]
     * 证明：反证法，如果d[i]不是关于i的严格单调上升的函数，则假设存在 d[j] >= d[i] and j < i；
     * 因为d[i]是严格单调的，如果从d[i]的末尾删除i-j个元素，使之长度变为j，则新的子序列的最后一个元素x一定小于d[i]，即d[j]<d[i]
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


    public static void main(String[] args) {

        int ans;
        ans = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        Assert.assertEquals(ans, 4);

//        ans = lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
//        ans = lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12});
//        Assert.assertEquals(ans, 6);

    }

}
