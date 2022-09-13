package com.pc.八大排序算法;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 测试排序算法的辅助类
 */
public class SortHelper {

    public static int[] generateMostlySequence(int n) {
        return IntStream.range(0, n).toArray();
    }

    public static int[] generateRepeatedSequence(int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, 2);
        return arr;
    }

}
