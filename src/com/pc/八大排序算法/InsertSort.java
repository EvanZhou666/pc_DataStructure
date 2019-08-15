package com.pc.八大排序算法;

import java.util.Arrays;

/**
 * @Description
 * 选择排序法:
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 * @Author EvanZhou
 * @Date 2019/8/15
 **/
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = new  int[]{5,2,4,3,8};
//        将第一个元素看做有序序列 ，后面的看做无序序列
        int temp;
        for (int i =0 ;i<arr.length-1;i++) {
            int m = i;// 有序序列的最后一个索引位置
            temp = arr [i+1]; //（无序列表的第一个元素）要插入的元素
            while ( m >= 0 && temp<arr[m]) { // 很容易在这里犯错，如果把这两个条件交换位置 就会出错,还有把temp写成arr[insertIndex]也会出错
                arr[m+1] = arr [m];
                m --;
            }
            arr[m+1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

}
