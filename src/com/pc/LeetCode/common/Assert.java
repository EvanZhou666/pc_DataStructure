package com.pc.LeetCode.common;

public class Assert {

    public static boolean assertIsTrue(boolean value) {
        if (!value) {
            throw new IllegalArgumentException("The value is not true");
        }
        return value;
    }

    public static boolean assertIsFalse(boolean value) {
        if (value) {
            throw new IllegalArgumentException("The value is not true");
        }
        return value;
    }

    public static boolean assertEquals(Integer realValue, Integer excepted) {
        if (realValue != excepted) {
            throw new IllegalArgumentException("The value is not Equals.excepted is "+ excepted +" but realvalue is "+realValue);
        }
        return true;
    }

    public static boolean assertEquals(double realValue, double excepted) {
        if (realValue != excepted) {
            throw new IllegalArgumentException("The value is not Equals.excepted is "+ excepted +" but realvalue is "+realValue);
        }
        return true;
    }

}
