package com.pc.八大排序算法;

import com.google.common.base.Stopwatch;

import java.util.Random;

/**
 * 在待排序数组完全有序或者近乎完全有序的情况下，快速排序时间复杂度退化至O(N^2)的优化办法
 */
public class QuickSort2 {

    Random random = new Random();

    public void quickSort(int[] nums) {
        doQuickSort(nums, 0, nums.length - 1);
    }

    // 为什么要定义left 和right？ 因为我们在首次partition后，还需要分别递归快排partition的左区间和右区间
    // [left, right] 区间定义为左闭右闭
    private void doQuickSort(int[] nums, int left, int right) {

        // 只有1个元素，无需排序。
        if (left >= right) {
            return;
        }

        // 为什么partition需要右返回值，因为需要对partition的左右子区间分别进行递归快排。
        int p = partition(nums, left, right);

        // 递归快排左区间 ,区间边界行交给函数被调用者负责
        doQuickSort(nums, left, p - 1);
        // 快排右区间
        doQuickSort(nums, p + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
//        int rand = (int) (Math.random() * (right - left + 1)) + 1;
        // 这种算是在数组中随机一个元素的“奇迹淫巧了”，随机性比上面高出太多了 牛逼
        int rand = (random.nextInt(Integer.MAX_VALUE) % (right - left + 1)) + left;
        swap(nums, rand, left);
        // 继续以固定选择第1个元素作为标记点(数据结构的书里面右称做“枢纽”)
        int lFlag = left;
        // 定义比nums[lFlag]小的区间和比nums[lFlag]大的区间的分界点
        int j = lFlag;

        // cur 当前正在访问的元素
        for (int cur = left; cur <= right; cur++) {
            if (nums[cur] > nums[lFlag]) {
                // doNothing
            } else if (nums[cur] < nums[lFlag]) {
                swap(nums, cur, j + 1);
                j++;
            }
        }
        // 遍历结束，交换标记点("枢纽") 和“分区边界点”
        swap(nums, lFlag, j);
        // j值就是数组完全有序后，nums[lFlag]正确被放置的位置
        return j;
    }

    // 交换数组中的两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort2 quickSort = new QuickSort2();

        MergeSort2 mergeSort2 = new MergeSort2();
        int[] arr = SortHelper.generateRepeatedSequence(100000);

        Stopwatch stopwatch = Stopwatch.createStarted();
        mergeSort2.mergerSort(arr);
        System.out.println(stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        quickSort.quickSort(arr);
        System.out.println(stopwatch.stop());
    }
}
