package ru.example.spring;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 10;

    public synchronized void enqueue(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int value = queue.poll();
        notifyAll();
        return value;
    }

    public synchronized int size() {
        return queue.size();
    }
}
