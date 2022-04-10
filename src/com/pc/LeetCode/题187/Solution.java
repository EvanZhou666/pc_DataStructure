package com.pc.LeetCode.题187;

import java.util.*;

/**
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * <p>
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * <p>
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    /**
     * 自己想的【滑动窗口】解法，代码不是很优雅。官方更加优雅。滑动窗口不一定需要"有形"的双指针，也不一定需要有形的"窗口"。
     * @param s
     * @return
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();

        if (s == null || s.length() < 10) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        int length = chars.length;

        Deque<Character> window = new ArrayDeque();

        // 初始化window
        for (int i = 0; i < 10; i++) {
            window.addLast(chars[i]);
        }

        addToMapIfExsits(map, window);

        // 窗口的右边界记作l
        for (int l = 10; l < length; l++) {
            window.pollFirst();
            window.addLast(chars[l]);
            addToMapIfExsits(map, window);
        }

        for (String s1 : map.keySet()) {
            if (map.get(s1) > 1) {
                result.add(s1);
            }
        }
        return result;
    }

    private static void addToMapIfExsits(Map<String, Integer> map, Deque<Character> window) {
        String windowStr = getStr(window);
        if (map.containsKey(windowStr)) {
            map.put(windowStr, map.get(windowStr) + 1);
        } else {
            map.put(windowStr, 1);
        }
    }

    public static String getStr(Deque<Character> deque) {
        StringBuilder builder = new StringBuilder();
        deque.forEach(character -> builder.append(character));
        return builder.toString();
    }

    public static void main(String[] args) {
        List<String> list = findRepeatedDnaSequences("AAAAAAAAAAA");
        System.out.println(list);
        list.clear();
        list = findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.println(list.toString());
    }

}
