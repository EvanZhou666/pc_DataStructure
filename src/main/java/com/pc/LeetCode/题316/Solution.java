package com.pc.LeetCode.题316;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * todo 待重做解法是错误的
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] set = new int[26];
        int[] index = new int[26];
        List<Character> ans = new ArrayList<>(s.length());
        dfs(s, s.length() - 1, ans, set, index);
        StringBuilder builder = new StringBuilder();
        for (Character character : ans) {
            if (character != '#') {
                builder.append(character);
            }
        }
        return builder.toString();
    }

    private void dfs(String s, int i, List<Character> ans, int[] set, int[] index) {
        if (i < 0) {
            return;
        }

        if (set[s.charAt(i) - 'a'] == 0) {
            ans.add(0, s.charAt(i));
            set[s.charAt(i) - 'a'] = 1;
            index[s.charAt(i) - 'a'] = ans.size();
        } else {
            if (s.charAt(i) < ans.get(0)) {
                int reverseIndex = index[s.charAt(i) - 'a'];
                ans.set(ans.size() - reverseIndex, '#');
                ans.add(0, s.charAt(i));
                index[s.charAt(i) - 'a'] = ans.size();
            }
        }

        System.out.println(ans);
        dfs(s, i - 1, ans, set, index);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans ;
//        ans = solution.removeDuplicateLetters("cdadabcc");
        ans = solution.removeDuplicateLetters("abacb");
        ans = solution.removeDuplicateLetters(new StringBuilder("abacb").reverse().toString());
//        ans = solution.removeDuplicateLetters("cbacdcbc");
        System.out.println(ans);
    }

}
