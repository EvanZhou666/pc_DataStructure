package com.pc.八大排序算法;

import java.util.Arrays;

/**
 * @Description
 * 希尔排序
 * @Author EvanZhou
 * @Date 2019/8/15
 **/
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int gap = arr.length / 2; //分为5组 每组两个 （55,27）
        int temp;
        while (gap > 0) {
            for (int i = 0; i+gap < arr.length; i++) {
//                对每一组进行简单直接插入排序
                int lastIndex = i; // 有序序列的最后一个索引位置
                int waitIndex = i+gap; // 无序序列的第一个元素索引位置
                temp = arr [waitIndex]; //缓存带插入的元素
                while (lastIndex >= 0 && temp < arr[lastIndex]) {
//                    向后移动元素
                    arr [lastIndex+gap] = arr [lastIndex];
                    lastIndex -= gap;
                }
                arr [lastIndex+gap] = temp;
            }
            System.out.println(Arrays.toString(arr));
            gap /= 2;
        }
    }
}
