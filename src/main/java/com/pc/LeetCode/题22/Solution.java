package com.pc.LeetCode.题22;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //      错误的解题思路
//    public static List<String> generateParenthesis(int n) {
//        List<String> ans = new ArrayList<>();
//        ans.add("()");
//        for (int i = 1; i < n; i++) {
//            Set<String> set = new HashSet<>();
//            for (String an : ans) {
//                set.add("()" + an);
//                set.add(an + "()");
//                set.add("(" + an + ")");
//            }
//            ans.clear();
//            ans.addAll(set);
//        }
//        return ans;
//    }
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfs(ans, builder, n, n);
        return ans;
    }

    private static void dfs(List<String> ans, StringBuilder builder, int leftBrackets, int rightBrackets) {

        if (rightBrackets == 0) {
            ans.add(builder.toString());
            return;
        }

        // 池子里面的剩余左括号必须小于等于剩下的右括号
        if (leftBrackets == rightBrackets) {
            builder.append("(");
            dfs(ans, builder, leftBrackets - 1, rightBrackets);
            builder.deleteCharAt(builder.length() - 1);
        } else if (leftBrackets < rightBrackets) {
            if (leftBrackets > 0) {
                builder.append("(");
                dfs(ans, builder, leftBrackets - 1, rightBrackets);
                builder.deleteCharAt(builder.length() - 1);

                builder.append(")");
                dfs(ans, builder, leftBrackets, rightBrackets - 1);
                builder.deleteCharAt(builder.length() - 1);
            } else {
                builder.append(")");
                dfs(ans, builder, leftBrackets, rightBrackets - 1);
                builder.deleteCharAt(builder.length() - 1);
            }

        }

    }

    public static void main(String[] args) {
        List<String> ans = generateParenthesis(4);
        System.out.println(JSON.toJSON(ans));

        JSONArray array = JSON.parseArray("[\"(((())))\",\"((()()))\",\"((())())\",\"((()))()\",\"(()(()))\",\"(()()())\",\"(()())()\",\"(())(())\",\"(())()()\",\"()((()))\",\"()(()())\",\"()(())()\",\"()()(())\",\"()()()()\"]");
        for (int i = 0; i < array.size(); i++) {
            if (!ans.contains((String) array.get(i))) {
                System.out.println((String) array.get(i));
            }
        }

    }

}
