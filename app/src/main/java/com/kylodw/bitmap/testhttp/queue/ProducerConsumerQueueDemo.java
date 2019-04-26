package com.kylodw.bitmap.testhttp.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/26
 * 使用阻塞队列实现生产者消费者
 */
public class ProducerConsumerQueueDemo {
    public static void main(String[] args) {

    }

    static class SharedData {
        private volatile boolean flag = true;
        private AtomicInteger atomicInteger = new AtomicInteger();

        BlockingQueue<String> blockingQueue = null;

        public SharedData(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
            System.out.println(blockingQueue.getClass().getName());
        }

    }
}
