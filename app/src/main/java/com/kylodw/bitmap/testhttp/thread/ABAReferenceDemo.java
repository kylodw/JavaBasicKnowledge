package com.kylodw.bitmap.testhttp.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * ABA------>原子引用------>如何规避
 * <p>
 * 解决ABA：原子引用为什么能解决ABA问题？
 * 时间戳原子引用
 * 原子类只能有一个对象但是可以通过AtomicReference 来包装一个多个变量的，或者可以直接加锁
 */
public class ABAReferenceDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("=======================ABA问题的产生===============================");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            System.out.println("我改变了一次：" + atomicReference.get());
            //中间被改变了一次
            atomicReference.compareAndSet(101, 100);
        }, "thread_1").start();
        new Thread(() -> {
            sleep(1000);
            atomicReference.compareAndSet(100, 2019);
            System.out.println(atomicReference.get());
        }, "thread_2").start();
        sleep(2000);
        System.out.println("==============解决ABA====================");
        new Thread(() -> {
            int version = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "  当前的版本号：" + version);
            sleep(1000);
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "  修改版本号：" + stampedReference.getStamp());
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "  修改版本号：" + stampedReference.getStamp());

        }, "thread_3").start();
        new Thread(() -> {
            int version = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "  当前的版本号：" + version);
            sleep(3000);
            boolean result = stampedReference.compareAndSet(100, 2019, stampedReference.getStamp(), stampedReference.getStamp()+ 1);
            System.out.println(Thread.currentThread().getName() + "     result:" + result + "  实际版本号：" + stampedReference.getStamp());
            System.out.println("最终的值：" + stampedReference.getReference());
        }, "thread_4").start();
//
//        //原子引用包装类
//        AtomicReference<User> userAtomicReference = new AtomicReference<User>();
//        User user = new User("kylodw", 23);
//        User user2 = new User("yucong", 25);
//
//        userAtomicReference.set(user);
//
//        System.out.println(userAtomicReference.compareAndSet(user, user2));
//        System.out.println(userAtomicReference.get().toString());
//
//        System.out.println(userAtomicReference.compareAndSet(user, user2));
//        System.out.println(userAtomicReference.get().toString());
//
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class User {
        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        String userName;
        int age;

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
