package com.pc.LeetCode.common;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value={TYPE})
public @interface GoodQuestions {

    GoodQuestion[] value();//对应3

}
