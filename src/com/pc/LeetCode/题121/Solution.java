package com.pc.LeetCode.题121;

import com.pc.LeetCode.common.Assert;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 
 * 
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 对于每一天来说，只存在两种行为，要么是买入股票，要么是卖出股票。
     * 由于是计算最大利润，那么先需要考虑假如在当天卖出股票，能获得的最大利润是多少？ 最大利润等于当天股票价格-当前之前的历史最低买入价格。
     * 如此一来便能得到每天的最大利润，因此求整体的最大利润便好计算了，只要比较每天的最大例利润是哪一天就行了
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 最大利润
        int maxProfile = Integer.MIN_VALUE;

        // 定义最小买入价格
        int minBuyPrice = Integer.MAX_VALUE;

        // 遍历数组，计算在第i天卖出股票可获得的最大利润
        for (int i = 0; i < prices.length; i++) {
            maxProfile = Math.max(maxProfile, prices[i] - minBuyPrice);

            // 更新最小买入价格
            minBuyPrice = Math.min(minBuyPrice, prices[i]);
        }

        // 最大利润可能是负收益，因此这种情况要特殊考虑，不交易才是明智之选。
        return maxProfile > 0 ? maxProfile: 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;

        ans = solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});

        Assert.assertEquals(ans, 5);

    }
}
