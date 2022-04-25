package com.pc.LeetCode.题9;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        char[] array = str.toCharArray();
        int l =0;
        int r =array.length-1;
        while (l < r) {
            if (array[l] != array[r]) {
                return false;
            }
            l ++;
            r --;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome(112233);
        System.out.println(palindrome);
    }

}
