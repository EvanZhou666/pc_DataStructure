package com.pc.八大排序算法;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/sort-an-array/
 * 给你一个数组arr，让你实现归并排序
 * 归并排序的一个优化点是数据规模不一定要拆分到2，当拆分的数据规模小到一定程度的时候，使用插入排序，就不要继续拆分了。
 * 本代码不做任何优化。
 */
public class MergeSort {


    public void mergerSort(int[] arr) {
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
        // 申请辅助数组
        int[] helper = new int[right - left + 1];

        // 将要排序的子数组复制到辅助数组内,因为我们函数签名没有返回值，所以只能修改arr数组
        for (int i = 0; i < helper.length; i++) {
            helper[i] = arr[left + i];
        }

        // arr[left,mid] --> 映射为 helper[left-left, mid-left] = help[0,mid-left]
        // 同理 arr[mid+1, right] -->helper[mid+1-left, right-left]

        int i = 0; // 左半边数组下一个要比较的地方
        int j = mid + 1 - left; // 右半边数组下一个要比较的地方
        int k = left; // 合并后的元素要存储的位置
        // 本质是对help[0,mid-left]和helper[mid+1-left, right-left]两个有序数组进行合并
        // 且两个数组长度可能不一样，且一定有一个先结束
        while (i <= mid -left && j <= right-left) {
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
        if (i > mid - left) {
            // 把剩下的右半段拷贝过去
            System.arraycopy(helper, j, arr, k, right - left - j + 1);
        } else { // 右半段先“耗尽”
            // 把剩下的左半段拷贝过去
            System.arraycopy(helper, i, arr, k, mid -left -i + 1);
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[]{8,6,2,3,1,5,7,4};
        mergeSort.mergerSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
