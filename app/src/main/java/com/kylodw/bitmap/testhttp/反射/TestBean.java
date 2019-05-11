package com.kylodw.bitmap.testhttp.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
public class TestBean {
    private String name = "kylodw";

    private TestBean() {
    }

    private TestBean(String name) {
        this.name = name;
    }

    private void sysName() {
        System.out.println(name);
    }
}

class main {
    public static void main(String[] args) {
        try {
            //初始化无参的构造方法
//            TestBean testBean = TestBean.class.newInstance();
//            System.out.println(testBean.toString());
            //初始化有参数的构造方法
            Constructor<TestBean> constructor = TestBean.class.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            TestBean oneTestBean = constructor.newInstance("hehe");
            //获取方法
            Method method = TestBean.class.getDeclaredMethod("sysName");
            method.setAccessible(true);
            method.invoke(oneTestBean);


            Field field = TestBean.class.getDeclaredField("name");
            field.setAccessible(true);
            System.out.println("field:"+field.get(oneTestBean));

            //
//            Class clazz = Class.forName("com.kylodw.bitmap.testhttp.反射.TestBean");
//            System.out.println(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
