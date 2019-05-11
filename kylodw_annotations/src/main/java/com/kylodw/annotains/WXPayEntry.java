package com.kylodw.annotains;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/11
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface WXPayEntry {
    /**
     * 包名
     *
     * @return
     */
    String packageName();

    /**
     * 类的Class
     *
     * @return
     */
    Class<?> entryCass();
}
