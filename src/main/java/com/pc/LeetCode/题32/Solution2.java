package com.pc.LeetCode.题32;

import com.pc.LeetCode.common.Assert;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2 {

    public static int longestValidParentheses(String s) {
        // 栈存储的是元素的下标
        // 如果是左括号，直接入栈。
        // 如果是右括号，且栈顶是左括号，则将栈顶元素出栈，更新最大有效括号长度。最大长度等于当前元素的下标减去【栈顶最后一个右括号的下标】。需要处理边界：【栈顶最后一个右括号的下标】可能不存在
        // 如果是右括号，且栈顶元素是右括号，则将栈顶元素出栈，如果出站后栈顶还是右括号，则不断将栈顶出栈。将当前右括号入栈。
        // 如果是右括号，且栈空，直接将当前右括号入栈。

        // 右括号入栈的含义：当前字符串不能连续有效就是因为这个搅屎棍右括号，因此这个位置是一个分割点。

        Deque<Integer> stack = new ArrayDeque<>();
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else if (s.charAt(stack.peek()) == '(') {
                    stack.pop();
                    int lastRightKuoHao = stack.isEmpty() ? -1 : stack.peek();
                    maxLength = Math.max(maxLength, i - lastRightKuoHao);
                } else {
                    while (!stack.isEmpty() && s.charAt(stack.peek()) == ')') {
                        stack.pop();
                    }
                    stack.push(i);
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int ans;
        ans = longestValidParentheses(")()())");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("(()()");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("((()()");
        Assert.assertEquals(4, ans);

        ans = longestValidParentheses("()(())");
        Assert.assertEquals(6, ans);

        ans = longestValidParentheses("()((())");
        Assert.assertEquals(4, ans);
    }
}
