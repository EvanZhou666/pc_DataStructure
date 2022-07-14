package com.pc.LeetCode.题135;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 示例1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 */
class Solution {

    public int candy(int[] ratings) {

        int[] candys = new int[ratings.length];
        int totalCandy = 0;
        candys[0] = 1;

        int left = 0;
        int right = left + 1;

        // 第1次循环，如果右边邻接的孩子比左边的邻接孩子高，保证右边的邻接孩子分到的糖果比左边的多
        while (right < ratings.length) {
            if (ratings[right] > ratings[left]) {
                candys[right] = candys[left] + 1;
            } else {
                candys[right] = 1;
            }
            left++;
            right++;
        }

        right = ratings.length - 1;
        left = right - 1;
        // 第2次循环，如果左边邻接的孩子比右边边的邻接孩子高，保证左边的邻接孩子分到的糖果比右边边的多

        while (right > 0) {
            // 左邻接孩子身高大于右邻接孩子身高，并且左邻接孩子糖果不大于右邻接孩子，需要让左邻接孩子比右邻接孩子多分1个糖果
            if (ratings[left] > ratings[right] && candys[left] <= candys[right]) {
                candys[left] = candys[right] + 1;
            }
            // 这一段不能放开，放开提交就得不到正确的结果。
            // 不放开意味着：如果左邻接孩子和右邻接孩子身高一致，允许左孩子比右孩子的糖果少的
            // ps：个人感觉是不合理的
            /*else if (ratings[left] == ratings[right]) {
                candys[left] = Math.max(candys[left], candys[right]);
            }*/
            totalCandy +=  candys[left];
            left--;
            right--;
        }

//        打印每个孩子分配的糖果情况
        System.out.println(Arrays.toString(candys));
        return totalCandy + candys[ratings.length-1];
    }


    /**
     * 官方解法
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            int max = Math.max(left[i], right);
            ret += max;
            left[i] = max;
        }
        System.out.println(Arrays.toString(left));
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int candy = 0;
        candy = solution.candy(new int[]{1, 0, 2});
        Assert.assertEquals(5, candy);

        candy = solution.candy(new int[]{1, 3, 5, 4, 2});
        Assert.assertEquals(9, candy);
//
        candy = solution.candy(new int[]{1,3,2,2,1});
        Assert.assertEquals(7, candy);
//
//        candy = solution.candy(new int[]{1,2,87,87,87,2,1});
//
        candy = solution.candy(new int[]{1,2,87,87,87,2,1});
        Assert.assertEquals(13, candy);

        candy = solution.candy2(new int[]{1,2,87,87,87,2,1});

//身高        [1, 2, 87, 87, 87, 2, 1]
//糖果        [1, 2, 3,  1,  3,  2, 1]

        Assert.assertEquals(13, candy);

    }
}
