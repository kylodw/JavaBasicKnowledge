package com.butterknife;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
public class ButterKnife {
    public static Unbinder bind(Activity activity) {
        try {
            Class<? extends Unbinder> activityClassName = (Class<? extends Unbinder>) Class.forName(activity.getClass().getName() + "_ViewBinding");
            //拿到构造函数
            Constructor<? extends Unbinder> declaredConstructor = activityClassName.getDeclaredConstructor(activity.getClass());
            declaredConstructor.setAccessible(true);
            //实例化对象
            return declaredConstructor.newInstance(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Unbinder.EMPTY;
    }


}
