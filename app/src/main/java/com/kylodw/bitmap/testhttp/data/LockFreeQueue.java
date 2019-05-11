package com.kylodw.bitmap.testhttp.data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/30
 */
public class LockFreeQueue<E> {

    private AtomicReference<Node<E>> head, tail;

    private AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        Node<E> node = new Node<>(null);
        //都指向node
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public boolean isEmpty() {
        return size.get() == 0;
    }

    public void addLast(E e) {
        if (e == null) throw new NullPointerException("e not null");
        Node<E> newNode = new Node<>(e);
        //
        Node<E> prev = tail.getAndSet(newNode);
        prev.next = newNode;
        size.incrementAndGet();
    }

    public E removeFirst() {
        Node<E> headNode, valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;
        } while (valueNode != null && !head.compareAndSet(headNode, valueNode));
        E value = (valueNode != null ? valueNode.element : null);
        if (valueNode != null) {
            valueNode.element = null;
        }
        size.decrementAndGet();
        return value;
    }


    private static class Node<E> {
        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        LockFreeQueue<Long> longLockFreeQueue = new LockFreeQueue<>();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                longLockFreeQueue.addLast(System.currentTimeMillis());
                countDownLatch.countDown();
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!longLockFreeQueue.isEmpty()) {
                        System.out.println(longLockFreeQueue.removeFirst());
                    }
                }
            }).start();
        }
    }
}
