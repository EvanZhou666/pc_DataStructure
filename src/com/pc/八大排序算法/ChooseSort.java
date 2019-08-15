package com.pc.八大排序算法;

import java.util.Arrays;

/**
 * @Description
 * 选择排序
 * @Author zhouzixiang
 * @Date 2019/8/15
 **/
public class ChooseSort {
    public static void main(String[] args) {
        int arr[] = new  int[]{5,2,4,3,8};
        int minIndex; //假定的最小数的索引位置
        for (int i =0;i<arr.length-1;i++) {
            minIndex = i;
            for (int j=i+1;j<arr.length;j++) {
                if (arr[minIndex]>arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

}
