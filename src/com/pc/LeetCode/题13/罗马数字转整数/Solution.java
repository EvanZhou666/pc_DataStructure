package com.pc.LeetCode.题13.罗马数字转整数;


/**
 *
 *@author EvanZhou
 *@since 2020/9/2
 */
public class Solution {

	public static int romanToInt(String s) {

		char[] characters = s.toCharArray();
		int sum = 0;
		for (int i = 0; i < characters.length;) {

			switch (characters[i]) {
			case 'I': {
				if (i + 1 < characters.length && characters[i + 1] == 'V') {
					sum += 4;
					i++;
				} else if (i + 1 < characters.length && characters[i + 1] == 'X') {
					sum += 9;
					i++;
				} else {
					sum += 1;
				}
				//				i++;
				break;
			}
			case 'X': {
				if (i + 1 < characters.length && characters[i + 1] == 'L') {
					sum += 40;
					i++;
				} else if (i + 1 < characters.length && characters[i + 1] == 'C') {
					sum += 90;
					i++;
				} else {
					sum += 10;
				}
				//				i++;
				break;
			}
			case 'C': {
				if (i + 1 < characters.length && characters[i + 1] == 'D') {
					sum += 400;
					i++;
				} else if (i + 1 < characters.length && characters[i + 1] == 'M') {
					sum += 900;
					i++;
				} else {
					sum += 100;
				}
				break;
			}
			case 'V': {
				sum += 5;
				break;
			}

			case 'L': {
				sum += 50;
				break;
			}
			case 'D': {
				sum += 500;
				break;
			}
			case 'M': {
				sum += 1000;
				break;
			}

			}
			i++;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(romanToInt("III"));
		System.out.println(romanToInt("IV"));
		System.out.println(romanToInt("IX"));
		System.out.println(romanToInt("LVIII"));
		System.out.println(romanToInt("MCMXCIV"));
	}
}
