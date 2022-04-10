package com.pc.LeetCode.common;

public class Assert {

    public static boolean assertIsTrue(boolean value) {
        if (!value) {
            throw new IllegalArgumentException("The value is not true");
        }
        return value;
    }

}
