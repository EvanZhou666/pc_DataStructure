package com.pc.背包问题;

import com.pc.LeetCode.common.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 完全背包问题
 * 有一个容量为c=5的背包，现在有3种物品，其价值分别为【6，10，12】，其重量分别为【1，2，3】，
 * 物品可以重复使用
 * 现在要求考虑在不超过背包容量的时候，怎么放置使得总价值最大？
 */
public class PackageAbsoluteSolution {

    /**
     * 思路1: 由于背包容量有限，可以将无限数量的物品转换为有限数量的物品。
     */
    public static int knapsack01(int[] weight, int[] v, int C) {

        List<Integer> newWeight = new ArrayList<>(8);

        List<Integer> newV = new ArrayList<>(8);

        for (int i = 0; i < weight.length; i++) {
            int max = C / weight[i];
            for (int j = 0; j < max; j++) {
                newWeight.add(weight[i]);
                newV.add(v[i]);
            }

        }
        return calMaxValueRecursive(newWeight, newV, newWeight.size() - 1, C);
    }

    /**
     * 思路2: 优化思路1，优化newWeight的空间结构，比如物品1的重量为1，在C=5时候，最多可以放置5个。
     * 但是思路一会生成[1,1,1,1,1]新的物品序列，会添加到[6,6,6,6,6]到新的weight，造成了空间的浪费。
     * 采用二进制表示法优化
     */
    public static int knapsack02(int[] weight, int[] v, int C) {

        List<Integer> newWeight = new ArrayList<>(8);

        List<Integer> newV = new ArrayList<>(8);

        for (int i = 0; i < weight.length; i++) {
            int max = C / weight[i];
            for (int j = 0; j < max; j++) {
                newWeight.add(weight[i]);
                newV.add(v[i]);
            }

        }
        return calMaxValueRecursive(newWeight, newV, newWeight.size() - 1, C);
    }

    /**
     * 递归求解怎么放置n个物品使得背包内物品总价值最大
     * @param weight 物品重量
     * @param v 价值
     * @param n 物品总数
     * @param c 背包容量，能够承受的物品总重量
     */
    private static int calMaxValueRecursive(List<Integer> weight, List<Integer> v, int n, int c) {
        // 终止条件：如果背包容量耗尽或者物品放置完成，
        if (c < 0 || n < 0) {
            return 0;
        }

        // 不将第n个物品放入背包的最大价值
        int res = calMaxValueRecursive(weight, v, n-1, c);

        // 将第n个物品放入背包的最大价值
        if (c - weight.get(n) < 0) { // 背包容量不够放入第n个物品，return
            return res;
        }
        res = Math.max(res, calMaxValueRecursive(weight, v, n-1, c - weight.get(n)) + v.get(n));

        return res;
    }

    public static void main(String[] args) {
        int ans = knapsack01(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5);
        System.out.println(ans);
        Assert.assertEquals(ans, 30);
    }
}
