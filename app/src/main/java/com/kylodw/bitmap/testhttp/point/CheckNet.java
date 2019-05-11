package com.kylodw.bitmap.testhttp.point;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
@Retention(RetentionPolicy.RUNTIME)
//class 不会检测错误   runtime 会检测错误
@Target(ElementType.METHOD)   //运行时和编译器什么意思？
//编译时期 就是点击之后开始生成class
public @interface CheckNet {
}
