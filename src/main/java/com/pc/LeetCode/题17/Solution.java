package com.pc.LeetCode.题17;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。<br/>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/11/09/200px-telephone-keypad2svg.png"/>
 */
public class Solution {

    private String digits;

    /**
     * 二分搜索 + 记忆化
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        if ("".equals(digits)) {
            return new ArrayList<>(0);
        }
        this.digits = digits;
        Map<String, List<String>> memo = new HashMap<>();
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("");
        memo.put("1", l1);
        memo.put("2", Arrays.asList("a", "b","c"));
        memo.put("3", Arrays.asList("d", "e","f"));
        memo.put("4", Arrays.asList("g", "h", "i"));
        memo.put("5", Arrays.asList("j", "k", "l"));
        memo.put("6", Arrays.asList("m", "n", "o"));
        memo.put("7", Arrays.asList("p", "q", "r", "s"));
        memo.put("8", Arrays.asList("t", "u", "v"));
        memo.put("9", Arrays.asList("w", "x", "y", "z"));

        List<String> ans = doLetterCombinations(0, digits.length() - 1, memo);
        return ans;
    }

    private List<String> doLetterCombinations(int left, int right, Map<String, List<String>> memo) {

        if (left == right) {
            return memo.get(String.valueOf(digits.charAt(left)));
        }

        String key = digits.substring(left, right + 1);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }


        int mid = left + (right - left) / 2;

        List<String> leftRes = doLetterCombinations(left, mid, memo);
        List<String> rightRes = doLetterCombinations(mid + 1, right, memo);

        List<String> res = union(leftRes, rightRes);
        memo.put(key, res);
        return res;
    }

    private List<String> union(List<String> leftRes, List<String> rightRes) {
        List<String> ans = new ArrayList<>(leftRes.size() * rightRes.size());
        for (int i = 0; i < leftRes.size(); i++) {
            for (int j = 0; j < rightRes.size(); j++) {
                ans.add(leftRes.get(i) + rightRes.get(j));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> ans;
        ans = solution.letterCombinations("23");
        System.out.println(JSON.toJSONString(ans));
    }

}
