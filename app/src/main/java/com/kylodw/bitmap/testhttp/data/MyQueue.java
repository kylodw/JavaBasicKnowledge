package com.kylodw.bitmap.testhttp.data;

import java.util.Queue;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/04/30
 */
public class MyQueue<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }

    private Node<E> first, last;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public E peekFirst() {
        return (first == null) ? null : first.getElement();
    }

    public E peekLast() {
        return (last == null) ? null : last.getElement();
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E answer = first.getElement();
        first = first.next;
        size--;
        if (size == 0) {
            last = null;
        }
        return answer;
    }

    public static void main(String[] args) {
        //先进先出
        MyQueue<String> stringMyQueue = new MyQueue<>();
        stringMyQueue.addLast("Hello");
        stringMyQueue.addLast("world");
        stringMyQueue.addLast("java");

        System.out.println(stringMyQueue.removeFirst());
        System.out.println(stringMyQueue.removeFirst());
        System.out.println(stringMyQueue.removeFirst());
    }
}
