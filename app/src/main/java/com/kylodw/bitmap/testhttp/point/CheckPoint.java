package com.kylodw.bitmap.testhttp.point;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 * 处理切点
 */
@Aspect
public class CheckPoint {

    /**
     * 找到需要处理的切点
     * 标记
     * * *(..)代表处理所有的方法
     */
    @Pointcut("execution(@com.kylodw.bitmap.testhttp.point.CheckNet * *(..))")
    public void checkNetBehavior() {
    }

    /**
     * 处理切面
     *
     * @param joinPoint
     * @return
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(this.getClass().getSimpleName(), "进入point");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = methodSignature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null) {
            Object object = joinPoint.getThis(); //获取context
            Context context = getContext(object);
            if (context != null) {
                if (!isNetworkAvailable(context)) {
                    Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;

            return view.getContext();
        }
        return null;
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
