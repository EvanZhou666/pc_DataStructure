package com.pc.LeetCode.题20.有效的括号;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static List<Character> left = new ArrayList<Character>();
    static List<Character> right = new ArrayList<Character>();

    static {
        left.add('(');
        left.add('[');
        left.add('{');

        right.add(')');
        right.add(']');
        right.add('}');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            if (isLeft(aChar)) {
                stack.push(aChar);
            } else {
                Character pop = null;
                try {
                    pop = stack.pop();
                } catch (EmptyStackException e) {

                }
                if (pop == null) {
                    return false;
                } else if (!pop.equals(leftMatches(aChar))) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isLeft(Character character) {
        return left.contains(character);
    }

    private static boolean isRight(Character character) {
        return right.contains(character);
    }

    private static Character leftMatches(Character character) {

        switch (character) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return null;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean valid = solution.isValid("()");
        System.out.println(valid);
    }

}
