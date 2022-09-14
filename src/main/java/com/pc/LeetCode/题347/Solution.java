package com.pc.LeetCode.题347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/">347. 前 K 个高频元素</a>
 */
public class Solution {

    Random random = new Random();

    // 前k个高频，数组得逆序排列
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计数字频次
        Map<Integer, Integer> frequence = new HashMap<>();
        for (int num : nums) {
            frequence.put(num, frequence.getOrDefault(num, 0) + 1);
        }

        int[][] numFrequence = new int[frequence.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : frequence.entrySet()) {
            numFrequence[i] = new int[]{entry.getKey(), entry.getValue()};
            i++;
        }

        // 2. 借鉴快速排序的思想，快排过程中每次递归会确定一个标记点，标记点左边的元素都小于，右边的元素都大于它。
        // 如果[left,标记点]区间的元素个数，刚好等于K，那么就刚好找到了答案。
        // 如果[left,标记点]区间的元素个数，大于K，那么递归这个区间，找刚好能让元素个数等于K的新的标记点。
        // 如果[left,标记点]区间的元素个数，小于K，说明左边区域不够K，那还有找右边区域，还需要再找X个元素（X=K-标记点区间长度），那么递归[标记点+1， right]这个区间，找刚好能让元素个数等于X的新的标记点。
        return quickSort(numFrequence, k);

    }

    private int[] quickSort(int[][] numFrequence, int k) {
        return doQuickSort(numFrequence, 0, numFrequence.length - 1, k);
    }


    private int[] doQuickSort(int[][] numFrequence, int left, int right, int k) {

        if (left >= right) {
            return new int[]{numFrequence[left][0]};
        }

        int[] p = partition2(numFrequence, left, right);


        int p1 = p[0]; //[left,lt] 大于区间
        int p2 = p[1]; // [gt,right] 小于区间
        // 等于区间 [lt + 1, gt - 1]

        // 这一段边界判断最是恶心，如果想当然就很容易出错，最好拿数据演练下
        if (p2 - left == k) {
            return getResultTopK(numFrequence, left, k);
        } else if (p2 - left > k) {
            int[] leftRet = doQuickSort(numFrequence, left, p1, k);
            return leftRet;
        } else {
            int requiredMore = k - (p2 - left);
            int[] leftRet = getResultTopK(numFrequence, left, p2 - left);
            int[] rightRet = doQuickSort(numFrequence, p2, right, requiredMore);
            return mergeLeftAndRightRet(leftRet, rightRet);
        }

    }

    /**
     * <p>3路快排</p>
     * <img src="partition过程.png">分区具体情况见图解</img>
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int[] partition2(int[][] nums, int left, int right) {

        int rand = random.nextInt(Integer.MAX_VALUE) % (right - left) + left;
        swap(nums, rand, left);
        // 继续以固定选择第1个元素作为标记点(数据结构的书里面右称做“枢纽”)
        int lFlag = left;
        // 定义比nums[left+1 ... j]的区间小于nums[lFlag]
        int j = lFlag;
        // 定于nums[k...right]的区间大于nums[lFlag]
        int k = right + 1;

        // cur 当前正在访问的元素, 遇到partition右边界则终止
        for (int cur = left; cur < k; cur++) {
            if (nums[cur][1] == nums[lFlag][1]) {
                // doNothing
            } else if (nums[cur][1] > nums[lFlag][1]) {
                swap(nums, cur, j + 1);
                j++;
            } else {
                k--;
                swap(nums, cur, k);
                // 因为交换后的nums[cur]大小不确定，所以为了不让cur在循环时候自动加1，这里先减1
                --cur;
            }
        }
        // 遍历结束，交换标记点("枢纽") 和“分区边界点”
        swap(nums, lFlag, j);
        // j值就是数组完全有序后，nums[lFlag]正确被放置的位置
        return new int[]{j - 1, k};
    }


    private void swap(int[][] numFrequence, int i, int j) {
        int[] temp = new int[2];
        temp[0] = numFrequence[i][0];
        temp[1] = numFrequence[i][1];

        numFrequence[i][0] = numFrequence[j][0];
        numFrequence[i][1] = numFrequence[j][1];
        numFrequence[j][0] = temp[0];
        numFrequence[j][1] = temp[1];
    }

    private int[] mergeLeftAndRightRet(int[] leftRet, int[] rightRet) {
        int[] mergedRet = new int[leftRet.length + rightRet.length];
        System.arraycopy(leftRet, 0, mergedRet, 0, leftRet.length);
        System.arraycopy(rightRet, 0, mergedRet, leftRet.length, rightRet.length);
        return mergedRet;
    }

    private int[] getResultTopK(int[][] numFrequence, int offset, int k) {
        int[] result = new int[k];
        for (int i = offset, count = 0; count < k; i++, count++) {
            result[i - offset] = numFrequence[i][0];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans;

        ans = solution.topKFrequent(new int[]{
                5, 1, -1, -8, -7, 8, -5, 0, 1, 10, 8, 0, -4, 3, -1, -1, 4, -5, 4, -3, 0, 2, 2, 2, 4, -2, -4, 8, -7, -7, 2, -8, 0, -8, 10, 8, -8, -2, -9, 4, -7, 6, 6, -1, 4, 2, 8, -3, 5, -9, -3, 6, -8, -5, 5, 10, 2, -5, -1, -5, 1, -3, 7, 0, 8, -2, -3, -1, -5, 4, 7, -9, 0, 2, 10, 4, 4, -4, -1, -1, 6, -8, -9, -1, 9, -9, 3, 5, 1, 6, -1, -2, 4, 2, 4, -6, 4, 4, 5, -5
        }, 7);
        System.out.println(Arrays.toString(ans));

        ans = solution.topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));

        ans = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));
        ans = solution.topKFrequent(new int[]{1}, 1);

        ans = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 1, 2, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));
    }
}
