package com.pc.八大排序算法;

import java.util.Arrays;

/**
 * 优化归并排序的空间
 */
public class MergeSort2 {

    // 申请辅助数组
    int[] helper;

    public void mergerSort(int[] arr) {
        helper = new int[arr.length];
        doMergeSort(arr, 0, arr.length - 1);
    }

    // 数组区间左闭右闭
    private void doMergeSort(int[] arr, int left, int right) {
        // left = right说明只剩下一个元素了，没有必要再拆分了
        if (left >= right) {
            return;
        }
        // 数组分为两半
        int mid = left + (right - left) / 2;
        // 左边部分
        doMergeSort(arr, left, mid);
        doMergeSort(arr, mid + 1, right);
        // 合并[left,mid]和[mid+1.right]区间的元素
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {

        // 将要排序的子数组复制到辅助数组内,因为我们函数签名没有返回值，所以只能修改arr数组
        for (int i = left; i <= right; i++) {
            helper[i] = arr[i];
        }

        int i = left; // 左半边数组下一个要比较的地方
        int j = mid + 1; // 右半边数组下一个要比较的地方
        int k = left; // 合并后的元素要存储的位置
        // 本质是对help[0,mid]和helper[mid+1, right]两个有序数组进行合并
        // 且两个数组长度可能不一样，且一定有一个先结束
        while (i <= mid && j <= right) {
            // 比较左右半段数组，如果右边大，选择右边存入arr，右边指针加1
            if (helper[i] <= helper[j]) {
                arr[k] = helper[i];
                i ++;
            } else {
                arr[k] = helper[j];
                j ++;
            }
            k ++;
        }

        // 左半段数组先“耗尽”
        if (i > mid) {
            // 把剩下的右半段拷贝过去
            System.arraycopy(helper, j, arr, k, right - j + 1);
        } else { // 右半段先“耗尽”
            // 把剩下的左半段拷贝过去
            System.arraycopy(helper, i, arr, k, mid - i + 1);
        }
    }

    public static void main(String[] args) {
        MergeSort2 mergeSort = new MergeSort2();
        int[] arr = new int[]{8,6,2,3,1,5,7,4};
        mergeSort.mergerSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
