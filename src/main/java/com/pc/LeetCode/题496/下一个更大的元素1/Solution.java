package com.pc.LeetCode.题496.下一个更大的元素1;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>496. 下一个更大元素 I</p>
 * <p>nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。</p>
 *
 * <p>给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。</p>
 *
 * <p>对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。</p>
 *
 * <p>返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。</p>
 *
 * <p>示例 1：</p>
 *
 * <p>输入：nums1 = [4,1,2], nums2 = [1,3,4,2].</p>
 * <p>输出：[-1,3,-1]</p>
 * <p>解释：nums1 中每个值的下一个更大元素如下所述：</li>
 * <li>4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。</li>
 * <li>1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。</li>
 * <li>2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。</li>
 * <p>示例 2：</p>
 *
 * <p>输入：nums1 = [2,4], nums2 = [1,2,3,4].</p>
 * <p>输出：[3,-1]</p>
 * <p>解释：nums1 中每个值的下一个更大元素如下所述：</p>
 * <li> 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。</li>
 * <li> 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。</li>
 *
 */
public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> map = leftRightMaxValue(nums2);

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 找数组中左边比自己大的且离自己最近的的元素 和找数组中右边比自己大的且离自己最近的元素
     * @param arr 假设arr中出现的都是正数,且不会出现重复的元素
     * @return 二维数组 result[i][0]，左边比自己大的离自己最近的元素，如果不存在返回-1 result[i][0]，右边比自己大的离自己最近的元素，如果不存在返回-1
     */
    public static Map<Integer, Integer> leftRightMaxValue(int arr[]) {
        // map[i][value] key = num[i], value=arr[i]右边比自己大的离自己最近大的元素
        Map<Integer, Integer> map = new HashMap<>();
        // 维护一个单调栈，栈底到栈顶，元素依次减小
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                Integer pop = stack.pop();
                map.put(arr[pop],  arr[i]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            // 右边找不到比自己大的
            map.put(arr[pop],  -1);
        }
        return map;
    }


}
