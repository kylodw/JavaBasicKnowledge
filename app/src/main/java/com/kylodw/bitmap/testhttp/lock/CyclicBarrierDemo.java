package com.kylodw.bitmap.testhttp.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 和CountDownLatch 相反  做加法
 * 屏障
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("提交订单");
        });
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第几个" + finalI);
                    try {
                        cyclicBarrier.await();//阻塞等待
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
