package com.pc.LeetCode.题5.最长回文子串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *@author Evan
 *@since 2020/9/7 14:39
 */
public class Solution {
	public static String longestPalindrome(String s) {
		// 正序
		List<Character> pos = new ArrayList();
		for (char po : s.toCharArray()) {
			pos.add(po);
		}

		List<Character> nav = new ArrayList<>(pos.size());
		char[] chars = s.toCharArray();
		for (int i = chars.length ; i > 0;) {
			nav.add(chars[--i]);
		}

		System.out.println(pos);
		System.out.println(nav);

		int start = 0;
		int maxLength = 0;
		int length = 0;
		for (int i =0;i<pos.size();) {
			if (pos.get(i) == nav.get(i)) {
				if (start == 0) {
					start = i;
				}
				i++;
				length ++;
			} else {
				if (length>maxLength) {
					maxLength = length;
					length = 0;
				} else {
					i++;
					start = i;
				}
			}
		}

		return s.substring(start,start+length);

	}

	public static void main(String[] args) {
		String s = "babad";

		String s1 = longestPalindrome(s);
		System.out.println(s1);
	}
}
