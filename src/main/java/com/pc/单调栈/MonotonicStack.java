package com.pc.单调栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>单调栈</p>
 * <ul>应用场景:
 *  <li>找数组中左边比自己小的且离自己最近的的元素和找数组中右边比自己小的且离自己最近的元素</li>
 *  <li>找数组中左边比自己大的且离自己最近的的元素和找数组中右边比自己大的且离自己最近的元素</li>
 * </ul>
 *
 *
 *
 */
public class MonotonicStack {

    /**
     * 找数组中左边比自己小的且离自己最近的的元素 和找数组中右边比自己小的且离自己最近的元素
     * @param arr 假设arr中出现的都是正数,且不会出现重复的元素
     * @return 二维数组 result[i][0]，左边比自己小的离自己最近的元素，如果不存在返回-1 result[i][0]，右边比自己小的离自己最近的元素，如果不存在返回-1
     */
    public static int[][] leftRightMinValue(int arr[]) {
        int[][] lrMin = new int[arr.length][2];
        // 维护一个单调栈，栈底到栈顶，元素依次增大
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
               Integer pop = stack.pop();
               lrMin[pop][1] = arr[i];
               lrMin[pop][0] = stack.isEmpty()? -1: arr[stack.peek()];
            }
            stack.push(i);
        }

        // 如果最后的栈非空，还要处理下剩余栈
        // 最后留在栈里的都是在右边找不到离自己最近的比自己小的元素
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            // 右边找不到比自己小的
            lrMin[pop][1] = -1;
            lrMin[pop][0] = stack.isEmpty()? -1: arr[stack.peek()];
        }
        return lrMin;
    }


    public static int[][] leftRightMaxValue(int arr[]) {
        int[][] lrMin = new int[arr.length][2];
        // 维护一个单调栈，栈底到栈顶，元素依次减小
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                Integer pop = stack.pop();
                lrMin[pop][1] = arr[i];
                lrMin[pop][0] = stack.isEmpty()? -1: arr[stack.peek()];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            // 右边找不到比自己大的
            lrMin[pop][1] = -1;
            lrMin[pop][0] = stack.isEmpty()? -1: arr[stack.peek()];
        }
        return lrMin;
    }


    /**
     * 找数组中左边比自己小的且离自己最近的的元素 和找数组中右边比自己小的且离自己最近的元素 数组元素可以重复
     * @param arr
     * @return
     */
    public static int[][] leftRightMinValueDuplicated(int arr[]) {
        int[][] lrMin = new int[arr.length][2];
        // 维护一个单调栈，栈底到栈顶，元素依次增大
        Deque<LinkedList<Integer>> stack = new LinkedList<>();


        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                LinkedList<Integer> item = new LinkedList<>();
                item.add(i);
                stack.push(item);
                continue;
            }

            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                LinkedList<Integer> pop = stack.pop();
                for (int j = 0; j < pop.size(); j++) {
                    lrMin[pop.get(j)][1] = arr[i];
                    lrMin[pop.get(j)][0] = stack.isEmpty()? -1: arr[stack.peek().getLast()];
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().getLast()] == arr[i]) {
                stack.peek().addLast(i);
            } else {
                LinkedList<Integer> item = new LinkedList<>();
                item.add(i);
                stack.push(item);
            }
        }

        // 如果最后的栈非空，还要处理下剩余栈
        // 最后留在栈里的都是在右边找不到离自己最近的比自己小的元素
        while (!stack.isEmpty()) {
            LinkedList<Integer> pop = stack.pop();
            // 右边找不到比自己小的
            for (int j = 0; j < pop.size(); j++) {
                lrMin[pop.get(j)][1] = -1;
                lrMin[pop.get(j)][0] = stack.isEmpty()? -1: arr[stack.peek().getLast()];
            }
        }
        return lrMin;
    }

    public static void main(String[] args) {
//        testLeftRightMinValue(new int[]{4, 3, 2, 1});
//        testLeftRightMaxValue(new int[]{4, 3, 5, 2, 1});
//        testLeftRightMinValueDuplicated(new int[]{4, 3, 2, 5, 4, 2, 1});
        isMonotonic(new int[]{1,3,2});
    }


    private static void testLeftRightMinValue(int arr[]) {
        System.out.println(Arrays.toString(arr));
        int[][] lrmin = leftRightMinValue(arr);
        for (int i = 0; i < lrmin.length; i++) {
            System.out.println(Arrays.toString(lrmin[i]));
        }
    }

    private static void testLeftRightMaxValue(int arr[]) {
        System.out.println(Arrays.toString(arr));
        int[][] lrmin = leftRightMaxValue(arr);
        for (int i = 0; i < lrmin.length; i++) {
            System.out.println(Arrays.toString(lrmin[i]));
        }
    }

    private static void testLeftRightMinValueDuplicated(int arr[]) {
        System.out.println(Arrays.toString(arr));
        int[][] lrmin = leftRightMinValueDuplicated(arr);
        for (int i = 0; i < lrmin.length; i++) {
            System.out.println(Arrays.toString(lrmin[i]));
        }
    }

    public static boolean isMonotonic(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (nums[i] >= nums[stack.peek()]) {
                    stack.push(i);
                }
            }
        }

        if (stack.size() == nums.length) {
            return true;
        } else {
            stack.clear();
            for (int i = 0; i < nums.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (nums[i] <= nums[stack.peek()]) {
                        stack.push(i);
                    }
                }
            }
            return stack.size() == nums.length;
        }

    }

}
