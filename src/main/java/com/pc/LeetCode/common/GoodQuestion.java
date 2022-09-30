package com.pc.LeetCode.common;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value={TYPE})
@Repeatable( GoodQuestions.class )
public @interface GoodQuestion {
    // 类型
    String type() default "";
}
