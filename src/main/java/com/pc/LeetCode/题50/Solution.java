package com.pc.LeetCode.题50;

import java.util.HashMap;
import java.util.Map;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 */
public class Solution {

    private Map<Integer, Double> cache = new HashMap<>();

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.d;
        }
        double res = doMyPow(x, n);
        cache.clear();
        return res;
    }

    public double doMyPow(double x, int n) {

        if (cache.get(n) != null) {
            return cache.get(n);
        }

        if (n == -1) {
            cache.put(-1, 1 / x);
            return 1 / x;
        }

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            cache.put(1, x);
            return x;
        }

        if (n == 2) {
            cache.put(2, x * x);
            return x * x;
        }

        if (n == 3) {
            cache.put(3, x * x * x);
            return x * x * x;
        }

        int mid = n / 2;
        double left = doMyPow(x, mid);
        double right = doMyPow(x, n - mid);
        double result = left * right;
        cache.put(n, result);
        return left * right;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        double v = solution.myPow(2.000000, 10);
        System.out.println(v);

        double v2 = solution.myPow(2.10000, 3);
        System.out.println(v2);

        double v3 = solution.myPow(1.72777, 7);
        System.out.println(v3);

        double v4 = solution.myPow(1.00000, -2147483648);
        System.out.println(v4);

        System.out.println(-2147483648 /2);
        System.out.println(-2147483648 -(-1073741824));


    }
}
