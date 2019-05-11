package com.kylodw.bitmap.testhttp.customenum;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/29
 */
public class Test {
    public static void main(String[] args) {
        List<String> stringTest = new ArrayList<>();
        stringTest.add("kylodw");

        Type type = ((ParameterizedType) stringTest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);

        ExtendDemo extendDemo= new ExtendDemo();
        Type type2 = ((ParameterizedType) extendDemo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type2);

    }

    private static class ReferDemo<T> {

    }

    private static class ExtendDemo extends ReferDemo<String> {
        public void method(){

        }
    }
}
