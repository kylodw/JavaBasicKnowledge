package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 */
public class FairLock {
    public static void main(String[] args) throws InterruptedException {
        /*
         * 可重入锁
         *
         * true 为公平   类似于队列的先来后到,按申请锁的顺序来（等待队列）
         * false 为不公平  类似于加塞  ，不按照申请锁的顺序 ，
         * 有可能会造成优先级反转或者饥饿现象（如果失败，就再采用公平锁的方式）
         *
         * 默认为false不公平
         * 非公平的优点在于吞吐量比公平锁大
         * synchronized是非公平锁
         */
        Lock lock = new ReentrantLock(true);

        /*
         * 理论 代码  小总结
         * 可重入锁（递归锁）  ReentrantLock   synchronized非公平的可重入锁
         * 最大作用可以避免死锁
         * 线程可以进入任何一个他已经拥有的锁所同步着的代码块
         */
        Phone phone = new Phone();
        new Thread(phone::sendSMS, "thread_1").start();
        new Thread(phone::sendSMS, "thread_2").start();
        Thread.sleep(2000);
        System.out.println("=====================================");
        new Thread(phone).start();
        new Thread(phone).start();
    }

    static class Phone implements Runnable {
        public synchronized void sendSMS() {
            System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
            sendEmail();
        }

        public synchronized void sendEmail() {
            System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail()");
        }

        @Override
        public void run() {
            get();
        }

        Lock lock = new ReentrantLock();
        //加几次锁 解几次  不解 编译通过但是会卡死
        private void get() {
            lock.lock();
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getId()+"invoke get");
                set();
            } catch (Exception e) {
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }

        private void set() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getId()+"invoke set");
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
    }
}
