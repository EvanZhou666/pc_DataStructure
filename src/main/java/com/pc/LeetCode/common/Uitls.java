package com.pc.LeetCode.common;

import com.alibaba.fastjson.JSONArray;

import java.util.Arrays;
import java.util.List;

public class Uitls {

    /**
     * 打印数组以及双指针，方便调试用
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void printArray(char[] nums, int l, int r) {
        if (r >= nums.length) {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=nil");
        } else {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=" + r);
        }
    }

    public static void printArray(int[] nums, int l, int r) {
        if (r >= nums.length) {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=nil");
        } else {
            System.out.println(Arrays.toString(nums) + " l=" + l + " r=" + r);
        }
    }

    public static char[][] convert(String inputString) {
        Character[] CC = new Character[0];
        List<? extends Character[]> jsonArray = JSONArray.parseArray(inputString, CC.getClass());

        char[][] input = new char[jsonArray.size()][jsonArray.get(0).length];

        for (int i = 0; i < jsonArray.size(); i++) {
            for (int j = 0; j < jsonArray.get(i).length; j++) {
                Character[] character = jsonArray.get(i);
                input[i][j] = character[j];
            }

        }
        return input;
    }

    public static int[][] convertToInts(String inputString) {
        Integer[] CC = new Integer[0];
        List<? extends Integer[]> jsonArray = JSONArray.parseArray(inputString, CC.getClass());

        int[][] input = new int[jsonArray.size()][jsonArray.get(0).length];

        for (int i = 0; i < jsonArray.size(); i++) {
            for (int j = 0; j < jsonArray.get(i).length; j++) {
                Integer[] character = jsonArray.get(i);
                input[i][j] = character[j];
            }

        }
        return input;
    }

    public static void printBoard(char[][] board, String message) {
        System.out.println("==========="+message+"========");
        for (int i = 0; i < board.length; i++) {
            if (i < 10) {
                System.out.println(" " + i + ":" + Arrays.toString(board[i]));
            } else {
                System.out.println(i + ":" + Arrays.toString(board[i]));
            }
        }
    }

}
