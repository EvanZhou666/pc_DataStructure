package com.pc.LeetCode.题190.颠倒二进制位;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author Evan
 */
public class Solution {

    public static int reverseBits(int n) {

        int h1 = n & 0xFF000000;
        int h2 = n & 0x00FF0000;
        int h3 = n & 0x0000FF00;
        int h4 = n & 0x000000FF;

        h1 = h1 >>> 24;
        h2 = h2 >>> 16;
        h3 = h3 >>> 8;

        // 对每一块逐位反转
        int b1 = 0b00000001;
        int b2 = 0b00000010;
        int b3 = 0b00000100;
        int b4 = 0b00001000;
        int b5 = 0b00010000;
        int b6 = 0b00100000;
        int b7 = 0b01000000;
        int b8 = 0b10000000;

        h1 = ((h1 & b1) << 7) |
                ((h1 & b2) << 5) |
                ((h1 & b3) << 3) |
                ((h1 & b4) << 1) |
                ((h1 & b5) >>> 1) |
                ((h1 & b6) >>> 3) |
                ((h1 & b7) >>> 5) |
                ((h1 & b8) >>> 7);

        h2 = ((h2 & b1) << 7) |
                ((h2 & b2) << 5) |
                ((h2 & b3) << 3) |
                ((h2 & b4) << 1) |
                ((h2 & b5) >>> 1) |
                ((h2 & b6) >>> 3) |
                ((h2 & b7) >>> 5) |
                ((h2 & b8) >>> 7);

        h3 = ((h3 & b1) << 7) |
                ((h3 & b2) << 5) |
                ((h3 & b3) << 3) |
                ((h3 & b4) << 1) |
                ((h3 & b5) >>> 1) |
                ((h3 & b6) >>> 3) |
                ((h3 & b7) >>> 5) |
                ((h3 & b8) >>> 7);

        h4 = ((h4 & b1) << 7) |
                ((h4 & b2) << 5) |
                ((h4 & b3) << 3) |
                ((h4 & b4) << 1) |
                ((h4 & b5) >>> 1) |
                ((h4 & b6) >>> 3) |
                ((h4 & b7) >>> 5) |
                ((h4 & b8) >>> 7);


        int reverse = (h4 << 24) | (h3 << 16) | (h2 << 8) | h1;

        System.out.println("==="+intToBinary(reverse));
        return reverse;
    }


    /**
     * 整数转二进制,前面补零
     */
    public static String intToBinary(int num) {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++) {
            chs[Integer.SIZE - 1 - i] = (char) ((num >> i & 1) + '0');
        }
        return new String(chs);
    }


    public static void main(String[] args) {
        int b1 = 0b00000001;
        byte b2 = (byte) 0x80;
        System.out.println(intToBinary(b2) +b2);
        System.out.println(Integer.toBinaryString(b2) +b2);
        System.out.println(intToBinary(b1|b2));
        reverseBits(0b00000010100101000001111010011100);
//        reverseBits(0b00000000000000000000000000001111);

    }
}
