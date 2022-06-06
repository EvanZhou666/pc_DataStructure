package com.pc.LeetCode.题12;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 12. 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 1 <= num <= 3999
 */
public class Solution {

    private static Map<Integer, String> keyMap = new LinkedHashMap<>();

    static {
        keyMap.put(1000, "M");
        keyMap.put(900, "CM");
        keyMap.put(500, "D");
        keyMap.put(400, "CD");
        keyMap.put(100, "C");
        keyMap.put(90, "XC");
        keyMap.put(50, "L");
        keyMap.put(40, "XL");
        keyMap.put(10, "X");
        keyMap.put(9, "IX");
        keyMap.put(5, "V");
        keyMap.put(4, "IV");
        keyMap.put(1, "I");
    }


    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            int count = 0;
            for (Integer key : keyMap.keySet()) {
                if ((count = num / key) == 0) {
                    continue;
                } else {
                    num = num % key;
                    for (int i = 0; i < count; i++) {
                        res.append(keyMap.get(key));
                    }
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = "";
        res = solution.intToRoman(5);
        Assert.assertEquals(res, "V");

        res = solution.intToRoman(58);
        Assert.assertEquals(res, "LVIII");

        res = solution.intToRoman(1994);
        Assert.assertEquals(res, "MCMXCIV");

    }

}
