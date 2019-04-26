package com.kylodw.bitmap.testhttp.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * i++  使用原子类  不使用synchronized
 * CAS  自旋锁  Unsafe(do while)
 * 缺点： 循环时间长，CPU开销大
 * 2，只能保证一个变量的共享操作（加锁可以保证块状）
 * 3，可能引发ABA问题
 *
 * ABA-->原子引用更新--->如何规避原子更新
 *
 */
public class CASDemo {
    public static void main(String[] args) {
        CASTest();
    }

    private static void CASTest() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //比较和交换
        //期望值和修改值
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t 线程名字：" + Thread.currentThread().getName() + "\t " +
                "值：" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t 线程名字：" + Thread.currentThread().getName() + "\t " +
                "值：" + atomicInteger.get());
    }
//    private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
//    private static final long VALUE;
//
//    static {
//        try {
//            VALUE = U.objectFieldOffset
//                    (AtomicInteger.class.getDeclaredField("value"));
//        } catch (ReflectiveOperationException e) {
//            throw new Error(e);
//        }
//    }
//
//    private volatile int value;
}
