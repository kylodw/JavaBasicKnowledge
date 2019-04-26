package com.kylodw.bitmap.testhttp.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 细粒度的锁处理
 * 条件变量  通知唤醒
 *
 */
public class SyncLockDemo {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedData.print5();
            }
        }, "thread-1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedData.print10();
            }
        }, "thread-2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedData.print15();
            }
        }, "thread-3").start();
    }

    static class SharedData {
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition condition3 = lock.newCondition();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();

        //1 判断
        public void print5() {
            lock.lock();
            try {
                while (number != 1) {
                    condition1.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                number = 2;//修改标志位
                condition2.signal();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

        public void print10() {
            lock.lock();
            try {
                while (number != 2) {
                    condition2.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                number = 3;//修改标志位
                condition3.signal();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

        public void print15() {
            lock.lock();
            try {
                while (number != 3) {
                    condition3.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t " + i);
                }
                number = 1;//修改标志位
                condition1.signal();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
        //2 工作

        //3 唤醒
    }
}
