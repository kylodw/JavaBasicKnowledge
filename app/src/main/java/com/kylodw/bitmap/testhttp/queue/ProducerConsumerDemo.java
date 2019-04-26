package com.kylodw.bitmap.testhttp.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 传统版的消费者生产者模式
 * wait
 * notify
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread-2").start();
    }

    /**
     * 多线程判断用while防止虚假唤醒
     * synchronized 和lock的区别：
     * 1，jvm层面 关键字 底层monitor对象     两个exit 正常退出和异常退出
     * lock：新的java类 api层面的锁
     * 2，synchronized 不需要手动释放锁
     * lock：需要配对lock(),unlock()释放锁
     * 3，synchronized  不可中断，除非抛异常或者正常完成
     * Lock： 可中断 （1，设置超时方法2，调用中断方法中断）
     * 4，synchronized 默认非公平锁
     * Lock 默认非公平，但是可以通过变量设置
     * 5，绑定多个Condition
     * synchronized  没有
     * Lock 可以分组唤醒
     */
    static class ShareData {

        private int number = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void decrement() throws Exception {
            lock.lock();
            try {
                while (number == 0) {
                    //等待，不能生产
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName() + " \t " + number);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void increment() throws Exception {
            lock.lock();
            try {
                while (number != 0) {
                    //等待，不能生产
                    condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName() + " \t " + number);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
