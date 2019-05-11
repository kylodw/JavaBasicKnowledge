package com.butterknife.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface KBindView {
    int value();
}
