package com.pc.LeetCode.题605;

import com.pc.LeetCode.common.Assert;

/**
 * 605. 种花问题
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= flowerbed.length <= 2 * 104
 * flowerbed[i] 为 0 或 1
 * flowerbed 中不存在相邻的两朵花
 * 0 <= n <= flowerbed.length
 */
public class Solution {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int maxCanPlace = 0;

        // 前一个已经种上花的位置
        int pre = -2;

        // 依次遍历每个位置
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) { // 如果当前位置已经种上花了，更新"前一个"已经种上花的位置
                pre = i;
            } else {
                if (i - pre >= 2) { // 如果距离上一个位置间隔数超过1
                    if (i < flowerbed.length - 1 && flowerbed[i + 1] == 0) { // 并且下一个位置没有种上花
                        maxCanPlace++;
                        pre = i;
                    } else if (i == flowerbed.length - 1) { // 如果当前位置在数组最后一个位置
                        maxCanPlace++;
                        pre = i;
                    }
                }
            }

        }
//        System.out.println(maxCanPlace);
        return maxCanPlace >= n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Boolean b;
        b = solution.canPlaceFlowers(new int[]{1, 0, 0, 0,}, 1);
        Assert.assertIsTrue(b);

        b = solution.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2);
        Assert.assertIsFalse(b);

        b = solution.canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0}, 2);
        Assert.assertIsTrue(b);
    }
}
